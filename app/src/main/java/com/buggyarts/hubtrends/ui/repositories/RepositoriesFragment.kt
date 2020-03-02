package com.buggyarts.hubtrends.ui.repositories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.buggyarts.hubtrends.R
import com.buggyarts.hubtrends.data.model.repositories.GitHubRepo
import com.buggyarts.hubtrends.databinding.FragmentRepositoriesBinding
import com.buggyarts.hubtrends.ui.base.BaseFragment
import com.buggyarts.hubtrends.ui.main.RepoAdapter

class RepositoriesFragment : BaseFragment() {

    private lateinit var binding: FragmentRepositoriesBinding
    private lateinit var viewModel: RepositoriesViewModel
    private lateinit var repoAdapter: RepoAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_repositories, container, false)
        return binding.root
    }

    override fun setupDependencies() {
        viewModel = ViewModelProviders.of(this)[RepositoriesViewModel::class.java]
        viewModel.initRepository(repositoryComponent)
    }

    override fun initViews() {
        binding.pbLoading.visibility = View.VISIBLE

        // init recycler with Adapter
        repoAdapter = RepoAdapter(ArrayList(), requireContext())
        binding.rvRepos.adapter = repoAdapter
    }

    override fun initListeners() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getRepos()
        }

        binding.tvRetry.setOnClickListener {
            viewModel.getRepos()
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
    }

    override fun makeApiCall() {
        viewModel.getRepos()
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

    companion object {
        fun newInstance() = RepositoriesFragment()
    }
}
