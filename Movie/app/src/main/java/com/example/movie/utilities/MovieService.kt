package com.example.movie.utilities

import com.example.movie.model.discovermodel.DiscoverResponse
import com.example.movie.model.searchmodel.SearchResponse
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET(MOVIE_DISCOVER_PATH)
    fun getMovieDiscover(
        @Query("page") page: String,
        @Query("api_key") apiKey: String = API_Key,
        @Query("sort_by") sortBy: String = SORT_BY
    ): Observable<DiscoverResponse>

    @GET(MOVIE_SEARCH_PATH)
    fun getMovieSearch(
        @Query("query") query: String,
        @Query("api_key") apiKey: String = API_Key
    ): Observable<SearchResponse>

    companion object {
        const val API_Key = "21332209b5f92ac3315a3bf5180c38d7"
        const val MOVIE_DISCOVER_PATH = "discover/movie"
        const val SORT_BY = "popularity.desc"
        const val MOVIE_SEARCH_PATH = "search/movie"
        private const val BASE_URL = "https://api.themoviedb.org/3/"

         var movieService: MovieService? = null

        fun getInstance() : MovieService{
            if (movieService == null) {
                val retrofit =
                    Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build()
                movieService = retrofit.create(MovieService::class.java)
            }
            return movieService!!

        }
    }


}
