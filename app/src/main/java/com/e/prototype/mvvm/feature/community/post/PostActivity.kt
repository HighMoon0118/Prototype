package com.e.prototype.mvvm.feature.community.post

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.applandeo.materialcalendarview.EventDay
import com.e.prototype.R
import com.e.prototype.databinding.ActivityPostBinding
import com.e.prototype.mvvm.feature.calendar.Schedule
import com.e.prototype.mvvm.feature.main.PostViewModel
import com.e.prototype.mvvm.feature.main.ScheduleViewModel
import kotlinx.android.synthetic.main.activity_post.view.*
import java.text.SimpleDateFormat
import java.util.*

class PostActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityPostBinding
    private lateinit var viewModel: PostViewModel
    private var kind = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        kind = intent.getIntExtra("kind", 0)

        binding.btnPost.setOnClickListener(this)
        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(this.application))
            .get(PostViewModel::class.java)

    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btn_post -> {
                val title = binding.editPostTitle.text.toString()
                val content = binding.editPostContent.text.toString()
                val date = Date(System.currentTimeMillis())
                val dateFormat = SimpleDateFormat("yyyy-MM-dd")

                val post = Post(
                    kind = kind,
                    title = title,
                    content = content,
                    createdAt = dateFormat.format(date),
                    updatedAt = null
                )
                viewModel.insert(post)
                finish()
            }
        }
    }
}