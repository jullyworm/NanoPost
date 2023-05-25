package com.example.nanopost

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nanopost.domain.auth.GetTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ActivityViewModel @Inject constructor(
    private val getTokenUseCase: GetTokenUseCase,
) : ViewModel() {

    private val _tokenLiveData = MutableLiveData<String?>()
    val tokenLiveData: LiveData<String?> = _tokenLiveData

    fun getToken() {
        _tokenLiveData.postValue(getTokenUseCase())
    }
}