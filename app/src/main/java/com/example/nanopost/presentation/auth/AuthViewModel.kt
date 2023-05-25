package com.example.nanopost.presentation.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nanopost.data.models.CheckUsernameResult
import com.example.nanopost.data.models.request.RegistrationRequest
import com.example.nanopost.data.models.response.TokenResponse
import com.example.nanopost.domain.auth.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val checkUsernameUseCase: CheckUsernameUseCase,
    private val authorizeUseCase: AuthorizeUseCase,
    private val registerUseCase: RegisterUseCase,
    private val addTokenUseCase: AddTokenUseCase,
    private val addUserIdUserCase: AddUserIdUserCase,
): ViewModel()  {

    private val _checkUsernameLiveData = MutableLiveData<CheckUsernameResult?>()
    val checkUsernameLiveData: LiveData<CheckUsernameResult?> = _checkUsernameLiveData

    private val _authLiveData = MutableLiveData<TokenResponse?>()
    val authLiveData: LiveData<TokenResponse?> = _authLiveData

    private val _errLiveData = MutableLiveData<Exception>()
    val errLiveData: LiveData<Exception> = _errLiveData

    fun authorize(username: String, password: String){
            //try {
        viewModelScope.launch(Dispatchers.IO) {
            _authLiveData.postValue(authorizeUseCase(username, password))
        }
                _authLiveData.value?.let { addTokenUseCase(it.token) }
                _authLiveData.value?.let{ addUserIdUserCase(it.userId)}
           /* }
            catch (e: HttpException){
                _errLiveData.postValue(e)
                throw e
            }*/

    }
    fun register(username: String, password: String){
        //try {
        viewModelScope.launch(Dispatchers.IO) {
            _authLiveData.postValue(registerUseCase(username, password))

        }
        _authLiveData.value?.let { addTokenUseCase(it.token) }
        _authLiveData.value?.let{ addUserIdUserCase(it.userId)}

        //}
        /*catch (e: HttpException){
            _errLiveData.postValue(e)
            throw e
        }*/
    }

    fun checkUsername(username: String){
        viewModelScope.launch(Dispatchers.IO) {
           _checkUsernameLiveData.postValue(checkUsernameUseCase(username))
        }
    }
}