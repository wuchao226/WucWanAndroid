package com.wuc.banner.drawer

import com.wuc.banner.model.IndicatorStyle
import com.wuc.banner.options.IndicatorOptions

/**
 * Indicator Drawer Factory.
 */
internal object DrawerFactory {
    fun createDrawer(indicatorOptions: IndicatorOptions): IDrawer {
        return when (indicatorOptions.indicatorStyle) {
            IndicatorStyle.DASH -> DashDrawer(indicatorOptions)
            IndicatorStyle.ROUND_RECT -> RoundRectDrawer(indicatorOptions)
            else -> CircleDrawer(indicatorOptions)
        }
    }
}
