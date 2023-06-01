package com.example.nanopost.presentation.image

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nanopost.data.models.Image
import com.example.nanopost.domain.images.GetImageUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class ImageViewModel @Inject constructor(
    private val getImageUseCase: GetImageUseCase,
) : ViewModel() {

    private val _imageLiveData = MutableLiveData<Image>()
    val imageLiveData: LiveData<Image> = _imageLiveData

    fun getImage(imageId: String) {
        viewModelScope.launch {
            _imageLiveData.postValue(getImageUseCase(imageId))
        }
    }
}