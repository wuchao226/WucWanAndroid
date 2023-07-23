package com.wuc.banner.annotation

import androidx.annotation.IntDef
import com.wuc.banner.model.IndicatorSlideMode.Companion.COLOR
import com.wuc.banner.model.IndicatorSlideMode.Companion.NORMAL
import com.wuc.banner.model.IndicatorSlideMode.Companion.SCALE
import com.wuc.banner.model.IndicatorSlideMode.Companion.SMOOTH
import com.wuc.banner.model.IndicatorSlideMode.Companion.WORM

/**
 * 指示器滑动模式
 */
@IntDef(NORMAL, SMOOTH, WORM, COLOR, SCALE)
@kotlin.annotation.Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FIELD)
annotation class AIndicatorSlideMode
