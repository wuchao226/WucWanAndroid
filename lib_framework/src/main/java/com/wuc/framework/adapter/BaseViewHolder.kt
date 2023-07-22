package com.wuc.framework.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

/**
 * @author     wuchao
 * @date       2023/7/21 23:34
 * @description  基本ViewHolder
 */

open class BaseViewHolder(rootView: View) : RecyclerView.ViewHolder(rootView)

open class BaseBindViewHolder<B : ViewBinding>(val binding: B) : BaseViewHolder(binding.root)