package com.wuc.common.provider

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.launcher.ARouter
import com.wuc.common.constant.LOGIN_SERVICE_LOGIN
import com.wuc.common.service.ILoginService


/**
 * @author     wuchao
 * @date       2023/7/24 00:26
 * @desciption  UserService提供类，对外提供相关能力
 * 任意模块就能通过LoginServiceProvider使用对外暴露的能力
 */
object LoginServiceProvider {
    //val loginService = ARouter.getInstance().build(LOGIN_SERVICE_LOGIN).navigation() as? ILoginService
    // [The inject fields CAN NOT BE 'private'!!! please check field [userService] in class [LoginServiceProvider]]
    @Autowired(name = LOGIN_SERVICE_LOGIN)
    lateinit var loginService: ILoginService

    init {
        ARouter.getInstance().inject(this)
    }

    /**
     * 是否登录
     * @return Boolean
     */
    fun isLogin(): Boolean {
        return loginService.isLogin()
    }

    /**
     * 跳转登录页
     * @param context
     */
    fun login(context: Context) {
        loginService.login(context)
    }

    /**
     * 跳转隐私协议
     * @param context
     */
    fun readPolicy(context: Context) {
        loginService.readPolicy(context)
    }

    /**
     * 登出
     * @param context
     * @param lifecycleOwner
     * @param observer
     */
    fun logout(
        context: Context,
        lifecycleOwner: LifecycleOwner?,
        observer: Observer<Boolean>
    ) {
        loginService.logout(context, lifecycleOwner, observer)
    }
}