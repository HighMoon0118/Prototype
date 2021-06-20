package com.e.prototype.mvvm.feature.community

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.ViewModelProvider
import com.e.prototype.R
import com.e.prototype.databinding.FragmentCommunityBinding
import com.e.prototype.mvvm.feature.community.coverletter.CoverLetterFragment
import com.e.prototype.mvvm.feature.community.gossip.GossipFragment
import com.e.prototype.mvvm.feature.community.interview.InterviewFragment
import com.e.prototype.mvvm.feature.community.mypost.MyPostFragment
import com.e.prototype.mvvm.feature.community.post.AddPostActivity
import com.e.prototype.mvvm.feature.main.PostViewModel


class CommunityFragment : Fragment(), View.OnClickListener{

    private var isFabOpen = false
    private lateinit var fabOpen : Animation
    private lateinit var fabClose: Animation
    private lateinit var viewModel: PostViewModel

    private var _binding: FragmentCommunityBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        activity?.run {
            viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(this.application))
                .get(PostViewModel::class.java)
        }

        _binding = FragmentCommunityBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.viewPager.adapter = PagerAdapter(childFragmentManager)
        binding.tabs.setupWithViewPager(binding.viewPager)

        binding.fabMain.setOnClickListener(this)
        binding.fabGossip.setOnClickListener(this)
        binding.fabInterview.setOnClickListener(this)
        binding.fabCoverLetter.setOnClickListener(this)

        fabOpen = AnimationUtils.loadAnimation(context, R.anim.fab_open)
        fabClose = AnimationUtils.loadAnimation(context, R.anim.fab_close)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            if(isFabOpen) {
                anim()
            }
            else {
                super.getActivity()?.finish()
            }
        }


        return view
    }

    override fun onResume() {
        super.onResume()
        binding.viewPager.currentItem = viewModel.currentItem.value!!
    }

    override fun onPause() {
        super.onPause()
        if(isFabOpen){
            anim()
        }
        viewModel.setCurrentItem(binding.viewPager.currentItem)
    }



    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.fab_main -> {
                anim()
            }

            R.id.fab_gossip -> {
                anim()
                val intent = Intent(activity, AddPostActivity::class.java)
                intent.putExtra("kind", 0)
                startActivityForResult(intent, 0)
            }

            R.id.fab_cover_letter -> {
                anim()
                val intent = Intent(activity, AddPostActivity::class.java)
                intent.putExtra("kind", 1)
                startActivityForResult(intent, 0)
            }

            R.id.fab_interview -> {
                anim()
                val intent = Intent(activity, AddPostActivity::class.java)
                intent.putExtra("kind", 2)
                startActivityForResult(intent, 0)
            }
        }
    }

    private fun anim() {
        if(isFabOpen) {
            binding.fabGossip.startAnimation(fabClose)
            binding.fabCoverLetter.startAnimation(fabClose)
            binding.fabInterview.startAnimation(fabClose)
            isFabOpen = false
        }
        else {
            binding.fabGossip.show()
            binding.fabCoverLetter.show()
            binding.fabInterview.show()
            binding.fabGossip.startAnimation(fabOpen)
            binding.fabCoverLetter.startAnimation(fabOpen)
            binding.fabInterview.startAnimation(fabOpen)
            isFabOpen = true
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            val kind = data?.getIntExtra("kind", 0)
            binding.viewPager.currentItem = kind!!
        }
    }

    private inner class PagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
        override fun getCount(): Int = 4

        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> GossipFragment()
                1 -> CoverLetterFragment()
                2 -> InterviewFragment()
                3 -> MyPostFragment()
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
    }
}