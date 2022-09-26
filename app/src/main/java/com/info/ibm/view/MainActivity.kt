package com.info.ibm.view

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.info.ibm.R
import com.info.ibm.api.RetrofitInstance
import com.info.ibm.databinding.ActivityMainBinding
import com.info.ibm.model.VehiclesResponseItem
import com.info.ibm.repository.MainRepository
import com.info.ibm.repository.MyViewModelFactory
import com.info.ibm.viewmodel.MainViewModel
import java.util.*


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: HomeAdapter
    var swipeRefreshLayout: SwipeRefreshLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        swipeRefreshLayout = binding.container
        initViews()
        setAdapter()
        searchInputField()
        onShowBtnClick()
        onSwipeRefresh()
    }

    private fun initViews() {
        val retrofitService = RetrofitInstance
        val mainRepository = MainRepository(retrofitService)
        binding.viewModel =
            ViewModelProvider(this, MyViewModelFactory(mainRepository))[MainViewModel::class.java]
    }

    private fun onSwipeRefresh() {
        binding.container.setOnRefreshListener {

            Toast.makeText(this@MainActivity, "Swiped", Toast.LENGTH_SHORT).show()
            swipeRefreshLayout?.isRefreshing = false
            callApi()
        }
    }

    private fun setAdapter() {
        binding.rvHomeCards.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter = HomeAdapter(this, object : HomeAdapter.OnItemClickListner {
            override fun onClick(
                str: String,
                strMake: String,
                strColor: String,
                strCarType: String, kilometrage: Int
            ) {
                val intent = Intent(this@MainActivity, VehicleDetailsActivity::class.java)
                intent.putExtra("vin", str)
                intent.putExtra("make_and_model", strMake)
                intent.putExtra("color", strColor)
                intent.putExtra("car_type", strCarType)
                intent.putExtra("kilometrage", kilometrage)
                startActivity(intent)
            }


        })
        binding.rvHomeCards.adapter = adapter
    }


    private fun onShowBtnClick() {
        binding.btnList.setOnClickListener {
            val strNumber: String = binding.edtInput.text.toString().trim()
            if (TextUtils.isEmpty(strNumber) || strNumber.toInt() > 100) {
                Toast.makeText(this, "Enter input value between 1 to 100", Toast.LENGTH_SHORT)
                    .show()
            } else {
                callApi()
            }
        }
    }

    private fun callApi() {

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

    private fun searchInputField() {
        binding.edtInput.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                validation()
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

                // TODO Auto-generated method stub
            }

            override fun afterTextChanged(s: Editable) {

                // filter your list from your input
                filter(s.toString())
                //you can use runnable postDelayed like 500 ms to delay search text
            }
        })

    }


    fun filter(text: String?) {
        val temp: MutableList<VehiclesResponseItem> = ArrayList()
        for (d in temp) {
            //or use .equal(text) with you want equal match
            //use .toLowerCase() for better matches
            if (d.makeAndModel.equals(text)) {
                temp.add(d)
            }
        }
        //update recyclerview
        adapter.setVehiclesResponseItemList(temp)
    }

    private fun validation() {
        val strNumber: String = binding.edtInput.text.toString().trim()
        if (TextUtils.isEmpty(strNumber) || strNumber.toInt() > 100) {
            Toast.makeText(this, "Enter input value between 1 to 100", Toast.LENGTH_SHORT).show()
        }

    }
}



