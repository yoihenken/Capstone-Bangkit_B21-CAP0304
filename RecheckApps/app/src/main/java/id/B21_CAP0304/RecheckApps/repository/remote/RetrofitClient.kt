package id.B21_CAP0304.RecheckApps.repository.remote

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient {
    private val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    private var gson: Gson = GsonBuilder()
        .setLenient()
        .create()

    var client = OkHttpClient.Builder().addInterceptor(logging).build()
    private val base: Retrofit = Retrofit.Builder()
        .baseUrl("http://34.126.110.154/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    fun services(): Service = base.create(Service::class.java)
}