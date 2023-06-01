package com.example.nanopost.presentation.createPost

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nanopost.domain.images.PutImageUseCase
import com.example.nanopost.domain.post.CreatePostUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreatePostViewModel @Inject constructor(
    private val createPostUseCase: CreatePostUseCase,
    private val putImageUseCase: PutImageUseCase,
) : ViewModel() {

    private val _imagesLiveData = MutableLiveData<List<Uri>>()
    val imagesLiveData: LiveData<List<Uri>> = _imagesLiveData

    private val _textLiveData = MutableLiveData<String>()
    val textLiveData: LiveData<String> = _textLiveData

    fun createPost(text: String?, image1: String?, image2: String?, image3: String?, image4: String?){
        createPostUseCase(text, image1, image2, image3, image4)
    }

    fun addImage(uri: Uri){
    }

}