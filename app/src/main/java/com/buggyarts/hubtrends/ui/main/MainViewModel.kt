package com.buggyarts.hubtrends.ui.main

import com.buggyarts.hubtrends.di.component.RepositoryComponent
import com.buggyarts.hubtrends.ui.base.BaseViewModel

class MainViewModel : BaseViewModel() {

    override fun initRepository(repositoryComponent: RepositoryComponent) {
        repositoryComponent.inject(this)
    }
}