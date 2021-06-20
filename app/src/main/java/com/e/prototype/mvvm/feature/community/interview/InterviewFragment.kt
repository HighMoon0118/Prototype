package com.e.prototype.mvvm.feature.community.interview

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.e.prototype.databinding.FragmentInterviewBinding
import com.e.prototype.mvvm.feature.community.post.Post
import com.e.prototype.mvvm.feature.community.post.PostDetailActivity
import com.e.prototype.mvvm.feature.main.PostViewModel

class InterviewFragment: Fragment() {

    private var _binding: FragmentInterviewBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: PostViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentInterviewBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.recyclerInterview.layoutManager = LinearLayoutManager(context)

        activity?.run {
            viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(this.application))
                .get(PostViewModel::class.java)
        }

        val interviewHeaderAdapter = InterviewHeaderAdapter()
        val interviewAdapter = InterviewAdapter{ post -> adapterOnClick(post)}
        val concatAdapter = ConcatAdapter(interviewHeaderAdapter, interviewAdapter)

        val recyclerView: RecyclerView = binding.recyclerInterview
        recyclerView.adapter = concatAdapter

        viewModel.interviewList.observe(viewLifecycleOwner, Observer<List<Post>>{ postList ->
            Log.d("ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ", postList.size.toString())
            interviewAdapter.submitList(postList)
        })

        return view
    }

    private fun adapterOnClick(post: Post) {
        val intent = Intent(activity, PostDetailActivity::class.java)
        intent.putExtra("PostId", post.id)
        startActivity(intent)
    }
}