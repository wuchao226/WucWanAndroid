package com.wuc.login.policy

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewbinding.ViewBinding
import com.wuc.framework.base.BaseDataBindActivity
import com.wuc.login.R
import com.wuc.login.databinding.ActivityPrivacyPolicyBinding

/**
 * @author     wuchao
 * @date       2023/7/30 22:51
 * @desciption 隐私协议
 */
class PrivacyPolicyActivity : BaseDataBindActivity<ActivityPrivacyPolicyBinding>() {

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, PrivacyPolicyActivity::class.java)
            context.startActivity(intent)
        }
    }


    override fun initView(savedInstanceState: Bundle?) {
    }
}