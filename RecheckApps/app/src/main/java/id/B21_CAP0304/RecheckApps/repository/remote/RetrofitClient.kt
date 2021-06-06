package id.B21_CAP0304.RecheckApps.repository.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private val base: Retrofit = Retrofit.Builder()
        .baseUrl("https://34.126.110.154/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun services(): Service = base.create(Service::class.java)
}