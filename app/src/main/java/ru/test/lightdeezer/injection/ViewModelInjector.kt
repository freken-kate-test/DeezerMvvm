package ru.test.lightdeezer.injection

import dagger.Component
import ru.test.lightdeezer.data.network.NetworkModule
import ru.test.lightdeezer.ui.playlist.PLaylistsViewModel
import ru.test.lightdeezer.ui.playlist.detail.DetailActivityViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {
    fun inject(trackViewModel: PLaylistsViewModel)
    fun inject(detailViewModel: DetailActivityViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}