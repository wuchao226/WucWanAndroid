package com.wuc.banner.model

/**
 * 指示器样式
 */
interface IndicatorStyle {
    companion object {
        const val CIRCLE = 0

        const val DASH = 1 shl 1
        // 值为 1 左移 2 位, 即二进制表示为 100。在十进制中，二进制数 100 对应的值是 4。因此，ROUND_RECT 的值将是 4。
        const val ROUND_RECT = 1 shl 2
    }
}