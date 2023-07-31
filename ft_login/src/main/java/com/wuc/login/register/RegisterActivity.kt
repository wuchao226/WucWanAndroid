package com.wuc.login.register

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewbinding.ViewBinding
import com.alibaba.android.arouter.facade.annotation.Route
import com.wuc.common.constant.LOGIN_ACTIVITY_REGISTER
import com.wuc.framework.base.BaseMvvmActivity
import com.wuc.login.R
import com.wuc.login.login.LoginViewModel

/**
 * @author     wuchao
 * @date       2023/7/30 22:00
 * @desciption 注册
 */
@Route(path = LOGIN_ACTIVITY_REGISTER)
class RegisterActivity : BaseMvvmActivity<ViewBinding, LoginViewModel>() {

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, RegisterActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun initView(savedInstanceState: Bundle?) {
        TODO("Not yet implemented")
    }
}