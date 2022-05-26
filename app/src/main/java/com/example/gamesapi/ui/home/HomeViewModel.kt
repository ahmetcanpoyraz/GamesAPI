package com.example.gamesapi.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gamesapi.data.remote.GamesAPIService
import com.example.gamesapi.model.GamesModel
import com.example.gamesapi.model.Results
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class HomeViewModel : ViewModel() {
    private val gameApiService = GamesAPIService()
    private val disposable = CompositeDisposable()

    val games = MutableLiveData<List<Results>>()
    val gameError = MutableLiveData<Boolean>()
    val gameLoading = MutableLiveData<Boolean>()

    fun refreshData(){
        getDataFromAPI()
    }

    private fun getDataFromAPI(){
        gameLoading.value = true

        disposable.add(
            gameApiService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<GamesModel>(){
                    override fun onSuccess(t: GamesModel) {
                        games.value = t.results
                        gameError.value = false
                        gameLoading.value = false
                    }

                    override fun onError(e: Throwable) {
                        gameLoading.value = false
                        gameError.value = true
                        e.printStackTrace()
                    }

                })
        )
    }

}