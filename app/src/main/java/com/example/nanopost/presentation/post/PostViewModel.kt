package com.example.nanopost.presentation.post

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nanopost.data.models.Post
import com.example.nanopost.domain.post.GetPostUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val getPostUseCase: GetPostUseCase,
) : ViewModel() {

    private val _postLiveData = MutableLiveData<Post>()
    val postLiveData: LiveData<Post> = _postLiveData

    fun getPost(postId: String) {
        viewModelScope.launch {
            _postLiveData.postValue(getPostUseCase(postId))
        }
    }

}