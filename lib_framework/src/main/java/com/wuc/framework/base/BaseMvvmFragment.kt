package com.wuc.framework.base

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

/**
 * @author     wuchao
 * @date       2023/7/23 00:27
 * @desciption ViewBinding 和 ViewModel基类
 */
abstract class BaseMvvmFragment<DB : ViewBinding, VM : ViewModel> : BaseDataBindFragment<DB>() {
    lateinit var mViewModel: VM

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViewModel()
        super.onViewCreated(view, savedInstanceState)
    }

    open fun initViewModel() {
        val argument = (this.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments
        mViewModel = ViewModelProvider(this).get(argument[1] as Class<VM>)
    }
}