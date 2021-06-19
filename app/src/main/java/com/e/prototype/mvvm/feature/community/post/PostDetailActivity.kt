package com.e.prototype.mvvm.feature.community.post

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.e.prototype.databinding.ActivityPostDetailBinding

class PostDetailActivity : AppCompatActivity(){

    private lateinit var binding: ActivityPostDetailBinding
    private lateinit var viewModel: PostDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("디테일 페이지1", "ㅋㅋㅋㅋㅋㅋㅋ")
        binding = ActivityPostDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application))
            .get(PostDetailViewModel::class.java)

        val id = intent.getIntExtra("PostId", 0)

        val post: Post = viewModel.getPost(id)
        Log.d("디테일 페이지2", "ㅋㅋㅋㅋㅋㅋㅋ")
        binding.textGossipDetailTitle.text = post.title
        binding.textGossipDetailContent.text = post.content
    }
}