package com.example.movie.extensions

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("loadGenre")
fun loadGenre(view: TextView, ids: List<Int>){

    var j = String()

    for(i in ids.indices){

        when(ids[i]){

            28 ->    j += "Action"
            12 ->    j += "Adventure"
            16 ->    j += "Animation"
            35 ->    j += "Comedy"
            80 ->    j += "Crime"
            99 ->    j += "Documentary"
            18 ->    j += "Drama"
            10751 -> j += "Family"
            14 ->    j += "Fantasy"
            36 ->    j += "History"
            27 ->    j += "Horror"
            10402 -> j += "Music"
            9648 ->  j += "Mystery"
            10749 -> j += "Romance"
            878 ->   j += "Science Fiction"
            10770 -> j += "TV Movie"
            53 ->    j += "Thriller"
            10752 -> j += "War"
            37 ->    j += "Western"

        }

        j += when(i){
            ids.size -1 -> "."
            ids.size -2 -> ", and "
            else -> ", "

        }


    }
    view.text = "Genre: $j"

}