package com.wuc.common.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * @author     wuchao
 * @date       2023/7/24 00:22
 * @desciption  搜索相关
 */
@Parcelize
data class KeyWord(
    val id: String?,
    val keyWord: String
) : Parcelable

/**
 * 热门搜索
 */
@Parcelize
data class HotSearch(
    val id: Int?,
    val link: String?,
    val name: String? = "",
    val order: Int?,
    val visible: Int?
) : Parcelable