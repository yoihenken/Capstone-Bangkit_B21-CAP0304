package id.B21_CAP0304.RecheckApps.ui.main

import android.app.Activity
import android.app.Application
import androidx.lifecycle.*
import id.B21_CAP0304.RecheckApps.model.ItemsDetail
import id.B21_CAP0304.RecheckApps.repository.local.LocalRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val itemsDetail = MutableLiveData<List<ItemsDetail>>()

    fun onResultItems() : LiveData<List<ItemsDetail>> = itemsDetail

    fun getItems(application: Application, activity: Activity) = viewModelScope.launch {
        LocalRepository(application).getAllItemsDetail().collect { it ->
            it.observe(activity as LifecycleOwner) {
                itemsDetail.value = it
            }
        }
    }

}