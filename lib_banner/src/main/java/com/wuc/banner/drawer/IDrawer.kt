package com.wuc.banner.drawer

import android.graphics.Canvas
import com.wuc.banner.base.BaseDrawer

/**
 * IDrawer
 */
interface IDrawer {

    fun onLayout(
        changed: Boolean,
        left: Int,
        top: Int,
        right: Int,
        bottom: Int
    )

    fun onMeasure(
        widthMeasureSpec: Int,
        heightMeasureSpec: Int
    ): BaseDrawer.MeasureResult

    fun onDraw(canvas: Canvas)
}
