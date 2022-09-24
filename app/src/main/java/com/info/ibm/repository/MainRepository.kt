package com.info.ibm.repository

import com.info.ibm.api.RetrofitInstance

class MainRepository constructor(private val retrofitService: RetrofitInstance) {

    suspend fun getVehiclesList() = retrofitService.instance?.apiInterface?.getVehiclesList()

}