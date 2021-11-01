package com.example.Mandi

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.Mandi.ViewModel.MainViewModel
import com.example.Mandi.ViewModel.MainViewModelFactory


class MandiActivity : AppCompatActivity() {

    public lateinit var adapter: MandiAdapter
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mandi_activity)

        val mandiview = findViewById<RecyclerView>(R.id.mandiview)
        val repository = (application as FApplication).fRepository

        mainViewModel = ViewModelProvider(this, MainViewModelFactory(repository)).get(MainViewModel::class.java)
//        mainViewModel.JLive.observe(this, {
//            adapter = MandiAdapter(this, it.data.other_mandi)
//
//            mandiview.adapter = adapter
//            mandiview.layoutManager = LinearLayoutManager(this)
//        })
        val fetch = findViewById<Button>(R.id.fetch)
        fetch.setOnClickListener {
            mainViewModel.insertdetails(this)
        }

        mainViewModel.getchdetails(this)!!.observe(this,{
            if(it != null) {
                adapter = MandiAdapter(this, it)

                mandiview.adapter = adapter
                mandiview.layoutManager = LinearLayoutManager(this)
            }
        })

    }
}