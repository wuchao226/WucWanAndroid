package com.wuc.banner.annotation

import androidx.annotation.IntDef
import com.wuc.banner.model.PageStyle.MULTI_PAGE
import com.wuc.banner.model.PageStyle.MULTI_PAGE_OVERLAP
import com.wuc.banner.model.PageStyle.MULTI_PAGE_SCALE
import com.wuc.banner.model.PageStyle.NORMAL

/**
 * 指示器页面样式
 */
@Target(AnnotationTarget.VALUE_PARAMETER)
@IntDef(NORMAL, MULTI_PAGE, MULTI_PAGE_OVERLAP, MULTI_PAGE_SCALE)
@Retention(AnnotationRetention.SOURCE)
annotation class APageStyle()
