package com.example.Mandi

import android.app.Application
import com.example.Mandi.api.ApiService
import com.example.Mandi.api.MyRetrofitBuilder
import com.example.Mandi.db.MDatabase
import com.example.Mandi.db.fDatabase
import com.example.Mandi.repository.FRepository

class FApplication: Application() {

    lateinit var  fRepository: FRepository

    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
        val apiService = MyRetrofitBuilder.getinstance().create(ApiService::class.java)
        val fDatabase = fDatabase.getDatabase(applicationContext)
        val mDatabase = MDatabase.getDatabase(applicationContext)
        fRepository = FRepository(fDatabase, apiService, mDatabase, applicationContext)
    }

}