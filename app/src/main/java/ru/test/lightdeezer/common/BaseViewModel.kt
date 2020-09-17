package ru.test.lightdeezer.common

import androidx.lifecycle.ViewModel
import ru.test.lightdeezer.data.network.NetworkModule
import ru.test.lightdeezer.injection.DaggerViewModelInjector
import ru.test.lightdeezer.injection.ViewModelInjector
import ru.test.lightdeezer.ui.playlist.PLaylistsViewModel
import ru.test.lightdeezer.ui.playlist.detail.DetailActivityViewModel

abstract class BaseViewModel: ViewModel(){
    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    private fun inject() {
        when (this) {
            is PLaylistsViewModel -> injector.inject(this)
            is DetailActivityViewModel -> injector.inject(this)
        }
    }
}