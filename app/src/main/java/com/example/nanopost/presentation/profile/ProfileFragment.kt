package com.example.nanopost.presentation.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.example.nanopost.presentation.adapters.PagingDataAdapter
import com.example.nanopost.R
import com.example.nanopost.data.models.Profile
import com.example.nanopost.databinding.FragmentProfileBinding
import com.example.nanopost.presentation.adapters.ImagesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private val binding by viewBinding(FragmentProfileBinding::bind)
    private val postAdapter = PagingDataAdapter()
    private val imagesAdapter = ImagesAdapter()
    private val viewModel: ProfileViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //val profileId: String? = requireArguments().getString("profileId")
        var userFlag = false

        binding.recycler.apply {
            adapter = postAdapter.apply {
                onItemClick = {
                    findNavController().navigate(
                        ProfileFragmentDirections.actionProfileFragmentToPostFragment(it.id)
                    )
                }
            }
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
        }
        binding.imageCard.arrow.setOnClickListener {
            findNavController().navigate(
                ProfileFragmentDirections.actionProfileFragmentToImagesFragment()
            )
        }
        binding.imageCard.images.apply {
            adapter = imagesAdapter.apply {
                onItemClick = {
                    findNavController().navigate(
                        ProfileFragmentDirections.actionProfileFragmentToImageFragment(it.id)
                    )
                }
            }
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
        }

        viewModel.getUserId()
        /*if (profileId != null) {
            viewModel.getProfile(profileId)
        }
        if (profileId != null) {
            viewModel.loadPosts(profileId)
        }*/

        viewModel.userIdLivaData.observe(viewLifecycleOwner) { userId ->
            if (userId != null) {
                viewModel.getProfile(userId)
                viewModel.loadPosts(userId)
                userFlag = true
            }
        /*if (profileId == null) {
                userFlag = true
                if (userId != null) {
                    viewModel.getProfile(userId)
                }
                if (userId != null) {
                    viewModel.loadPosts(userId)
                }
            }
            else userFlag = userId == profileId*/
        }

        viewModel.profileLiveData.observe(viewLifecycleOwner) { profile ->
            imagesAdapter.submitList(profile.images)
            bindProfile(profile, userFlag)
        }

        viewModel.postsLiveData.observe(viewLifecycleOwner) { posts ->
            postAdapter.submitData(viewLifecycleOwner.lifecycle, posts)
        }

        binding.fab.setOnClickListener {
            findNavController().navigate(
                ProfileFragmentDirections.actionProfileFragmentToCreatePostFragment()
            )
        }

    }

    private fun bindProfile(profile: Profile, user: Boolean){
        binding.profileCard.headerName.text = profile.username
        binding.profileCard.headerMonogram.load(profile.avatarSmall)
        binding.profileCard.bio.text = profile.bio
        binding.profileCard.headerNumberImages.text = profile.images.size.toString()
        binding.profileCard.headerNumberPosts.text = profile.postsCount.toString()
        binding.profileCard.headerNumberSubscribers.text = profile.subscribersCount.toString()
        if (user) {
            binding.profileCard.subscribeButton.visibility = View.INVISIBLE
            binding.profileCard.unsubscribeButton.visibility = View.INVISIBLE
            binding.profileCard.editButton.visibility = View.VISIBLE
        }
        else if (profile.subscribed) {
            binding.profileCard.subscribeButton.visibility = View.INVISIBLE
            binding.profileCard.unsubscribeButton.visibility = View.VISIBLE
            binding.profileCard.editButton.visibility = View.INVISIBLE
        }
        else {
            binding.profileCard.subscribeButton.visibility = View.VISIBLE
            binding.profileCard.unsubscribeButton.visibility = View.INVISIBLE
            binding.profileCard.editButton.visibility = View.INVISIBLE
        }
    }
}