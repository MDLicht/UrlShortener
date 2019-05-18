package com.mdlicht.zb.simpleurlshortener.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.mdlicht.zb.simpleurlshortener.fragment.HistoryFragment
import com.mdlicht.zb.simpleurlshortener.fragment.ShortenerFragment

class MainVpAdapter(private val context: Context, fm: FragmentManager): FragmentPagerAdapter(fm) {
    private val fragmentList = listOf(ShortenerFragment.newInstance(), HistoryFragment.newInstance())

    override fun getPageTitle(position: Int): CharSequence? = fragmentList[position].getFragmentTitle(context)

    override fun getItem(p0: Int): Fragment = fragmentList[p0]

    override fun getCount(): Int = fragmentList.size
}