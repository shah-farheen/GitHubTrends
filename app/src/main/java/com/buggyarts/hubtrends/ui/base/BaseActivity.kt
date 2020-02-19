package com.buggyarts.hubtrends.ui.base

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.buggyarts.hubtrends.HubTrendsApp
import com.buggyarts.hubtrends.di.component.DaggerRepositoryComponent
import com.buggyarts.hubtrends.di.component.RepositoryComponent
import com.buggyarts.hubtrends.di.module.RepositoryModule

abstract class BaseActivity : AppCompatActivity() {

    abstract fun setupDependencies()
    abstract fun initViews()
    abstract fun initListeners()
    abstract fun initObservers()

    lateinit var repositoryComponent: RepositoryComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        repositoryComponent = DaggerRepositoryComponent.builder()
            .repositoryModule(RepositoryModule())
            .applicationComponent((application as HubTrendsApp).applicationComponent)
            .build()
        setupDependencies()
        initViews()
        initListeners()
        initObservers()
    }

    protected fun showShortToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}