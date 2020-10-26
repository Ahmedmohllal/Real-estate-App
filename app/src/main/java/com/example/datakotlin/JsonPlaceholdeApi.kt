package com.example.datakotlin

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface JsonPlaceholdeApi {

    @GET("realestate")
     fun getPost() : Call<List<RealState>>
}