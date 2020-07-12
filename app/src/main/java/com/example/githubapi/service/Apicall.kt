package com.example.githubapi.service

import com.example.githubapi.model.GithubResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Apicall {
    companion object {
        const val BASE_URL = "https://api.github.com"
    }

    @GET("/users/{user_name}/repos")
    fun getUserRepo(@Path("user_name") user_name: String): Call<MutableList<GithubResponse>>
}