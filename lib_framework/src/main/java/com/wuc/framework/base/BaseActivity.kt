package com.wuc.framework.base

import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.wuc.framework.R
import com.wuc.framework.toast.TipsToast
import com.wuc.framework.utils.LoadingUtils

/**
 * @author     wuchao
 * @date       2023/7/22 23:36
 * @description Activity基类
 */
abstract class BaseActivity : AppCompatActivity() {
    protected var TAG: String? = this::class.java.simpleName
    private val dialogUtils by lazy { LoadingUtils(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentLayout()
        initView(savedInstanceState)
        initData()
    }

    /**
     * 设置布局
     */
    open fun setContentLayout() {
        setContentView(getLayoutResId())
    }

    /**
     * 初始化视图
     * @return Int 布局id
     * 仅用于不继承BaseDataBindActivity类的传递布局文件
     */
    abstract fun getLayoutResId(): Int

    /**
     * 初始化布局
     * @param savedInstanceState Bundle?
     */
    abstract fun initView(savedInstanceState: Bundle?)

    /**
     * 初始化数据
     */
    open fun initData() {

    }

    /**
     * 加载中……弹框
     */
    fun showLoading() {
        showLoading(getString(R.string.default_loading))
    }

    /**
     * 加载提示框
     */
    fun showLoading(msg: String?) {
        dialogUtils.showLoading(msg)
    }

    /**
     * 加载提示框
     */
    fun showLoading(@StringRes res: Int) {
        showLoading(getString(res))
    }

    /**
     * 关闭提示框
     */
    fun dismissLoading() {
        dialogUtils.dismissLoading()
    }

    /**
     * Toast
     * @param msg Toast内容
     */
    fun showToast(msg: String) {
        TipsToast.showTips(msg)
    }

    /**
     * Toast
     * @param resId 字符串id
     */
    fun showToast(@StringRes resId: Int) {
        TipsToast.showTips(resId)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}