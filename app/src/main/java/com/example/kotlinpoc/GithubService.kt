package com.example.kotlinpoc

import retrofit2.Response
import retrofit2.http.GET

data class Repository (
    val name: String,
    val html_url: String
)

interface GithubService {
    @GET("/users/joaaomanooel/repos")
    suspend fun getRepos(): Response<List<Repository>>
}
