package com.dicoding.picodiploma.loginwithanimation.view.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.picodiploma.loginwithanimation.data.pref.UserModel
import com.dicoding.picodiploma.loginwithanimation.data.UserRepository
import com.dicoding.picodiploma.loginwithanimation.data.response.LoginResponse
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.HttpException

class LoginViewModel(private val repository: UserRepository) : ViewModel() {
    fun saveSession(user: UserModel) {
        viewModelScope.launch {
            repository.saveSession(user)
        }
    }

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isError = MutableLiveData<String?>()
    val isError: MutableLiveData<String?> get() = _isError

    private val _loginResponse = MutableLiveData<LoginResponse>()
    val loginResponse: LiveData<LoginResponse> = _loginResponse

    fun login(email: String, password: String) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = repository.login(email, password)
                val loginResult = response.loginResult

                // Check for null values and handle appropriately
                val userId = loginResult?.email ?: ""
                val name = loginResult?.password ?: ""
                val token = loginResult?.accessToken ?: ""

                val user = UserModel(
                    userId = userId,
                    name = name,
                    email = email,
                    token = token,
                    isLogin = true
                )
                setAuth(user)
                _loginResponse.postValue(response)
            } catch (e: HttpException) {
                handleHttpException(e)
            } catch (e: Exception) {
                handleGeneralException(e)
            } finally {
                _isLoading.postValue(false)
            }
        }
    }


    private fun handleHttpException(e: HttpException) {
        val jsonInString = e.response()?.errorBody()?.string()
        val errorBody = Gson().fromJson(jsonInString, LoginResponse::class.java)
        _isError.postValue(errorBody.message)
    }

    private fun handleGeneralException(e: Exception) {
        _isError.postValue(e.message ?: "An unexpected error occurred")
    }

    private fun setAuth(userModel: UserModel) {
        viewModelScope.launch {
            repository.setAuth(userModel)
        }
    }

}