package com.wuc.common.provider

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.launcher.ARouter
import com.wuc.common.constant.SEARCH_SERVICE_SEARCH
import com.wuc.common.service.ISearchService


/**
 * @author     wuchao
 * @date       2023/7/24 00:29
 * @desciption SearchService提供类，对外提供相关能力
 * 任意模块就能通过SearchServiceProvider使用对外暴露的能力
 */
object SearchServiceProvider {
    @Autowired(name = SEARCH_SERVICE_SEARCH)
    lateinit var searchService: ISearchService

    init {
        ARouter.getInstance().inject(this)
    }

    /**
     * 跳转搜索页
     * @param context
     */
    fun toSearch(context: Context) {
        searchService.toSearch(context)
    }

    /**
     * 清除搜索历史缓存
     */
    fun clearSearchHistoryCache() {
        searchService.clearSearchHistoryCache()
    }
}