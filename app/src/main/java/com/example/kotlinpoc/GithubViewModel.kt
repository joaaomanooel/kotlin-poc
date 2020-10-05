package com.example.kotlinpoc

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GithubViewModel: ViewModel() {
    private val repositories: MutableLiveData<List<Repository>> by lazy {
        MutableLiveData<List<Repository>>()
    }

    fun getRepositories(): LiveData<List<Repository>> {
        return repositories
    }

    private suspend fun loadRepositories(){
        val githubService = GithubFactory.makeRetrofitService()
        val response = githubService.getRepos()

        repositories.value = response.body()
    }
}