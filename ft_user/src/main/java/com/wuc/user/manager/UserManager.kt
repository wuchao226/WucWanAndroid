package com.wuc.user.manager

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tencent.mmkv.MMKV
import com.wuc.common.constant.USER_INFO_DATA
import com.wuc.common.constant.USER_PHONE_NUMBER
import com.wuc.common.model.User

/**
 * @author     wuchao
 * @date       2023/7/31 23:29
 * @description  用户管理类
 */
object UserManager {
    private var mmkv = MMKV.defaultMMKV()
    private val userLiveData = MutableLiveData<User?>()

    /**
     * 是否登录
     * @return Boolean
     */
    fun isLogin(): Boolean {
        return getUserInfo() != null
    }

    /**
     * 保存用户信息
     * @param user
     */
    fun saveUserInfo(user: User?) {
        mmkv.encode(USER_INFO_DATA, user)
        if (userLiveData.hasObservers()) {
            userLiveData.postValue(user)
        }
    }

    /**
     * 保存用户手机号码
     * @param phone
     */
    fun saveUserPhone(phone: String?) {
        mmkv.encode(USER_PHONE_NUMBER, phone)
    }

    /**
     * 保存用户本地头像
     * @param path
     */
    fun saveUserPhoto(path: String?) {
        val user = getUserInfo()
        user?.icon = path
        saveUserInfo(user)
    }


    /**
     * 获取用户信息
     * @return User
     */
    fun getUserInfo(): User? {
        return mmkv.decodeParcelable(USER_INFO_DATA, User::class.java)
    }

    /**
     * 获取用户手机号码
     * @return phone
     */
    fun getUserPhone(): String? {
        return mmkv.decodeString(USER_PHONE_NUMBER, "")
    }

    /**
     * 清除用户信息
     */
    fun clearUserInfo() {
        mmkv.encode(USER_INFO_DATA, "")
        saveUserPhone("")
        if (userLiveData.hasObservers()) {
            userLiveData.postValue(null)
        }
    }


    /**
     * 获取用户LiveData
     * @return LiveData<User>
     */
    fun getUserLiveData(): LiveData<User?> {
        return userLiveData
    }
}