package com.info.ibm.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.info.ibm.R
import com.info.ibm.api.RetrofitInstance
import com.info.ibm.databinding.ActivityMainBinding
import com.info.ibm.repository.MainRepository
import com.info.ibm.repository.MyViewModelFactory
import com.info.ibm.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter:HomeAdapter

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
        binding.rvHomeCards.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        adapter= HomeAdapter(this, object :HomeAdapter.OnItemClickListner{
            override fun onClick(str: String,strMake:String,strColor:String,strCarType:String) {
                val intent=Intent(this@MainActivity, VehicleDetailsActivity::class.java)
                intent.putExtra("vin",str)
                intent.putExtra("make_and_model",strMake)
                intent.putExtra("color",strColor)
                intent.putExtra("car_type",strCarType)
                startActivity(intent)
            }


        })
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