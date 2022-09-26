package com.info.ibm.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.info.ibm.R
import com.info.ibm.databinding.ActivityVehicleDetailsBinding
import kotlinx.android.synthetic.main.layout_bottom_sheet.*
import kotlinx.android.synthetic.main.layout_bottom_sheet.view.*

class VehicleDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVehicleDetailsBinding
    private lateinit var data: String
    private lateinit var dataMake: String
    private lateinit var dataColor: String
    private lateinit var dataType: String
    private var kilometrage: Int = 0
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_vehicle_details)

        initBottomSheet()

        data = intent.getStringExtra("vin").toString()
        dataMake = intent.getStringExtra("make_and_model").toString()
        dataColor = intent.getStringExtra("color").toString()
        dataType = intent.getStringExtra("car_type").toString()
        kilometrage = intent.getIntExtra("kilometrage", 0)

        binding.tvVin.text = data
        binding.tvMakeModel.text = dataMake
        binding.tvColor.text = dataColor
        binding.tvCarType.text = dataType
        val carbonEmitted = kilometrage - 5000
        val firstKms = carbonEmitted*1.5
        val finalKms= firstKms+5000

        binding.bottomSheet.bottomSheet.tvKm.text = kilometrage.toString()+" Kilometrage"
        binding.bottomSheet.bottomSheet.tvCarbonEmission.text=finalKms.toString()+" Carbon Emission"
            //kilometrage.toString()




    }

    private fun initBottomSheet() {
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        binding.btnBottomSheet.setOnClickListener {
//            binding.bottomSheetKm.bottomSheet.tvKm.text= kilometrage.toString()
            if (bottomSheetBehavior.state == BottomSheetBehavior.STATE_EXPANDED) {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED

            } else {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED

            }
        }
    }
}
