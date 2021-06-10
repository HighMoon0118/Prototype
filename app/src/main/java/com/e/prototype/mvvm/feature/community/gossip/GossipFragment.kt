package com.e.prototype.mvvm.feature.community.gossip

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.e.prototype.databinding.FragmentCommunityBinding

import com.e.prototype.databinding.FragmentGossipBinding

class GossipFragment : Fragment() {


    private var _binding: FragmentGossipBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGossipBinding.inflate(inflater, container, false)

        val view = binding.root
        binding.recyclerGossip.layoutManager = LinearLayoutManager(context)

        return view
    }
}