package com.example.movie.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewDebug
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.R
import com.example.movie.viewmodel.ViewModel
import com.example.movie.databinding.FragmentHomeBinding
import com.example.movie.extensions.showLoader
import com.example.movie.model.discovermodel.ResultsItem
import com.example.movie.utilities.RecyclerViewLoadMoreScroll
import kotlinx.android.synthetic.main.movie_load_shimmer_item.*

class HomeFragment : Fragment() {


    private val TAG:String = "HomeFragment"



    private val viewModel: ViewModel by lazy {
        ViewModelProvider.AndroidViewModelFactory
            .getInstance(requireActivity().application)
            .create(ViewModel::class.java)
    }



    private var adapter: HomeFragmentAdpater? = null
    var mListResultsItem = ArrayList<ResultsItem>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentHomeBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_home,container,false)

        var offset = 1
        viewModel.getDiscoverMovie(offset.toString())

        binding.rvHome.layoutManager = LinearLayoutManager(requireContext())

        adapter = HomeFragmentAdpater()

        binding.rvHome.adapter = adapter



        binding.rvHome.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if(!recyclerView.canScrollVertically(1)){
                    offset += 1

                    Log.d(TAG, "ScrollListener: $offset")

                    viewModel.getDiscoverMovie(offset.toString())

                    Toast.makeText(context,"Scroll Reached",Toast.LENGTH_SHORT).show()
                }

            }

        })





        return binding.root
    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




    }

    override fun onStart() {
        super.onStart()
        setUpObservers()
    }


    private fun setUpObservers(){
        viewModel.discoverResponse.observe(this, Observer { t ->
            Log.d(TAG, "setUpObservers(): $t")



            mListResultsItem.addAll(t.results as List<ResultsItem>)

            adapter?.updateDiscoverMovie(mListResultsItem)

        })

        viewModel.loadingStateDiscover.observe(this, Observer { b ->


        })

    }
}