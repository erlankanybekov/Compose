package com.example.compose

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.compose.data.Image

import com.example.compose.data.ImageApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor (
    private val api: ImageApi
        ):ViewModel(){

    private val _state = mutableStateOf(ImageState())
    val state: State<ImageState> = _state

    init {
        getRandomImage()
    }

    fun getRandomImage(){
        viewModelScope.launch {
            try {
                _state.value = state.value.copy(isLoading = true)
                _state.value = state.value.copy(
                    image = api.getRandomImage(),
                    isLoading = false
                )
            }catch (e:Exception){
                Log.e("MainViewModel","getRandImg -> ",e)
                _state.value = state.value.copy(isLoading = false)
            }
        }
    }

    data class ImageState(
        val image: Image? = null,
        val isLoading:Boolean = false
    )
}