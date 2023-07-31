package com.wuc.login.service

import android.content.Context
import android.content.Intent
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.alibaba.android.arouter.facade.annotation.Route
import com.wuc.common.constant.LOGIN_SERVICE_LOGIN
import com.wuc.common.provider.UserServiceProvider
import com.wuc.common.service.ILoginService
import com.wuc.framework.log.LogUtil
import com.wuc.framework.toast.TipsToast
import com.wuc.login.login.LoginActivity
import com.wuc.login.policy.PrivacyPolicyActivity
import com.wuc.network.manager.ApiManager
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

/**
 * @author     wuchao
 * @date       2023/7/30 23:28
 * @description 提供对ILoginService接口的具体实现
 */
@Route(path = LOGIN_SERVICE_LOGIN)
class LoginService : ILoginService {
    /**
     * 是否登录
     * @return Boolean
     */
    override fun isLogin(): Boolean {
        return UserServiceProvider.isLogin()
    }

    /**
     * 跳转登录页
     * @param context
     */
    override fun login(context: Context) {
        context.startActivity(Intent(context, LoginActivity::class.java))
    }

    /**
     * 跳转隐私协议页
     * @param context
     */
    override fun readPolicy(context: Context) {
        context.startActivity(Intent(context, PrivacyPolicyActivity::class.java))
    }

    /**
     * 登出
     */
    override fun logout(context: Context, lifecycleOwner: LifecycleOwner?, observer: Observer<Boolean>) {
        val scope = lifecycleOwner?.lifecycleScope ?: MainScope()
        scope.launch {
            val response = ApiManager.api.logout()
            if (response?.isFailed() == true) {
                TipsToast.showTips(response.errorMsg)
                return@launch
            }
            LogUtil.e("logout${response?.data}", tag = "smy")
            observer.onChanged(response?.isFailed() == true)
            login(context)
        }
    }

    override fun init(context: Context?) {
    }
}