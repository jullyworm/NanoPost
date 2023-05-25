package com.example.nanopost.presentation.adapters

import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.example.nanopost.R
import com.example.nanopost.data.models.Profile
import com.example.nanopost.databinding.ProfileCardBinding
import com.example.nanopost.inflate
import javax.inject.Inject

class ProfileAdapter @Inject constructor(): RecyclerView.Adapter<ProfileAdapter.ViewHolder>(){

    private var profile: Profile? = null
    //private var user: Boolean = false

    fun setUser(profile: Profile){
        this.profile = profile
        //this.user = user
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        profile?.let {data ->
            holder.bind(data)
        }
    }

    inner class ViewHolder(
        parent: ViewGroup,
    ) : RecyclerView.ViewHolder(
        parent.inflate(R.layout.profile_card),
    ) {
        private val binding by viewBinding(ProfileCardBinding::bind)

        fun bind(item: Profile) {
            binding.headerName.text = item.username
            binding.headerMonogram.load(item.avatarSmall)
            binding.bio.text = item.bio
            binding.headerNumberImages.text = item.images.size.toString()
            binding.headerNumberPosts.text = item.postsCount.toString()
            binding.headerNumberSubscribers.text = item.subscribersCount.toString()
            //if (user){
                binding.subscribeButton.visibility = INVISIBLE
                binding.unsubscribeButton.visibility = INVISIBLE
                binding.editButton.visibility = VISIBLE
           // }
           /* else if (item.subscribed) {
                binding.subscribeButton.visibility = INVISIBLE
                binding.unsubscribeButton.visibility = VISIBLE
                binding.editButton.visibility = INVISIBLE
            }
            else {
                binding.subscribeButton.visibility = VISIBLE
                binding.unsubscribeButton.visibility = INVISIBLE
                binding.editButton.visibility = INVISIBLE
            }*/
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            parent = parent,
        )
    }

    override fun getItemCount(): Int {
        return 1
    }


}