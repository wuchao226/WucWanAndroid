package com.wuc.user.info

import android.Manifest
import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.lifecycleScope
import com.alibaba.android.arouter.facade.annotation.Route
import com.tbruyelle.rxpermissions3.RxPermissions
import com.wuc.common.constant.USER_ACTIVITY_INFO
import com.wuc.common.manager.FileManager
import com.wuc.common.provider.LoginServiceProvider
import com.wuc.common.provider.UserServiceProvider
import com.wuc.framework.base.BaseDataBindActivity
import com.wuc.framework.utils.ViewUtils
import com.wuc.framework.utils.dpToPx
import com.wuc.user.databinding.ActivityUserInfoBinding
import java.io.File
import com.wuc.glide.loadFile
import com.wuc.framework.ext.onClick
import com.wuc.framework.log.LogUtil
import com.wuc.framework.toast.TipsToast
import com.wuc.user.dialog.ChoosePhotoDialog
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.wuc.common.R
import com.wuc.user.dialog.ChooseSexDialog
import com.wuc.user.dialog.SelectBirthdayDialog

/**
 * @author     wuchao
 * @date       2023/8/1 00:09
 * @desciption 用户信息
 */
@Route(path = USER_ACTIVITY_INFO)
class UserInfoActivity : BaseDataBindActivity<ActivityUserInfoBinding>() {
    //相机拍照URI
    private var photoUri: Uri? = null

    //裁剪图片URI
    private var mUploadImageUri: Uri? = null

    //裁剪图片文件
    private var mUploadImageFile: File? = null

    //保存的头像路径
    private var saveAvatarPath: String? = null

    //拍照回调
    var mActivityResultLauncherTake: ActivityResultLauncher<Intent>? = null

    //裁剪回调
    var mActivityResultLauncherCrop: ActivityResultLauncher<Intent>? = null

    //相册回调
    var mActivityResultLauncherAlbum: ActivityResultLauncher<Intent>? = null

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, UserInfoActivity::class.java)
            context.startActivity(intent)
        }
    }

    /**
     * 性别选择弹框
     */
    fun showSelectSexDialog() {
        ChooseSexDialog.Builder(this).setOnSexChooseCall {
            mBinding.tvSex.text = it
        }.show()
    }

    override fun initView(savedInstanceState: Bundle?) {
        ViewUtils.setClipViewCornerRadius(mBinding.tvSave, dpToPx(20))
        initUserInfo()
        initListener()
        registerActivityResult()
    }

    /**
     * 设置用户信息
     */
    private fun initUserInfo() {
        val user = UserServiceProvider.getUserInfo() ?: return
        //通过DataBinding绑定数据
        mBinding.user = user
        mBinding.activity = this
        mBinding.ivHead.loadFile(File(user.icon ?: ""))
    }

    private fun initListener() {
        mBinding.clBirthday.onClick {
            showBirthdayDialog()
        }
        mBinding.tvSave.onClick {
            saveUserInfo()
        }
        mBinding.clHead.onClick {
            showChoosePhotoDialog()
        }
    }
    /**
     * 设置生日日期
     */
    private fun showBirthdayDialog() {
        SelectBirthdayDialog.Builder(this).setBirthDayDateCall { date ->
            mBinding.tvBirthday.text = date
        }.show()
    }

    /**
     * 保存用户信息
     */
    private fun saveUserInfo() {
        val user = UserServiceProvider.getUserInfo()
        user?.let {
            if (!saveAvatarPath.isNullOrEmpty()) {
                user.icon = saveAvatarPath
            }
            user.nickname = mBinding.etName.text.toString()
            user.sex = mBinding.tvSex.text.toString()
            user.signature = mBinding.etSignature.text.toString()
            user.birthday = mBinding.tvBirthday.text.toString()
            showLoading()
            lifecycleScope.launch {
                UserServiceProvider.saveUserInfo(user)
                delay(500)
                dismissLoading()
                TipsToast.showTips(R.string.default_save_success)
            }
        } ?: kotlin.run {
            LoginServiceProvider.login(this)
        }
    }

    /**
     * 选择图片弹框
     */
    @SuppressLint("CheckResult")
    private fun showChoosePhotoDialog() {
        RxPermissions(this).request(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
        ).subscribe { granted ->
            if (granted) {
                ChoosePhotoDialog.Builder(this)
                    .setPhotoAlbumCall {
                        openAlbum()
                    }.setTakePicturesCall {
                        takePictures()
                    }.show()
            } else {
                TipsToast.showTips(com.wuc.common.R.string.default_agree_permission)
            }
        }
    }

    /**
     * 打开本地相册
     */
    private fun openAlbum() {
        val intentAlbum = Intent(Intent.ACTION_PICK, null)
        intentAlbum.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*")
        mActivityResultLauncherAlbum?.launch(intentAlbum)
    }

    /**
     * 调取本地相机拍照
     */
    private fun takePictures() {
        val takeIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val values = ContentValues()
        //根据uri查询图片地址
        photoUri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
        LogUtil.i("photoUri:" + photoUri?.authority + ",photoUri:" + photoUri?.path)
        //放入拍照后的地址
        takeIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
        //调起拍照
        mActivityResultLauncherTake?.launch(takeIntent)
    }

    /**
     * ActivityForResult
     */
    private fun registerActivityResult() {
        mActivityResultLauncherTake =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    //拍照回调
                    workCropFun(photoUri)
                }
            }
        mActivityResultLauncherCrop =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    //裁剪回调
                    setAvatar()
                }
            }
        mActivityResultLauncherAlbum =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    result.data?.data.let {
                        mUploadImageUri = it
                        setAvatar()
                    }
                }
            }
    }

    /**
     * 设置用户头像
     */
    private fun setAvatar() {
        val file: File? = if (mUploadImageUri != null) {
            FileManager.getMediaUri2File(mUploadImageUri!!)
        } else {
            mUploadImageFile
        }
        saveAvatarPath = file?.absolutePath
        mBinding.ivHead.loadFile(file)
        LogUtil.i("FilePath:${file?.absolutePath}")
    }

    /**
     * 系统裁剪方法
     */
    private fun workCropFun(imgPathUri: Uri?) {
        mUploadImageUri = null
        mUploadImageFile = null
        imgPathUri?.let {
            val imageObject: Any? = FileManager.getHeadJpgFile()
            if (imageObject is Uri) {
                mUploadImageUri = imageObject
            }
            if (imageObject is File) {
                mUploadImageFile = imageObject
            }
            val intent = Intent("com.android.camera.action.CROP")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }
            intent.run {
                setDataAndType(it, "image/*")// 图片资源
                putExtra("crop", "true") // 裁剪
                putExtra("aspectX", 1) // 宽度比
                putExtra("aspectY", 1) // 高度比
                putExtra("outputX", 200) // 裁剪框宽度
                putExtra("outputY", 200) // 裁剪框高度
                putExtra("scale", true) // 缩放
                putExtra("return-data", false) // true-返回缩略图-data，false-不返回-需要通过Uri
                putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString()) // 保存的图片格式
                putExtra("noFaceDetection", true) // 取消人脸识别
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    putExtra(MediaStore.EXTRA_OUTPUT, mUploadImageUri)
                } else {
                    val imgCropUri = Uri.fromFile(mUploadImageFile)
                    putExtra(MediaStore.EXTRA_OUTPUT, imgCropUri)
                }
            }
            mActivityResultLauncherCrop?.launch(intent)
        }
    }
}