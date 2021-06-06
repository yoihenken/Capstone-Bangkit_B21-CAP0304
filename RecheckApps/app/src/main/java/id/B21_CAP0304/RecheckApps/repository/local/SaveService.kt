package id.B21_CAP0304.RecheckApps.repository.local

import android.app.Application
import id.B21_CAP0304.RecheckApps.model.ItemsDetail
import id.B21_CAP0304.RecheckApps.model.ItemsResult
import kotlinx.coroutines.flow.flow

class SaveService (application: Application) {

    private var saveDao : SaveDao
    private val database = SaveDatabase.getInstance(application)
    private val databaseMainThread = SaveDatabase.getInstanceMainThread(application)

    init {
        saveDao = database.saveDao()
    }

    //Items Detail

    suspend fun addSaveItemsDetail(itemsDetail: ItemsDetail){
        saveDao.insertItemsDetail(itemsDetail)
    }

    suspend fun deleteSaveItemsDetail(itemsDetail: ItemsDetail){
        saveDao.deleteItemsDetail(itemsDetail)
    }

    suspend fun updateSaveItemsDetail(itemsDetail: ItemsDetail){
        saveDao.updateItemsDetail(itemsDetail)
    }

    suspend fun getAllItemsDetail() = flow {
        emit(saveDao.getAllItemsDetail())
    }

    suspend fun getAllDataItemsDetail() = saveDao.getAllDataItemsDetail()

    //Items Result

    suspend fun addSaveItemsResult(itemsResult: ItemsResult){
        saveDao.insertItemsResult(itemsResult)
    }

    suspend fun deleteSaveItemsResult(itemsResult: ItemsResult){
        saveDao.deleteItemsResult(itemsResult)
    }

    suspend fun updateSaveItemsResult(itemsResult: ItemsResult){
        saveDao.updateItemsResult(itemsResult)
    }

    suspend fun getAllItemsResult() = flow {
        emit(saveDao.getAllItemsResult())
    }

    suspend fun getAllItemsResult(idEst : Int) = flow {
        emit(saveDao.getAllItemsResult(idEst))
    }

    suspend fun getAllDataItemsResult() = saveDao.getAllDataItemsDetail()


}