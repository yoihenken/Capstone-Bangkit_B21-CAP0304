package id.B21_CAP0304.RecheckApps.ui.newes

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class NewesViewModelFactory(private val mApplication: Application) : ViewModelProvider.Factory {

    companion object {
        @Volatile
        private var INSTANCE: NewesViewModelFactory? = null
        @JvmStatic
        fun getInstance(application: Application): NewesViewModelFactory {
            if (INSTANCE == null) {
                synchronized(NewesViewModelFactory::class.java) {
                    INSTANCE = NewesViewModelFactory(application)
                }
            }
            return INSTANCE as NewesViewModelFactory
        }
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewesViewModel::class.java)) {
            return NewesViewModel(mApplication) as T
        } else if (modelClass.isAssignableFrom(NewesViewModel::class.java)) {
            return NewesViewModel(mApplication) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}