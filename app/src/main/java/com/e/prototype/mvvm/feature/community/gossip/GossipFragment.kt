package com.e.prototype.mvvm.feature.community.gossip

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.e.prototype.databinding.FragmentCommunityBinding

import com.e.prototype.databinding.FragmentGossipBinding
import com.e.prototype.mvvm.feature.community.post.Post
import com.e.prototype.mvvm.feature.community.post.PostActivity
import com.e.prototype.mvvm.feature.community.post.PostDetailActivity
import com.e.prototype.mvvm.feature.main.ScheduleViewModel

class GossipFragment : Fragment() {


    private var _binding: FragmentGossipBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: GossipViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGossipBinding.inflate(inflater, container, false)

        val view = binding.root
        binding.recyclerGossip.layoutManager = LinearLayoutManager(context)

        activity?.run {
            viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(this.application))
                .get(GossipViewModel::class.java)
        }

        val headerAdapter = HeaderAdapter()
        val gossipAdapter = GossipAdapter{ post -> adapterOnClick(post)}
        val concatAdapter = ConcatAdapter(headerAdapter, gossipAdapter)

        val recyclerView: RecyclerView = binding.recyclerGossip
        recyclerView.adapter = concatAdapter

        viewModel.gossipList.observe(viewLifecycleOwner, Observer<List<Post>>{ postList ->
            Log.d("ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ", postList.size.toString())
            gossipAdapter.submitList(postList)
        })


        return view
    }

    private fun adapterOnClick(post: Post) {

        val intent = Intent(activity, PostDetailActivity::class.java)
        intent.putExtra("PostId", post.id)
        startActivity(intent)
    }
}