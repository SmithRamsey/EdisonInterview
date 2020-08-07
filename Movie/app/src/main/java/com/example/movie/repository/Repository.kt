package com.example.movie.repository

import com.example.movie.model.discovermodel.DiscoverResponse
import com.example.movie.model.searchmodel.SearchResponse
import com.example.movie.utilities.MovieService
import io.reactivex.Observable

object Repository {

    fun getDiscoverMovie(page: String): Observable<DiscoverResponse> =
        MovieService.getInstance().getMovieDiscover(page)

    fun getSearchMovie(query: String): Observable<SearchResponse> =
        MovieService.getInstance().getMovieSearch(query)
}