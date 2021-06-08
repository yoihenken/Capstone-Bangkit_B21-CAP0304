package id.B21_CAP0304.RecheckApps.repository.remote

import id.B21_CAP0304.RecheckApps.model.GetItemResponse
import id.B21_CAP0304.RecheckApps.model.ItemDataResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface Service {
    @GET("/getItems")
    fun getHome(): Call<GetItemResponse>


    @POST("/predict")
    fun predict(@Body data: GetItemResponse): Call<List<Int>>

}