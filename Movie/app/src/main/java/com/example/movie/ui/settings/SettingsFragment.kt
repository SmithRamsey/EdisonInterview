package com.example.movie.ui.settings

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate

import com.example.movie.R



class SettingsFragment : Fragment() {

    private lateinit var switch: Switch

    public var switchState: Boolean? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v = inflater.inflate(R.layout.fragment_settings, container, false)

        switch = v.findViewById(R.id.switchMaterial)





        if(AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES){
            switch.isChecked = true
        }



        switch.setOnCheckedChangeListener { buttonView, isChecked ->

            if(isChecked){
                switchState = true
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else{
                switchState = false
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }

        }


        return v
    }

    override fun onSaveInstanceState(outState: Bundle) {

        switchState?.let { outState.putBoolean("SWITCH_STATE", it) }
        super.onSaveInstanceState(outState)
    }

}
