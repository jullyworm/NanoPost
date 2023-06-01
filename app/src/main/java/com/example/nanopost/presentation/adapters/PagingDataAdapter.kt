package com.example.nanopost.presentation.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.example.nanopost.R
import com.example.nanopost.data.models.Post
import com.example.nanopost.databinding.PostCardBinding
import com.example.nanopost.inflate
import java.util.*

class PagingDataAdapter: androidx.paging.PagingDataAdapter<Post, PagingDataAdapter.ViewHolder>(
    DiffCallback
) {

    var onItemClick: (Post) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            parent = parent,
            onItemClick = onItemClick,
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    class ViewHolder(
        parent: ViewGroup,
        private val onItemClick: (Post) -> Unit
    ) : RecyclerView.ViewHolder(
        parent.inflate(R.layout.post_card),
    ) {
        private val binding by viewBinding(PostCardBinding::bind)

        fun bind(item: Post) {
            binding.contentHeader.text = item.owner.username
            binding.contentText.text = item.text
            binding.contentImage1.load(item.images[0].sizes.first().url)
            binding.contentMonogram.load(item.owner.avatarUrl)
            val date = Date(item.dateCreater.toLong())
            binding.contentTime.text = date.toString()
            binding.root.setOnClickListener {
                onItemClick(item)
            }
            binding.favoriteButton.text = item.likes.likesCount.toString()
            if(item.likes.liked) binding.favoriteButton.setIconResource(R.drawable.baseline_favorite_24)
            else binding.favoriteButton.setIconResource(R.drawable.baseline_favorite_border_24)
        }

    }

    object DiffCallback : DiffUtil.ItemCallback<Post>() {
        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem.id == newItem.id
        }
    }
}