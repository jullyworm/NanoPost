package com.example.nanopost.presentation.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.nanopost.data.models.Post
import com.example.nanopost.data.models.Profile
import com.example.nanopost.domain.auth.GetUserIdUseCase
import com.example.nanopost.domain.profile.GetProfilePostsUseCase
import com.example.nanopost.domain.profile.GetProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getProfilePostsUseCase: GetProfilePostsUseCase,
    private val getProfileUseCase: GetProfileUseCase,
    private val getUserIdUseCase: GetUserIdUseCase,
) : ViewModel() {

    private val _profileLiveData = MutableLiveData<Profile>()
    val profileLiveData: LiveData<Profile> = _profileLiveData

    private val _postsLiveData = MutableLiveData<PagingData<Post>>()
    val postsLiveData: LiveData<PagingData<Post>> = _postsLiveData

    private val _userIdLiveData = MutableLiveData<String?>()
    val userIdLivaData: LiveData<String?> = _userIdLiveData

    fun getProfile(profileId: String){
        viewModelScope.launch {
            _profileLiveData.postValue(getProfileUseCase(profileId))
        }
    }

    fun getUserId(){
        viewModelScope.launch {
            _userIdLiveData.postValue(getUserIdUseCase())
        }
    }

    fun loadPosts(profileId: String){
        viewModelScope.launch {
            getProfilePostsUseCase(profileId)
                .cachedIn(viewModelScope)
                .collect{
                    _postsLiveData.value = it
                }
        }
    }
}