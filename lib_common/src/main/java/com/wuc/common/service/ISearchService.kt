package com.wuc.common.service

import android.content.Context
import com.alibaba.android.arouter.facade.template.IProvider

/**
 * @author     wuchao
 * @date       2023/7/24 00:28
 * @desciption 搜索服务相关接口
 * 提供了对外相关能力，其他模块只需要按需添加，需要在SearchService模块实现
 */
interface ISearchService : IProvider {
    /**
     * 跳转搜索页
     * @param context
     */
    fun toSearch(context: Context)

    /**
     * 清除搜索历史缓存
     */
    fun clearSearchHistoryCache()
}