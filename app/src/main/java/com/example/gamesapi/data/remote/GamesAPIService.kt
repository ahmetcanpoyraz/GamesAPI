package com.example.gamesapi.data.remote

import com.example.gamesapi.model.GamesModel
import com.example.gamesapi.util.Constants
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class GamesAPIService {

    private val api = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(GamesAPI::class.java)

    fun getData() : Single<GamesModel>{
        return  api.getGames()
    }

}