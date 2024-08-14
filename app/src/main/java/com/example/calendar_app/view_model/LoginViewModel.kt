package com.example.calendar_app.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.domain.i_repository.IUserRepository
import com.domain.model.user.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import okhttp3.internal.wait
import retrofit2.Retrofit
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
@Inject constructor(val retrofit: Retrofit)
    : ViewModel() {
    private val _loginResult = MutableLiveData<User>()
    val loginResult: LiveData<User> = _loginResult
//
    fun login(username: String, password: String) = viewModelScope.launch {
//        _loginResult.value = iUserRepository.authentication(username, password)
    _loginResult.value = User("", "", "")
}
}