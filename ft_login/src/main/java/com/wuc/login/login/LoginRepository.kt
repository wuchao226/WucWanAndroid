package com.wuc.login.login

import com.wuc.common.model.User
import com.wuc.network.manager.ApiManager
import com.wuc.network.repository.BaseRepository

/**
 * @author     wuchao
 * @date       2023/7/29 20:44
 * @description  登录仓库
 */
class LoginRepository : BaseRepository() {
    /**
     * 登录
     * @param username  用户名
     * @param password  密码
     */
    suspend fun login(username: String, password: String): User? {
        return requestResponse {
            ApiManager.api.login(username, password)
        }
    }

    /**
     * 注册
     * @param username  用户名
     * @param password  密码
     * @param repassword  确认密码
     */
    suspend fun register(username: String, password: String, repassword: String): User? {
        return requestResponse {
            ApiManager.api.register(username, password, repassword)
        }
    }
}