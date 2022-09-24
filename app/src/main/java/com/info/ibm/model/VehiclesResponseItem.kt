package com.info.ibm.model


import com.google.gson.annotations.SerializedName

data class VehiclesResponseItem(
    @SerializedName("car_options")
    val carOptions: List<String>,
    @SerializedName("car_type")
    val carType: String,
    @SerializedName("color")
    val color: String,
    @SerializedName("doors")
    val doors: Int,
    @SerializedName("drive_type")
    val driveType: String,
    @SerializedName("fuel_type")
    val fuelType: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("kilometrage")
    val kilometrage: Int,
    @SerializedName("license_plate")
    val licensePlate: String,
    @SerializedName("make_and_model")
    val makeAndModel: String,
    @SerializedName("mileage")
    val mileage: Int,
    @SerializedName("specs")
    val specs: List<String>,
    @SerializedName("transmission")
    val transmission: String,
    @SerializedName("uid")
    val uid: String,
    @SerializedName("vin")
    val vin: String
)