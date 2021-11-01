package com.example.Mandi.api

import com.example.Mandi.model.Mandi
import retrofit2.Response
import retrofit2.http.GET


interface ApiService {

    @GET("test/mandi?lat=28.44108136&lon=77.0526054&ver=89&lang=hi&crop_id=10")
    suspend fun getothermandi() : Response<Mandi>
}