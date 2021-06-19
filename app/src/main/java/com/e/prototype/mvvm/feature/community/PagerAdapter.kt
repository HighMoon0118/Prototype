package com.e.prototype.mvvm.feature.community

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.e.prototype.mvvm.feature.community.gossip.GossipFragment

class PagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> GossipFragment()
            else -> GossipFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "잡담"
            1 -> "자소서"
            2 -> "면접"
            3 -> "관심글"
            else -> "잡담"
        }
    }

    override fun getCount(): Int = 4

}