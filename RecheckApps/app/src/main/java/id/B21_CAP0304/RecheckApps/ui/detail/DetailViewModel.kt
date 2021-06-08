package id.B21_CAP0304.RecheckApps.ui.detail

import android.app.Activity
import android.app.Application
import androidx.lifecycle.*
import id.B21_CAP0304.RecheckApps.model.ItemsDetail
import id.B21_CAP0304.RecheckApps.model.ItemsResult
import id.B21_CAP0304.RecheckApps.repository.local.LocalRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DetailViewModel : ViewModel() {

    private val _savedResult = MutableLiveData<List<ItemsResult>>()
    val savedResult: LiveData<List<ItemsResult>> get() = _savedResult

    //    Get saved result all
    fun getSavedResult(application: Application, id: Int, activity: Activity) =
        viewModelScope.launch {
            LocalRepository(application).getAllItemsResult(id).collect {
                it.observe(activity as LifecycleOwner) { result ->
                    _savedResult.value = result
                }
            }
        }

    //    Get saved result by id Detail
    fun getSavedResult(application: Application, activity: Activity, idEst: Int) =
        viewModelScope.launch {
            LocalRepository(application).getAllItemsResult(idEst).collect {
                it.observe(activity as LifecycleOwner) { result ->
                    _savedResult.value = result
                }
            }
        }

    fun deleteSaved(
        application: Application,
        itemsDetail: ItemsDetail,
        listItemsResult: List<ItemsResult>
    ) = viewModelScope.launch {
//        Delete Items Detail
        LocalRepository(application).deleteItemDetail(itemsDetail)

//        Delete Items Result
        for (itemsResult in listItemsResult) {
            LocalRepository(application).deleteItemResult(itemsResult)
        }
    }


}