package com.wuc.user.dialog

import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import com.wuc.framework.base.BaseDialog
import com.wuc.framework.base.BaseDialogFragment
import com.wuc.framework.ext.onClick
import com.wuc.framework.utils.getStringFromResource
import com.wuc.user.R
import com.wuc.user.databinding.DialogSexChooseBinding

/**
 * @author     wuchao
 * @date       2023/8/9 00:43
 * @description  性别选择弹框
 */
class ChooseSexDialog {
    class Builder(activity: FragmentActivity) : BaseDialogFragment.Builder<Builder>(activity) {
        private var mOnSexChooseCall: ((String) -> Unit)? = null
        private val mBinding = DialogSexChooseBinding.inflate(LayoutInflater.from(activity))

        init {
            initView()
        }

        private fun initView() {
            setContentView(mBinding.root)
            setWidth(ViewGroup.LayoutParams.MATCH_PARENT)
            setHeight(ViewGroup.LayoutParams.WRAP_CONTENT)
            setAnimStyle(BaseDialog.AnimStyle.BOTTOM)
            gravity = Gravity.BOTTOM

            mBinding.clSexBoy.onClick {
                mOnSexChooseCall?.invoke(getStringFromResource(R.string.user_sex_boy))
                dismiss()
            }
            mBinding.clSexGirl.onClick {
                mOnSexChooseCall?.invoke(getStringFromResource(R.string.user_sex_girl))
                dismiss()
            }
            mBinding.tvCancel.onClick {
                dismiss()
            }
        }

        fun setOnSexChooseCall(onSexChooseCall: ((String) -> Unit)): Builder {
            mOnSexChooseCall = onSexChooseCall
            return this
        }
    }
}