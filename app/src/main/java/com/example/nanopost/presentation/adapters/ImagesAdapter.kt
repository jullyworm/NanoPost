package com.example.nanopost.presentation.adapters


import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.example.nanopost.R
import com.example.nanopost.data.models.Image
import com.example.nanopost.databinding.ImageBinding
import com.example.nanopost.inflate

class ImagesAdapter : ListAdapter<Image, ImagesAdapter.ViewHolder>(DiffCallback) {

    var onItemClick: (Image) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            parent = parent,
            onItemClick = onItemClick,
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        parent: ViewGroup,
        private val onItemClick: (Image) -> Unit
    ) : RecyclerView.ViewHolder(
        parent.inflate(R.layout.image),
    ) {
        private val binding by viewBinding(ImageBinding::bind)

        fun bind(item: Image) {
            binding.image.load(item.sizes.last().url)
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<Image>() {
        override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
            return oldItem.id == newItem.id
        }
    }
}