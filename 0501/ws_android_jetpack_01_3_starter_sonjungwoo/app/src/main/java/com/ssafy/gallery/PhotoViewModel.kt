package com.ssafy.gallery

import androidx.lifecycle.ViewModel

class PhotoViewModel : ViewModel() {
    var photos = mutableListOf<Photo>()
        private set

    fun initPhotos(list: MutableList<Photo>) {
        photos = list
    }

}