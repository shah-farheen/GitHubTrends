package com.buggyarts.hubtrends.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
    abstract fun makeApiCall()

    lateinit var repositoryComponent: RepositoryComponent

    private var isSavedState = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        repositoryComponent = DaggerRepositoryComponent.builder()
            .repositoryModule(RepositoryModule())
            .applicationComponent((requireActivity().application as HubTrendsApp).applicationComponent)
            .build()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupDependencies()
        initViews()
        initListeners()
        initObservers()
        savedInstanceState?.let {
            isSavedState = it.getBoolean(IS_SAVED_STATE, false)
        }
        if(!isSavedState) makeApiCall()
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(IS_SAVED_STATE, true)
    }

    protected fun showShortToast(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val IS_SAVED_STATE = "IS_SAVED_STATE"
    }
}