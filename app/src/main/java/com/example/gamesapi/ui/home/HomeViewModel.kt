package com.example.gamesapi.ui.home

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.gamesapi.BaseViewModel
import com.example.gamesapi.data.local.GameDatabase
import com.example.gamesapi.data.remote.GamesAPIService
import com.example.gamesapi.model.GamesModel
import com.example.gamesapi.model.Results
import com.example.gamesapi.util.CustomSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : BaseViewModel(application) {
    private val gameApiService = GamesAPIService()
    private val disposable = CompositeDisposable()
    private var customPreferences = CustomSharedPreferences(getApplication())
    private var refreshTime = 10 * 60 * 1000 * 1000 * 1000L

    val games = MutableLiveData<List<Results>>()
    val gameError = MutableLiveData<Boolean>()
    val gameLoading = MutableLiveData<Boolean>()

    fun refreshData(){
        val updateTime = customPreferences.getTime()
        if(updateTime != null && updateTime != 0L && System.nanoTime() - updateTime < refreshTime){
            getDataFromSQLite()
        }
        else {
            getDataFromAPI()
        }
        getDataFromAPI()
    }

    fun refreshFromAPI(){
        getDataFromAPI()
    }

    private fun getDataFromSQLite(){
        gameLoading.value = true
        launch {
            val games = GameDatabase(getApplication()).gameDao().getAllGames()
            showGames(games)
        }
    }

    private fun getDataFromAPI(){
        gameLoading.value = true

        disposable.add(
            gameApiService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<GamesModel>(){
                    override fun onSuccess(t: GamesModel) {
                        storeInSQLite(t.results)
                    }

                    override fun onError(e: Throwable) {
                        gameLoading.value = false
                        gameError.value = true
                        e.printStackTrace()
                    }

                })
        )
    }

    private fun showGames(list: List<Results>){
        games.value = list
        gameError.value = false
        gameLoading.value = false
    }

    private fun storeInSQLite(list: List<Results>){
        launch {
            val dao = GameDatabase(getApplication()).gameDao()
            val listLong = dao.insertAll(*list.toTypedArray())
            var i = 0
            while (i < list.size){
                list[i].uuid = listLong[i].toInt()
                i = i + 1
            }
            showGames(list)
        }

        customPreferences.saveTime(System.nanoTime())
    }


    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }




}