package id.B21_CAP0304.RecheckApps.repository.remote

import id.B21_CAP0304.RecheckApps.model.GetItemResponse
import retrofit2.Call
import retrofit2.http.GET

interface Service {
    @GET("/")
    fun getHome(): Call<GetItemResponse>

}