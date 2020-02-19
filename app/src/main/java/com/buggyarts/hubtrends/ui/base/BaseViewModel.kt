package com.buggyarts.hubtrends.ui.base

import androidx.lifecycle.ViewModel
import com.buggyarts.hubtrends.di.component.RepositoryComponent

abstract class BaseViewModel : ViewModel() {

    abstract fun initRepository(repositoryComponent: RepositoryComponent)
}