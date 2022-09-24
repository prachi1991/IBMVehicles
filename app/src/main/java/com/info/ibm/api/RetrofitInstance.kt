package com.info.ibm.api

import com.info.ibm.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitInstance {
    private var mInstance: RetrofitInstance? = null

    var apiInterface: APIInterface? = null
        get() {
            if (field == null) {
                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BODY
                val httpClient = OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(10, TimeUnit.SECONDS)
                httpClient.addInterceptor(logging) // <-- this is the important line!

                field = Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(APIInterface::class.java)
            }
            return field
        }
        private set

    val instance: RetrofitInstance?
        get() {
            if (mInstance == null) {
                mInstance = RetrofitInstance
            }
            return mInstance
        }
}