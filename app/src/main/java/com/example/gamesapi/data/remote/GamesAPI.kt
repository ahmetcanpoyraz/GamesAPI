package com.example.gamesapi.data.remote

import com.example.gamesapi.model.GamesModel
import com.example.gamesapi.util.Constants
import io.reactivex.Single
import retrofit2.http.GET

interface GamesAPI {
    @GET("games?key=${Constants.APIKey}")
    fun getGames(): Single<GamesModel>
}