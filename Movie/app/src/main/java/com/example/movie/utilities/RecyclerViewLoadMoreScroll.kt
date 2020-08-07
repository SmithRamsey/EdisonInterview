package com.example.movie.utilities

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewLoadMoreScroll : RecyclerView.OnScrollListener {

    private  var visibleThreshold = 3
    private lateinit var mOnLoadMoreListener: onLoadMoreListener
    private var isLoading: Boolean = false
    private var lastVisibleItem: Int = 0
    private var totalItemCount: Int = 0
    private var mLayoutManager: RecyclerView.LayoutManager

    fun setLoaded(){
        isLoading = false
    }

    fun getLoaded(): Boolean{
        return isLoading
    }

    fun setOnLoadMoreListener(mOnLoadMoreListener: onLoadMoreListener){
        this.mOnLoadMoreListener = mOnLoadMoreListener
    }

    constructor(layoutManager: LinearLayoutManager){
        this.mLayoutManager = layoutManager
    }


    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)


        if(dy <= 0) return

        totalItemCount = mLayoutManager.itemCount

        lastVisibleItem = (mLayoutManager as LinearLayoutManager).findLastVisibleItemPosition()

        if(!isLoading && totalItemCount <= lastVisibleItem + visibleThreshold){
            mOnLoadMoreListener.onLoadMore()
            isLoading = true
        }
    }

    private fun getLastVisibleItem(lastVisibleItemPositions: IntArray): Int{
        var maxSize = 0

        for(i in lastVisibleItemPositions.indices){
            if (i==0){
                maxSize = lastVisibleItemPositions[i]
            }else if(lastVisibleItemPositions[i]> maxSize){
                maxSize = lastVisibleItemPositions[i]
            }
        }

        return maxSize
    }

}