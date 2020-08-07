package com.example.movie.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import com.example.movie.model.discovermodel.DiscoverResponse
import com.example.movie.model.searchmodel.SearchResponse
import com.example.movie.repository.Repository
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class ViewModel(application: Application) : AndroidViewModel(application) {

    private val _discoverResponse = MutableLiveData<DiscoverResponse>()
    val discoverResponse : LiveData<DiscoverResponse> = _discoverResponse

    private val _loadingStateDiscover = MutableLiveData<Boolean>()
    val loadingStateDiscover : LiveData<Boolean> = _loadingStateDiscover

    private val _searchResponse = MutableLiveData<SearchResponse>()
    val searchResponse : LiveData<SearchResponse> = _searchResponse

    private val _loadingStateSearch = MutableLiveData<Boolean>()
    val loadingStateSearch : LiveData<Boolean> = _loadingStateSearch

    private val TAG:String = "ViewModel"

    fun getSearchMovie(query: String){
        Repository.getSearchMovie(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<SearchResponse> {
                override fun onComplete() {
                    _loadingStateSearch.value = false
                }

                override fun onSubscribe(d: Disposable) {
                    _loadingStateSearch.value = true
                }

                override fun onNext(t: SearchResponse) {
                    _searchResponse.value = t
                }

                override fun onError(e: Throwable) {
                    Log.d(TAG, "getSearchMovie(): onError$e")
                }

            })
    }

    fun getDiscoverMovie(page: String){
        Repository.getDiscoverMovie(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<DiscoverResponse>{
                override fun onComplete() {
                    _loadingStateDiscover.value = false
                }

                override fun onSubscribe(d: Disposable) {
                    _loadingStateDiscover.value = true
                }

                override fun onNext(t: DiscoverResponse) {
                    _discoverResponse.value = t
                }

                override fun onError(e: Throwable) {
                    Log.d(TAG, "getDiscoverMovie(): onError$e")
                }
            })
    }

}