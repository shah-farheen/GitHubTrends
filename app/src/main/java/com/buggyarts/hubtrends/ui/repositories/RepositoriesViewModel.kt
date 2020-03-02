package com.buggyarts.hubtrends.ui.repositories

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.buggyarts.hubtrends.data.model.repositories.GitHubRepo
import com.buggyarts.hubtrends.di.component.RepositoryComponent
import com.buggyarts.hubtrends.repo.Repository
import com.buggyarts.hubtrends.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class RepositoriesViewModel : BaseViewModel() {

    val repos: MutableLiveData<List<GitHubRepo>> = MutableLiveData()
    val toShowErrorState: MutableLiveData<Boolean> = MutableLiveData(false)

    override fun initRepository(repositoryComponent: RepositoryComponent) {
        repositoryComponent.inject(this)
    }

    @Inject
    lateinit var repository: Repository

    fun getRepos() {
        viewModelScope.launch {
            handleRemoteRepos(repository.getReposFromRemote())
        }
    }

    private fun handleRemoteRepos(remoteRepos: List<GitHubRepo>?) {
        if (remoteRepos == null || remoteRepos.isEmpty()) {
            toShowErrorState.value = true
        } else {
            toShowErrorState.value = false
            repos.value = remoteRepos
        }
    }
}