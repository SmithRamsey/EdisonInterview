package com.example.movie.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs

import com.example.movie.R
import com.example.movie.databinding.FragmentDetailsBinding

/**
 * A simple [Fragment] subclass.
 */
class DetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentDetailsBinding = DataBindingUtil.inflate(
            inflater,R.layout.fragment_details, container,false
        )

        val args: DetailsFragmentArgs by navArgs()

        binding.discoverItem = args.discoverResultItem


        return binding.root
    }



}
