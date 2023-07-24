package com.wuc.network.callback

import com.wuc.framework.toast.TipsToast


/**
 * @author     wuchao
 * @date       2023/7/24 23:58
 * @desciption 接口请求错误回调
 */
interface IApiErrorCallback {
    /**
     * 错误回调处理
     */
    fun onError(code: Int?, error: String?) {
        TipsToast.showTips(error)
    }

    /**
     * 登录失效处理
     */
    fun onLoginFail(code: Int?, error: String?) {
        TipsToast.showTips(error)
    }
}