package com.e.prototype.mvvm.feature.community

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import com.e.prototype.R
import com.e.prototype.databinding.FragmentCommunityBinding
import com.e.prototype.mvvm.feature.community.post.PostActivity


class CommunityFragment : Fragment(), View.OnClickListener {

    private var isFabOpen = false
    private lateinit var fabOpen : Animation
    private lateinit var fabClose: Animation

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

        binding.fabMain.setOnClickListener(this)
        binding.fabGossip.setOnClickListener(this)
        binding.fabInterview.setOnClickListener(this)
        binding.fabCoverLetter.setOnClickListener(this)

        fabOpen = AnimationUtils.loadAnimation(context, R.anim.fab_open)
        fabClose = AnimationUtils.loadAnimation(context, R.anim.fab_close)

        return view
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.fab_main -> {
                anim()
            }

            R.id.fab_gossip -> {
                val intent = Intent(activity, PostActivity::class.java)
                intent.putExtra("kind", 0)
                startActivity(intent)
            }
        }
    }

    private fun anim() {
        if(isFabOpen) {
            binding.fabGossip.show()
            binding.fabCoverLetter.show()
            binding.fabInterview.show()
            binding.fabGossip.startAnimation(fabClose)
            binding.fabCoverLetter.startAnimation(fabClose)
            binding.fabInterview.startAnimation(fabClose)
            isFabOpen = false
        }
        else {
            binding.fabGossip.startAnimation(fabOpen)
            binding.fabCoverLetter.startAnimation(fabOpen)
            binding.fabInterview.startAnimation(fabOpen)
            isFabOpen = true
        }
    }
}