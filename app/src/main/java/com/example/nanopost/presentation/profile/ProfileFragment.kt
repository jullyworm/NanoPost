package com.example.nanopost.presentation.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.nanopost.presentation.adapters.PagingDataAdapter
import com.example.nanopost.R
import com.example.nanopost.databinding.FragmentFeedBinding
import com.example.nanopost.presentation.adapters.ImagesAdapter
import com.example.nanopost.presentation.adapters.ProfileAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment: Fragment(R.layout.fragment_feed) {

    private val binding by viewBinding(FragmentFeedBinding::bind)
    private val postAdapter = PagingDataAdapter()
    private val profileAdapter = ProfileAdapter()
    private val imagesAdapter = ImagesAdapter()
    private val viewModel: ProfileViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //val profileId: String = requireArguments().getString("profileId")
       // var userFlag = false
        val concatenatedAdapter = ConcatAdapter(profileAdapter, imagesAdapter, postAdapter)


        imagesAdapter.onItemClick = {
            findNavController().navigate(
                ProfileFragmentDirections.actionProfileFragmentToImagesFragment()
            )
        }
        postAdapter.onItemClick = {
            findNavController().navigate(
                ProfileFragmentDirections.actionProfileFragmentToPostFragment(it.id)
            )
        }


        binding.recycler.apply{
            adapter = concatenatedAdapter
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
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

        viewModel.userIdLivaData.observe(viewLifecycleOwner){ userId ->
            if (userId != null) {
                viewModel.getProfile(userId)
                viewModel.loadPosts(userId)
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

        viewModel.profileLiveData.observe(viewLifecycleOwner){ profile ->
            profileAdapter.setUser(profile)
            imagesAdapter.submitList(profile.images)
        }

        viewModel.postsLiveData.observe(viewLifecycleOwner){ posts ->
            postAdapter.submitData(viewLifecycleOwner.lifecycle, posts)
        }

        binding.fab.setOnClickListener{
            findNavController().navigate(
                ProfileFragmentDirections.actionProfileFragmentToCreatePostFragment()
            )
        }

    }
}