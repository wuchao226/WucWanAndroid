package com.wuc.framework.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.wuc.framework.ext.saveAs
import com.wuc.framework.ext.saveAsUnChecked
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * @author     wuchao
 * @date       2023/7/23 00:07
 * @description ViewBinding Fragment 基类
 */
abstract class BaseDataBindFragment<DB : ViewBinding> : BaseFragment() {
    //This property is only valid between onCreateView and onDestroyView.
    var mBinding: DB? = null

    override fun getContentView(inflater: LayoutInflater, container: ViewGroup?): View {
//        mBinding = DataBindingUtil.inflate(inflater, getLayoutResId(), container, false)
        // getSuperclass()获得该类的父类
        // getGenericSuperclass()获得带有泛型的父类
        // Type是 Java 编程语言中所有类型的公共高级接口。它们包括原始类型、参数化类型、数组类型、类型变量和基本类型。
        val type: Type? = javaClass.genericSuperclass
        // ParameterizedType 参数化类型，即泛型,具体的泛型类型, 如Map<String, String>
        // getActualTypeArguments 获取参数化类型的数组，返回实际泛型类型列表,泛型可能有多个
//      val vbClass : Class<DB> = (type as ParameterizedType).actualTypeArguments[0] as Class<DB>
        val vbClass: Class<DB> = type!!.saveAs<ParameterizedType>().actualTypeArguments[0].saveAs<Class<DB>>()
        // getDeclaredMethod(String name, Class[] params) 获得类声明的命名的方法
        val method = vbClass.getDeclaredMethod("inflate", LayoutInflater::class.java)
        mBinding = method.invoke(this, layoutInflater)!!.saveAsUnChecked()
        return mBinding!!.root
    }

    override fun getLayoutResId(): Int = 0

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }
}