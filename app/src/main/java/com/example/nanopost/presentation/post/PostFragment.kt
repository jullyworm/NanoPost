package com.example.nanopost.presentation.post

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.example.nanopost.R
import com.example.nanopost.databinding.FragmentPostBinding
import java.util.*

class PostFragment : Fragment(R.layout.fragment_post) {
    private val binding by viewBinding(FragmentPostBinding::bind)
    private val viewModel: PostViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        val postId: String? = requireArguments().getString("postId")
        if (postId != null) {
            viewModel.getPost(postId)
        }

        viewModel.postLiveData.observe(viewLifecycleOwner){post ->
            binding.contentHeader.text = post.owner.username
            binding.contentMonogram.load(post.owner.avatarUrl)
            binding.contentTime.text = Date(post.dateCreater.toLong()).toString()
            if (post.text != null) binding.contentText.text = post.text
            binding.contentImage1.load(post.images[0].sizes.first().url)
            binding.contentImage2.load(post.images[1].sizes.first().url)
            binding.contentImage3.load(post.images[2].sizes.first().url)
            binding.contentImage4.load(post.images[3].sizes.first().url)
        }
    }
}