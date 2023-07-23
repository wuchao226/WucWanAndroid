package com.wuc.banner.annotation

import androidx.annotation.IntDef
import com.wuc.banner.model.IndicatorGravity

/**
 * 指示器位置
 */
@IntDef(IndicatorGravity.CENTER, IndicatorGravity.START, IndicatorGravity.END)
@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.VALUE_PARAMETER)
annotation class AIndicatorGravity()
