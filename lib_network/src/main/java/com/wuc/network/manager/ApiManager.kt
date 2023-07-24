package com.wuc.network.manager

import com.wuc.network.api.ApiInterface


/**
 * @author mingyan.su
 * @date   2023/2/27 21:14
 * @desc   API管理器
 */
object ApiManager {
    val api by lazy { HttpManager.create(ApiInterface::class.java) }
}