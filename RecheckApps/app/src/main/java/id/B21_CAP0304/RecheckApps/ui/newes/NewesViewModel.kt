package id.B21_CAP0304.RecheckApps.ui.newes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.B21_CAP0304.RecheckApps.model.ItemDataResponse
import id.B21_CAP0304.RecheckApps.repository.remote.RemoteRepository
import kotlinx.coroutines.launch

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

    fun addCountItems() = viewModelScope.launch {
        val value = _itemDataRequest.value
        value?.add(ItemDataResponse())
        _itemDataRequest.value = value
    }

    fun removeCountItems(position: Int) = viewModelScope.launch {
        val value = _itemDataRequest.value
        value?.removeAt(position)
        _itemDataRequest.value = value
    }

    fun updatePosition(itemData: ItemDataResponse, position: Int) {
        val value = _itemDataRequest.value
        value?.set(position, itemData)
        _itemDataRequest.value = value
    }
}