package id.B21_CAP0304.RecheckApps.repository.remote

import android.util.Log
import id.B21_CAP0304.RecheckApps.model.GetItemResponse
import id.B21_CAP0304.RecheckApps.model.ItemDataResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object RemoteRepository {
    fun getItems(callback : (Boolean, GetItemResponse?) -> Unit) {
        RetrofitClient.services().getHome().enqueue(object : Callback<GetItemResponse>{
            override fun onResponse(
                call: Call<GetItemResponse>,
                response: Response<GetItemResponse>
            ) {
                if(response.body() != null){
                    callback(true, response.body())
                    //Log.d(TAG, "onResponse: ${response.body()}")

                } else {
                   callback(false, null)
                }
            }

            override fun onFailure(call: Call<GetItemResponse>, t: Throwable) {
               callback(false, null)
                Log.d("TAG", "onFailure: ${t.localizedMessage}")
                //Log.d("TAG", "onFailure: ${call.}")
            }
        })
    }

    fun predict(dataItems : GetItemResponse, callback : (List<Int>) -> Unit){
        RetrofitClient.services().predict(dataItems).enqueue(object : Callback<List<Int>> {
            override fun onResponse(call: Call<List<Int>>, response: Response<List<Int>>) {
                callback(response.body()!!)
            }

            override fun onFailure(call: Call<List<Int>>, t: Throwable) {
                callback(listOf(-1))
            }
        })
    }
}