package com.e.prototype.mvvm.feature.community.gossip

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.e.prototype.R

class HeaderAdapter: RecyclerView.Adapter<HeaderAdapter.HeaderViewHolder>()  {

    private var gossipHeader: String = "잡담 게시판"

    class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val flowerNumberTextView: TextView = view.findViewById(R.id.text_gossip_header)

        fun bind(gossipHeader: String) {
            flowerNumberTextView.text = gossipHeader
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.header_gossip, parent, false)
        return HeaderViewHolder(view)
    }

    override fun onBindViewHolder(holder: HeaderViewHolder, position: Int) {
        holder.bind(gossipHeader)
    }

    override fun getItemCount(): Int {
        return 1
    }

}