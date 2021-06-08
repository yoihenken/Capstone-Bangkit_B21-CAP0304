package id.B21_CAP0304.RecheckApps.ui.newes

import android.app.Activity
import android.app.Application
import android.icu.text.SimpleDateFormat
import android.util.Log
import androidx.lifecycle.*
import id.B21_CAP0304.RecheckApps.model.GetItemResponse
import id.B21_CAP0304.RecheckApps.model.ItemDataResponse
import id.B21_CAP0304.RecheckApps.model.ItemsDetail
import id.B21_CAP0304.RecheckApps.model.ItemsResult
import id.B21_CAP0304.RecheckApps.repository.local.LocalRepository
import id.B21_CAP0304.RecheckApps.repository.remote.RemoteRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*

class NewesViewModel(val application: Application) : ViewModel() {
    private val localRepository: LocalRepository = LocalRepository(application)

    private val itemInserted = MutableLiveData<ItemsDetail>()

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

    fun onSave() : LiveData<ItemsDetail> = itemInserted

    fun predict(title: String, dataItems: GetItemResponse, activity: Activity) {
        val sdf = SimpleDateFormat("dd MMMM yyyy")
        val currentDate = sdf.format(Date())
        val itemsDetail =
            ItemsDetail(title = title, count = dataItems.data.size, date = currentDate)

        RemoteRepository.predict(dataItems) {
            if (it[0] != -1) {
                for ((idx, result) in it.withIndex()) {
                    dataItems.data[idx].status = result == 1
//                    val itemData = dataItems.data[idx]
//                    itemsResult.add(
//                        ItemsResult(
//                            title = itemData.itemName,
//                            price = itemData.price.toString(),
//                            brand = itemData.brand,
//                            status = if (result == 1) "Overprice" else "Not Overprice"
//                        )
//                    )
                }
                saveItemsData(itemsDetail, dataItems, activity)
            }
        }
    }

    fun saveItemsData(itemsDetail: ItemsDetail, dataItems: GetItemResponse, activity: Activity) =
        viewModelScope.launch {
            val itemsResult = mutableListOf<ItemsResult>()
            Log.d("TAG", "saveItemsData: $itemsDetail")
            localRepository.saveItemDetail(itemsDetail)

            localRepository.getItemsDetail(itemsDetail.title!!, itemsDetail.date!!).collect { it ->
                it.observe(activity as LifecycleOwner) {
                    if(it.isNotEmpty()){
                        Log.d("TAG", "saveItemsData: $it")

                        for (itemData in dataItems.data) {
                                itemsResult.add(
                                    ItemsResult(
                                        title = itemData.itemName,
                                        price = itemData.price.toString(),
                                        brand = itemData.brand,
                                        status = if (itemData.status) "Overprice" else "Not Overprice",
                                        idEst = it[0].id
                                    )
                                )

                        }

                        saveItemsResultBatch(itemsResult)
                        itemInserted.value = it[0]
                    }
                }
            }




        }

    fun saveItemsResultBatch(itemsResult: List<ItemsResult>) = viewModelScope.launch {
        localRepository.saveItemResultBatch(itemsResult)
    }
}