package com.info.ibm.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.info.ibm.R
import com.info.ibm.api.RetrofitInstance
import com.info.ibm.databinding.ActivityMainBinding
import com.info.ibm.repository.MainRepository
import com.info.ibm.repository.MyViewModelFactory
import com.info.ibm.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter = HomeAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initViews()
        setAdapter()
    }

    private fun initViews() {
        val retrofitService = RetrofitInstance
        val mainRepository = MainRepository(retrofitService)
        binding.viewModel = ViewModelProvider(this, MyViewModelFactory(mainRepository))[MainViewModel::class.java]
    }

    private fun setAdapter() {
        binding.rvHomeCards.layoutManager = GridLayoutManager(this, 2)
        binding.rvHomeCards.adapter = adapter

        binding.viewModel!!.vehicleList.observe(this, {
            adapter.setVehiclesResponseItemList(it)
        })
        binding.rvHomeCards.adapter = adapter

        binding.viewModel!!.errorMessage.observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        binding.viewModel!!.loading.observe(this, Observer {
            if (it) {
                binding.progressDialog.visibility = View.VISIBLE
                binding.rvHomeCards.adapter = adapter

            } else {
                binding.progressDialog.visibility = View.GONE
            }
        })

        binding.viewModel!!.getAllVehiclesList()
        binding.rvHomeCards.adapter = adapter
    }

}