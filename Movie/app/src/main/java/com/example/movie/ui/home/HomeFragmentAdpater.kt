package com.example.movie.ui.home

import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.model.discovermodel.ResultsItem
import com.example.movie.R
import com.example.movie.databinding.HomeListItemBinding
import com.example.movie.extensions.bind

class HomeFragmentAdpater: RecyclerView.Adapter<HomeFragmentAdpater.HomeFragmentViewHolder>() {

    var titles: List<ResultsItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeFragmentViewHolder
            = HomeFragmentViewHolder(parent.bind(R.layout.home_list_item))

    override fun getItemCount(): Int
            = titles.size

    override fun onBindViewHolder(holder: HomeFragmentViewHolder, position: Int) {
        val resultsItem = titles[position]
        holder.binding.discoverItem = resultsItem


        holder.binding.constraintLaunchHistoryItem.setOnClickListener(View.OnClickListener {

            val action: NavDirections = HomeFragmentDirections
                .actionNavHomeToDetailsFragment(resultsItem)



            Navigation.findNavController(it).navigate(action)

        })

    }

    fun updateDiscoverMovie(titles: List<ResultsItem>){
        this.titles = titles
        notifyDataSetChanged()
    }

    class HomeFragmentViewHolder(val binding: HomeListItemBinding) : RecyclerView.ViewHolder(binding.root){

    }
}