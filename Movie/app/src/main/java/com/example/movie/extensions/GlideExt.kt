package com.example.movie.extensions


import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import kotlinx.android.synthetic.main.home_list_item.view.*
import kotlinx.android.synthetic.main.progress_loading.view.*

@BindingAdapter("loadImage")
fun loadImage(view: ImageView, url: String){
    Glide.with(view.context)
        .load(url)
        .transform(RoundedCorners(10))
        .into(view)

}