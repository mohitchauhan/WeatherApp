package com.android.xu.weather.home

import androidx.lifecycle.ViewModel
import com.android.xu.weather.inject.ViewModelBuilder
import com.android.xu.weather.inject.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class HomeBuilder {
    @ContributesAndroidInjector(modules = [ViewModelBuilder::class])
    internal abstract fun contributeHomeActivity(): HomeActivity

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel
}