package com.wuc.framework.adapter

import android.util.SparseArray
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * @author     wuchao
 * @date       2023/7/22 00:25
 * @description FragmentStateAdapter
 */
class ViewPage2FragmentAdapter(activity: FragmentActivity, var fragments: SparseArray<Fragment>) :
    FragmentStateAdapter(activity) {
    //FragmentStateAdapter内部自己会管理已实例化的fragment对象，所以不需要考虑复用的问题。
    override fun getItemCount(): Int {
        return fragments.size()
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

    fun setData(fragments: SparseArray<Fragment>) {
        this.fragments = fragments
    }
}