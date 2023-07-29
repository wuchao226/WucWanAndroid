package com.wuc.start.utils

import com.wuc.framework.helper.AppHelper
import com.wuc.framework.log.LogUtil

object DispatcherLog {
    var isDebug = AppHelper.isDebug()

    @JvmStatic
    fun i(msg: String?) {
        if (msg == null) {
            return
        }
        LogUtil.i(msg, tag = "StartTask")
    }
}