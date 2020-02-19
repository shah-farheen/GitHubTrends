package com.buggyarts.hubtrends.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.buggyarts.hubtrends.data.model.repositories.GitHubRepo
import com.buggyarts.hubtrends.di.component.RepositoryComponent
import com.buggyarts.hubtrends.repo.Repository
import com.buggyarts.hubtrends.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel : BaseViewModel() {

    val repos: MutableLiveData<List<GitHubRepo>> = MutableLiveData()
    val toShowErrorState: MutableLiveData<Boolean> = MutableLiveData(false)
    val toShowRemoteFetchError: MutableLiveData<Boolean> = MutableLiveData(false)

    override fun initRepository(repositoryComponent: RepositoryComponent) {
        repositoryComponent.inject(this)
    }

    @Inject
    lateinit var repository: Repository

    fun getRepos() {
        viewModelScope.launch {
            if (repository.shouldUpdateFromRemote()) {
                handleRemoteRepos(repository.getReposFromRemote())
            } else {
                toShowRemoteFetchError.value = false
//                handleLocalRepos(repository.getReposFromLocal())
            }
        }
    }

    fun getReposFromRemote() {
        viewModelScope.launch {
            handleRemoteRepos(repository.getReposFromRemote())
        }
    }

    private suspend fun handleRemoteRepos(remoteRepos: List<GitHubRepo>?) {
        if (remoteRepos == null) {
            toShowRemoteFetchError.value = true
//            handleLocalRepos(repository.getReposFromLocal())
        } else {
            toShowRemoteFetchError.value = false
            toShowErrorState.value = false
            repos.value = remoteRepos
        }
    }

    private fun handleLocalRepos(localRepos: List<GitHubRepo>?) {
        if (localRepos == null || localRepos.isEmpty()) {
            toShowErrorState.value = true
            repos.value = null
        } else {
            toShowErrorState.value = false
            repos.value = localRepos
        }
    }
}