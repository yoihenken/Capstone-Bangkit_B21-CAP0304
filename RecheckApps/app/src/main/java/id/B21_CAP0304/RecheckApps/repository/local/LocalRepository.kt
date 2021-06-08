package id.B21_CAP0304.RecheckApps.repository.local

import android.app.Application
import androidx.lifecycle.LiveData
import id.B21_CAP0304.RecheckApps.model.ItemsDetail
import id.B21_CAP0304.RecheckApps.model.ItemsResult
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class LocalRepository(application: Application) {

    private var saveDao: SaveDao
    private val database = SaveDatabase.getInstance(application)
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        saveDao = database.saveDao()
    }

    //Items Detail

    fun saveItemResult(itemsResult: ItemsResult) {
        executorService.execute {
            saveDao.insertItemsResult(itemsResult)
        }
    }

    fun saveItemDetail(itemsDetail: ItemsDetail) {
        executorService.execute {
            saveDao.insertItemsDetail(itemsDetail)
        }
    }

    fun deleteItemResult(itemsResult: ItemsResult) {
        executorService.execute {
            saveDao.deleteItemsResult(itemsResult)
        }
    }

    fun deleteItemDetail(itemsDetail: ItemsDetail) {
        executorService.execute {
            saveDao.deleteItemsDetail(itemsDetail)
        }
    }

    fun getAllItemsResult(id: Int): LiveData<List<ItemsResult>> = saveDao.getAllItemsResult(id)
    fun getAllItemsDetail(): LiveData<List<ItemsDetail>> = saveDao.getAllItemsDetail()
    fun getItemsDetail(id: Int): LiveData<ItemsDetail> = saveDao.getItemsDetail(id)
}