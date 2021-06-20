package com.e.prototype.mvvm.feature.community.coverletter

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
import com.e.prototype.databinding.FragmentCoverLetterBinding
import com.e.prototype.mvvm.feature.community.post.Post
import com.e.prototype.mvvm.feature.community.post.PostDetailActivity
import com.e.prototype.mvvm.feature.main.PostViewModel

class CoverLetterFragment: Fragment() {

    private var _binding : FragmentCoverLetterBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: PostViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCoverLetterBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.recyclerCoverLetter.layoutManager = LinearLayoutManager(context)

        activity?.run {
            viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(this.application))
                .get(PostViewModel::class.java)
        }

        val coverLetterHeaderAdapter = CoverLetterHeaderAdapter()
        val coverLetterAdapter = CoverLetterAdapter{ post -> adapterOnClick(post)}
        val concatAdapter = ConcatAdapter(coverLetterHeaderAdapter, coverLetterAdapter)

        val recyclerView: RecyclerView = binding.recyclerCoverLetter
        recyclerView.adapter = concatAdapter

        viewModel.coverLetterList.observe(viewLifecycleOwner, Observer<List<Post>>{ postList ->
            Log.d("ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ", postList.size.toString())
            coverLetterAdapter.submitList(postList)
        })

        return view
    }

    private fun adapterOnClick(post: Post) {
        val intent = Intent(activity, PostDetailActivity::class.java)
        intent.putExtra("PostId", post.id)
        startActivity(intent)
    }
}