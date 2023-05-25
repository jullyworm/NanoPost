package com.example.nanopost.presentation.adapters

import android.net.Uri
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.example.nanopost.R
import com.example.nanopost.databinding.ImageBinding
import com.example.nanopost.inflate


class ImagesAdderAdapter : ListAdapter<Uri, ImagesAdderAdapter.ViewHolder>(DiffCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            parent = parent,
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        parent: ViewGroup,
    ) : RecyclerView.ViewHolder(
        parent.inflate(R.layout.image),
    ) {
        private val binding by viewBinding(ImageBinding::bind)

        fun bind(item: Uri) {
            binding.image.load(item)
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<Uri>() {
        override fun areContentsTheSame(oldItem: Uri, newItem: Uri): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: Uri, newItem: Uri): Boolean {
            return oldItem == newItem
        }
    }
}