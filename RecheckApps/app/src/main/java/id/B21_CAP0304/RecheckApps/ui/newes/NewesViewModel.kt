package id.B21_CAP0304.RecheckApps.ui.newes

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class NewesViewModel : ViewModel() {

    private val curItems = mutableListOf<Int>()
    private val _countItems = MutableLiveData<List<Int>>()
    val countItems :  LiveData<List<Int>> get() = _countItems

    fun addCountItems(item : Int) = viewModelScope.launch {
        curItems.add(item)
        _countItems.value = curItems
    }

    fun removeCountItems(position : Int) = viewModelScope.launch {
        curItems.removeAt(position)
        _countItems.value = curItems
    }


}