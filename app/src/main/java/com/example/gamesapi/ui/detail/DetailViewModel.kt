package com.example.gamesapi.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gamesapi.model.Results

class DetailViewModel : ViewModel() {

    val gameLiveData = MutableLiveData<Results>()
    fun getDataFromRoom(){
        val game = Results(1,"","name","232",true,"dsa",2.2,2,2,"23,",2,2,2,2,"asda")
        gameLiveData.value = game

    }
}