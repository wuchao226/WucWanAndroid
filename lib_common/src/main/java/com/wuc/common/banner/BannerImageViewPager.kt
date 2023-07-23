package com.wuc.common.banner

import android.content.Context
import android.util.AttributeSet
import androidx.viewpager2.widget.ViewPager2
import com.wuc.banner.BannerViewPager
import com.wuc.common.holder.BannerImageHolder
import com.wuc.common.model.Banner

/**
 * 图片类型Banner
 */
open class BannerImageViewPager(context: Context, attrs: AttributeSet? = null) :
    BannerViewPager<Banner, BannerImageHolder>(context, attrs) {

    init {
        registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                val banner = getData()[position]
//                val banner = ListUtils.getItem(getData(), position)
            }
        })
    }
}