package com.example.gamesapi.ui.detail

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.gamesapi.BaseViewModel
import com.example.gamesapi.data.local.GameDatabase
import com.example.gamesapi.model.Results
import kotlinx.coroutines.launch
import java.util.*

class DetailViewModel(application: Application) : BaseViewModel(application) {

    val gameLiveData = MutableLiveData<Results>()
    fun getDataFromRoom(uuid: Int){
            launch {
                val dao = GameDatabase(getApplication()).gameDao()
                val game = dao.getGame(uuid)
                gameLiveData.value = game
            }
    }
}