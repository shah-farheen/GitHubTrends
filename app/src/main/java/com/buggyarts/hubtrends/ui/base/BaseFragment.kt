package com.buggyarts.hubtrends.ui.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.buggyarts.hubtrends.HubTrendsApp
import com.buggyarts.hubtrends.di.component.DaggerRepositoryComponent
import com.buggyarts.hubtrends.di.component.RepositoryComponent
import com.buggyarts.hubtrends.di.module.RepositoryModule

abstract class BaseFragment : Fragment() {

    abstract fun setupDependencies()
    abstract fun initViews()
    abstract fun initListeners()
    abstract fun initObservers()

    lateinit var repositoryComponent: RepositoryComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        repositoryComponent = DaggerRepositoryComponent.builder()
            .repositoryModule(RepositoryModule())
            .applicationComponent((requireActivity().application as HubTrendsApp).applicationComponent)
            .build()
        setupDependencies()
        initViews()
        initListeners()
        initObservers()
    }
}