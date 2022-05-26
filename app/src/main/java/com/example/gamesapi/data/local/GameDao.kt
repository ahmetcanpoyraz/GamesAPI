package com.example.gamesapi.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.gamesapi.model.Results

@Dao
interface GameDao {

    @Insert
    suspend fun insertAll(vararg games: Results) : List<Long>

    @Query("SELECT * FROM results")
    suspend fun getAllGames() : List<Results>

    @Query("SELECT * FROM results WHERE uuid = :gameId")
    suspend fun getGame(gameId : Int) : Results

    @Query("DELETE FROM results")
    suspend fun deleteAllGames()

}