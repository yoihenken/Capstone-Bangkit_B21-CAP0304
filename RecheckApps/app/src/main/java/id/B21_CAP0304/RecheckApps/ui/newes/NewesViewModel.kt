package id.B21_CAP0304.RecheckApps.ui.newes

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.B21_CAP0304.RecheckApps.model.GetItemResponse
import id.B21_CAP0304.RecheckApps.model.ItemDataResponse
import id.B21_CAP0304.RecheckApps.repository.remote.RemoteRepository

class NewesViewModel : ViewModel() {

    private val _masterItem = MutableLiveData<MutableList<ItemDataResponse>>()
    private val _itemDataRequest = MutableLiveData<MutableList<ItemDataResponse>>().apply {
        value = mutableListOf()
    }

    val itemData: LiveData<MutableList<ItemDataResponse>> get() = _masterItem
    val itemDataRequest: LiveData<MutableList<ItemDataResponse>> get() = _itemDataRequest

    init {
        RemoteRepository.getItems { status, response ->
            if (status) {
                _masterItem.value = response?.data?.toMutableList()
            } else {
                if (_masterItem.value == null) {
                    _masterItem.value = mutableListOf()
                }
            }
        }
    }

    fun predict(dataItems : GetItemResponse){
        RemoteRepository.predict(dataItems){
            //Log.d(TAG, "predict: ")
        }
    }
}