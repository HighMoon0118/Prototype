package com.e.prototype.mvvm.feature.community

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.e.prototype.databinding.FragmentCommunityBinding
import com.e.prototype.mvvm.feature.community.gossip.GossipFragment
import java.util.*


class CommunityFragment : Fragment() {

    private var _binding: FragmentCommunityBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCommunityBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.viewPager.adapter = PagerAdapter(childFragmentManager)
        binding.tabs.setupWithViewPager(binding.viewPager)


        return view
    }

    private class PagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            val fragment = when(position) {
                0 -> GossipFragment()
                else -> GossipFragment()
            }
            return fragment
        }

        override fun getPageTitle(position: Int): CharSequence? {
            val title = when(position)
            {
                0->"잡담"
                1->"자소서"
                2->"면접"
                3->"관심글"
                else -> "잡담"
            }
            return title
        }

        override fun getCount(): Int = 4

    }
}