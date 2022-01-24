package com.virtusa.assignment

import com.virtusa.assignment.data.ApiRespoModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {

    @GET("api/users/{input}")
    suspend fun getUserDetails(@Path("input") input: String): Response<ApiRespoModel>
}