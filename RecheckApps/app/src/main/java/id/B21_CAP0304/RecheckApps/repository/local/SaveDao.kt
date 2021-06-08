package id.B21_CAP0304.RecheckApps.repository.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import id.B21_CAP0304.RecheckApps.model.ItemsDetail
import id.B21_CAP0304.RecheckApps.model.ItemsResult

@Dao
interface SaveDao {

    //ItemsDetail
    @Insert
    suspend fun insertItemsDetail(itemsDetail: ItemsDetail)

    @Delete
    suspend fun deleteItemsDetail(itemsDetail: ItemsDetail)

    @Query("select * from ItemsDetail")
    fun getAllItemsDetail(): LiveData<List<ItemsDetail>>

    @Query("select * from ItemsDetail where title = :title AND date = :date")
    fun getItemsDetail(title: String, date: String): LiveData<List<ItemsDetail>>

    // ItemsResult
    @Insert
    suspend fun insertItemsResult(itemsResult: ItemsResult)

    @Insert
    suspend fun insertItemsResultBatch(itemsResult: List<ItemsResult>)

    @Delete
    suspend fun deleteItemsResult(itemsResult: ItemsResult)


    @Query("select * from ItemsResult where idEst = :idESt")
    fun getAllItemsResult(idESt: Int): LiveData<List<ItemsResult>>


}