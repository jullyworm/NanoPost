package com.example.nanopost.presentation.createPost

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.*
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.nanopost.R
import com.example.nanopost.databinding.FragmentCreatePostBinding
import com.example.nanopost.presentation.adapters.ImagesAdapter
import com.example.nanopost.presentation.adapters.ImagesAdderAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreatePostFragment : Fragment(R.layout.fragment_create_post) {
    private val binding by viewBinding(FragmentCreatePostBinding::bind)
    private val viewModel: CreatePostViewModel by viewModels()
    private val imagesAdapter = ImagesAdderAdapter()
   private val imagePickerLauncher = registerForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        if (uri != null) {
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        binding.images.apply{
            adapter = imagesAdapter
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
        }

        viewModel.imagesLiveData.observe(viewLifecycleOwner) { images ->
            imagesAdapter.submitList(images)
        }



        binding.textInput.doAfterTextChanged { text ->

        }

        binding.chip.setOnClickListener {
            imagePickerLauncher.launch(
                PickVisualMediaRequest(
                    ActivityResultContracts.PickVisualMedia.ImageOnly
                )
            )
        }

    }
}