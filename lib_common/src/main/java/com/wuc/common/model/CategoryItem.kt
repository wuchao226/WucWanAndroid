package com.wuc.common.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * @author     wuchao
 * @date       2023/7/24 00:21
 * @desciption 分类tab信息
 */
data class CategoryItem(
    val cid: Int?,
    val name: String?,
    var isSelected: Boolean?,
    val articles: MutableList<CategorySecondItem>? = mutableListOf()
)

@Parcelize
data class CategorySecondItem(
    val id: Int?,
    val link: String?,
    val title: String?,
    val chapterId: Int?
) : Parcelable