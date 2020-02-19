package com.buggyarts.hubtrends.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.PopupMenu
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.buggyarts.hubtrends.R
import com.buggyarts.hubtrends.data.model.repositories.GitHubRepo
import com.buggyarts.hubtrends.databinding.ActivityMainBinding
import com.buggyarts.hubtrends.ui.base.BaseActivity

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var repoAdapter: RepoAdapter
    private lateinit var popupMenu: PopupMenu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getRepos()
        binding.pbLoading.visibility = View.VISIBLE
    }

    /**
     * Set binding and viewModels here as this is the first thing executed in onCreate()
     */
    override fun setupDependencies() {
        binding = DataBindingUtil
            .setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProviders.of(this)[MainViewModel::class.java]
        viewModel.initRepository(repositoryComponent)
    }

    override fun initViews() {
        // init recycler with Adapter
        repoAdapter = RepoAdapter(ArrayList(), this)
        binding.rvRepos.adapter = repoAdapter

        // init PopupMenu
        popupMenu = PopupMenu(this, binding.ivThreeDots)
        popupMenu.inflate(R.menu.menu_main)
    }

    override fun initListeners() {
        popupMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menu_item_stars -> repoAdapter.sortByStars()
                R.id.menu_item_name -> repoAdapter.sortByName()
            }
            return@setOnMenuItemClickListener true
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getReposFromRemote()
        }

        binding.tvRetry.setOnClickListener {
            viewModel.getRepos()
        }

        binding.ivThreeDots.setOnClickListener {
            popupMenu.show()
        }
    }

    override fun initObservers() {
        viewModel.repos.observe(this,
            Observer<List<GitHubRepo>> {
                binding.swipeRefreshLayout.isRefreshing = false
                binding.pbLoading.visibility = View.GONE
                if (it != null) {
                    repoAdapter.clearRepos()
                    repoAdapter.addRepos(it as ArrayList<GitHubRepo>)
                }
            })

        viewModel.toShowErrorState.observe(this,
            Observer<Boolean> {
                showErrorState(it)
            })

        viewModel.toShowRemoteFetchError.observe(this,
            Observer<Boolean> {
                if (it) {
                    showShortToast(getString(R.string.remote_fetch_error))
                }
            })
    }

    private fun showErrorState(toShow: Boolean) {
        if (toShow) {
            repoAdapter.clearRepos()
            binding.layoutNoConnection.visibility = View.VISIBLE
            binding.tvRetry.visibility = View.VISIBLE
        } else {
            binding.layoutNoConnection.visibility = View.GONE
            binding.tvRetry.visibility = View.GONE
        }
    }
}
