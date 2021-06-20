package com.e.prototype.mvvm.feature.community.coverletter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.e.prototype.R
import com.e.prototype.mvvm.feature.community.post.Post

class CoverLetterAdapter(private val onClick: (Post) -> Unit):
    ListAdapter<Post, CoverLetterAdapter.ViewHolder>(CoverLetterDiffCallback){


    class ViewHolder(itemView: View, val onClick: (Post) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private val numTextView: TextView = itemView.findViewById(R.id.text_post_num)
        private val titleTextView: TextView = itemView.findViewById(R.id.text_post_title)
        private var currentPost: Post? = null
        init {
            itemView.setOnClickListener {
                currentPost?.let {
                    onClick(it)
                }
            }
        }

        fun bind(post: Post) {
            Log.d("바인드 됐냐", "ㅇㅇㅇ")
            currentPost = post
            numTextView.text = post.id.toString()
            titleTextView.text = post.title
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_post, viewGroup, false)

        return ViewHolder(view, onClick)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val post = getItem(position)
        viewHolder.bind(post)
    }

}

object CoverLetterDiffCallback : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id == newItem.id
    }
}