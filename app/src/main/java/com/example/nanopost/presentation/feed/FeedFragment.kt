package com.example.nanopost.presentation.feed

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.nanopost.presentation.adapters.PagingDataAdapter
import com.example.nanopost.R
import com.example.nanopost.databinding.FragmentFeedBinding
import com.example.nanopost.presentation.profile.ProfileFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeedFragment: Fragment(R.layout.fragment_feed) {

    private val binding by viewBinding(FragmentFeedBinding::bind)
    private val postAdapter = PagingDataAdapter()
    private val viewModel: FeedViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recycler.apply {
            adapter = postAdapter.apply {
                onItemClick = {
                    findNavController().navigate(
                        ProfileFragmentDirections.actionProfileFragmentToPostFragment(it.id)
                    )
                }
            }
        }

        viewModel.loadPosts()

        viewModel.postsLiveData.observe(viewLifecycleOwner){
            postAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }

        binding.fab.setOnClickListener{
            findNavController().navigate(
                FeedFragmentDirections.actionFeedFragmentToCreatePostFragment()
            )
        }
    }

}