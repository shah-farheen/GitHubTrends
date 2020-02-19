package com.buggyarts.hubtrends.di.component

import com.buggyarts.hubtrends.di.ViewModelScope
import com.buggyarts.hubtrends.di.module.RepositoryModule
import com.buggyarts.hubtrends.ui.main.MainViewModel
import dagger.Component

@ViewModelScope
@Component(modules = [RepositoryModule::class], dependencies = [ApplicationComponent::class])
interface RepositoryComponent {

    fun inject(mainViewModel: MainViewModel)
}