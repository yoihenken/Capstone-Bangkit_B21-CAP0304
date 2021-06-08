package id.B21_CAP0304.RecheckApps.repository.local

import android.app.Application
import id.B21_CAP0304.RecheckApps.model.ItemsDetail
import id.B21_CAP0304.RecheckApps.model.ItemsResult
import kotlinx.coroutines.flow.flow
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class LocalRepository(application: Application) {

    private var saveDao: SaveDao
    private val database = SaveDatabase.getInstance(application)

    init {
        saveDao = database.saveDao()
    }

    //Items Detail
    suspend fun saveItemResult(itemsResult: ItemsResult) {
        saveDao.insertItemsResult(itemsResult)
    }

    suspend fun saveItemResultBatch(itemsResult: List<ItemsResult>) {
        saveDao.insertItemsResultBatch(itemsResult)
    }

    suspend fun saveItemDetail(itemsDetail: ItemsDetail) {
        saveDao.insertItemsDetail(itemsDetail)
    }

    suspend fun deleteItemResult(itemsResult: ItemsResult) {
        saveDao.deleteItemsResult(itemsResult)
    }

    suspend fun deleteItemDetail(itemsDetail: ItemsDetail) {
        saveDao.deleteItemsDetail(itemsDetail)
    }

    suspend fun getAllItemsResult(id: Int) = flow { emit(saveDao.getAllItemsResult(id)) }

    suspend fun getAllItemsDetail() = flow {
        emit(saveDao.getAllItemsDetail())
    }
    suspend fun getItemsDetail(title: String, date: String) = flow {
        emit(saveDao.getItemsDetail(title, date))
    }
}