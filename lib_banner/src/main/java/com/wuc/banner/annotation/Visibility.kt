package com.wuc.banner.annotation

import android.view.View
import androidx.annotation.IntDef

/**
 * 指示器可见性
 */
@IntDef(View.VISIBLE, View.INVISIBLE, View.GONE)
@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.VALUE_PARAMETER)
annotation class Visibility()
