package ru.test.lightdeezer.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.test.lightdeezer.ui.playlist.detail.DetailActivityViewModel


class DetailFactory(id: String) :
    ViewModelProvider.NewInstanceFactory() {
    private val mId: String

    init {
        mId = id
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailActivityViewModel(mId) as T

    }
}