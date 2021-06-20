package com.e.prototype.mvvm.feature.community.post

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.e.prototype.R
import com.e.prototype.databinding.ActivityAddPostBinding
import java.text.SimpleDateFormat
import java.util.*

class AddPostActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityAddPostBinding
    private lateinit var viewModel: AddPostViewModel
    private var kind = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddPostBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        kind = intent.getIntExtra("kind", 0)

        binding.btnAddPost.setOnClickListener(this)
        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(this.application))
            .get(AddPostViewModel::class.java)

    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btn_add_post -> {
                val title = binding.editAddPostTitle.text.toString()
                val content = binding.editAddPostContent.text.toString()
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

                val intent = Intent()
                intent.putExtra("kind", kind)
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }
    }
}