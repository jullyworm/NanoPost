package com.example.nanopost.presentation.image

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.example.nanopost.R
import com.example.nanopost.databinding.FragmentImageBinding
import com.example.nanopost.databinding.FragmentPostBinding
import com.example.nanopost.presentation.post.PostViewModel
import java.util.*

class ImageFragment : Fragment(R.layout.fragment_image) {
    private val binding by viewBinding(FragmentImageBinding::bind)
    private val viewModel: ImageViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        val imageId: String? = requireArguments().getString("imageId")
        if (imageId != null) {
            viewModel.getImage(imageId)
        }

        viewModel.imageLiveData.observe(viewLifecycleOwner){image ->
            binding.contentMonogram.load(image.owner.avatarUrl)
            binding.contentHeader.text = image.owner.username
            binding.contentTime.text = Date(image.dateCreated.toLong()).toString()
            binding.contentImage.load(image.sizes.first().url)
        }
    }
}