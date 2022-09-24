package com.info.ibm.api

import com.info.ibm.model.VehiclesResponseItem
import com.info.ibm.utils.Constants
import retrofit2.Response
import retrofit2.http.GET


interface APIInterface {

    @GET(Constants.VEHICLELIST_URL)
    suspend fun getVehiclesList(): Response<List<VehiclesResponseItem>>
}