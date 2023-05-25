package com.example.nanopost.presentation.images

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.nanopost.data.models.Image
import com.example.nanopost.data.models.Post
import com.example.nanopost.domain.auth.GetUserIdUseCase
import com.example.nanopost.domain.images.GetImageUseCase
import com.example.nanopost.domain.images.GetImagesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImagesViewModel @Inject constructor(
    private val getImagesUseCase: GetImagesUseCase,
    private val getUserIdUseCase: GetUserIdUseCase,
) : ViewModel() {
    private val _imagesLiveData = MutableLiveData<PagingData<Image>>()
    val imagesLiveData: LiveData<PagingData<Image>> = _imagesLiveData

    private val _userIdLiveData = MutableLiveData<String?>()
    val userIdLivaData: LiveData<String?> = _userIdLiveData

    fun getUserId(){
        viewModelScope.launch {
            _userIdLiveData.postValue(getUserIdUseCase())
        }
    }

    fun loadImages(profileId: String){
        viewModelScope.launch {
            getImagesUseCase(profileId)
                .cachedIn(viewModelScope)
                .collect{
                    _imagesLiveData.value = it
                }
        }
    }

}