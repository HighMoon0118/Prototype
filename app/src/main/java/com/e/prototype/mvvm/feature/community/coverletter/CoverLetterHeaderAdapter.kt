package com.e.prototype.mvvm.feature.community.coverletter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.e.prototype.R

class CoverLetterHeaderAdapter: RecyclerView.Adapter<CoverLetterHeaderAdapter.HeaderViewHolder>()  {

    private var coverLetterHeader: String = "자소서 게시판"

    class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val flowerNumberTextView: TextView = view.findViewById(R.id.text_post_header)

        fun bind(coverLetterHeader: String) {
            flowerNumberTextView.text = coverLetterHeader
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.header_post, parent, false)
        return HeaderViewHolder(view)
    }

    override fun onBindViewHolder(holder: HeaderViewHolder, position: Int) {
        holder.bind(coverLetterHeader)
    }

    override fun getItemCount(): Int {
        return 1
    }

}