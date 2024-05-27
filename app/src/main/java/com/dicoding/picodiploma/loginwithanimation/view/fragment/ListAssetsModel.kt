package com.dicoding.picodiploma.loginwithanimation.view.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.picodiploma.loginwithanimation.data.UserRepository
import com.dicoding.picodiploma.loginwithanimation.data.response.ListStoryItem
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import retrofit2.HttpException

class ListAssetsModel(private val repository: UserRepository) : ViewModel() {

    private val _storyList = MutableLiveData<List<ListStoryItem>>()
    val storyList: LiveData<List<ListStoryItem>> get() = _storyList

    private val _isLoading = MutableLiveData<Boolean>()

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> get() = _message

    fun getAllStory() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val token = repository.getSession().first().token
                val storyResponse = repository.getAssets("Bearer $token")
                val message = storyResponse.message

                val nonNullStoryList = storyResponse.listStory?.filterNotNull() ?: emptyList()

                _storyList.value = nonNullStoryList
                _message.value = message ?: "Unknown Message"
            } catch (e: HttpException) {
                val jsonInString = e.response()?.errorBody()?.string()
//                val errorBody = Gson().fromJson(jsonInString, AddStoryGuestResponse::class.java)
                val errorMessage = ("Unknown Error")
                _message.value = errorMessage ?: "Unknown Error"
            } catch (e: Exception) {
                _message.value = e.message ?: "Unknown Error"
            } finally {
                _isLoading.value = false
            }
        }
    }
}