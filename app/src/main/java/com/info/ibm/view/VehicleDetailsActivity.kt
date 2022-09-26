package com.info.ibm.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.info.ibm.R
import com.info.ibm.databinding.ActivityVehicleDetailsBinding

class VehicleDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVehicleDetailsBinding
    private lateinit var data: String
    private lateinit var dataMake: String
    private lateinit var dataColor: String
    private lateinit var dataType: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_vehicle_details)
        data = intent.getStringExtra("vin").toString()
        dataMake = intent.getStringExtra("make_and_model").toString()
        dataColor = intent.getStringExtra("color").toString()
        dataType = intent.getStringExtra("car_type").toString()

        binding.tvVin.text = data
        binding.tvMakeModel.text = dataMake
        binding.tvColor.text=dataColor
        binding.tvCarType.text=dataType


    }
}