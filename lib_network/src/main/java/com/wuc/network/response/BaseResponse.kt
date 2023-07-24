package com.wuc.network.response

/**
 * @author     wuchao
 * @date       2023/7/24 23:57
 * @desciption 通用数据类
 */
data class BaseResponse<out T>(
    val data: T?,
    val errorCode: Int = 0,//服务器状态码 这里0表示请求成功
    val errorMsg: String = ""//错误信息
) {

    /**
     * 判定接口返回是否正常
     */
    fun isFailed(): Boolean {
        return errorCode != 0
    }
}
