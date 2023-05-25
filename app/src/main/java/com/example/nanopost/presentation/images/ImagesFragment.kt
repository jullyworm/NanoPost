package com.example.nanopost.presentation.images

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.nanopost.presentation.adapters.ImagesPagingDataAdapter
import com.example.nanopost.R
import com.example.nanopost.databinding.FragmentFeedBinding
import com.example.nanopost.databinding.FragmentImagesBinding
import com.example.nanopost.presentation.profile.ProfileFragmentDirections

class ImagesFragment: Fragment(R.layout.fragment_images) {

    private val binding by viewBinding(FragmentImagesBinding::bind)
    private val imagesAdapter = ImagesPagingDataAdapter()
    private val viewModel: ImagesViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //val profileId: String? = requireArguments().getString("profileId")

        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        binding.recycler.apply{
            adapter = imagesAdapter.apply {
                onItemClick = {
                    findNavController().navigate(
                        ImagesFragmentDirections.actionImagesFragmentToImageFragment2(it.id)
                    )
                }
            }
            layoutManager = StaggeredGridLayoutManager(3, 1)
        }

        viewModel.getUserId()
        viewModel.userIdLivaData.observe(viewLifecycleOwner){
            if (it != null) {
                viewModel.loadImages(it)
            }
        }

        viewModel.imagesLiveData.observe(viewLifecycleOwner){
            imagesAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }

    }
}