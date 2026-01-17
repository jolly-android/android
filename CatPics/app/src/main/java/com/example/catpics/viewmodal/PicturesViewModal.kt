package com.verkada.android.catpictures.viewmodal

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.verkada.android.catpictures.data.Picture
import kotlinx.coroutines.launch
import retrofit2.Retrofit

class PicturesViewModal : ViewModel() {
    /*private val _picture = mutableStateListOf<Picture>()
    val pictures:List<Picture> = _picture

    private val currentPage = 1

    init{
        loadPictures()
    }

    fun loadPictures() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.pictures(
                    page = currentPage
                )
                _picture.addAll(response)
                currentPage++
            } catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
     */
}