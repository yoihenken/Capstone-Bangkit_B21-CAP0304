package id.B21_CAP0304.RecheckApps.repository.local

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.room.*
import id.B21_CAP0304.RecheckApps.model.ItemsDetail
import id.B21_CAP0304.RecheckApps.model.ItemsResult

@Dao
interface SaveDao {

    //ItemsDetail
    @Insert
    suspend fun insertItemsDetail(itemsDetail: ItemsDetail)

    @Delete
    suspend fun deleteItemsDetail(itemsDetail: ItemsDetail)

    @Update
    suspend fun updateItemsDetail(itemsDetail: ItemsDetail)

    @Query("select * from ItemsDetail")
    fun getAllItemsDetail() : LiveData<List<ItemsDetail>>

    @Query("select * from ItemsDetail")
    fun getAllDataItemsDetail() : Cursor

    //ItemsResult
    @Insert
    suspend fun insertItemsResult(itemsResult: ItemsResult)

    @Delete
    suspend fun deleteItemsResult(itemsResult: ItemsResult)

    @Update
    suspend fun updateItemsResult(itemsResult: ItemsResult)

    @Query("select * from ItemsResult")
    fun getAllItemsResult() : LiveData<List<ItemsResult>>

    @Query("select * from ItemsResult where idEst = :idESt")
    fun getAllItemsResult(idESt : Int) : LiveData<List<ItemsResult>>

    @Query("select * from ItemsResult")
    fun getAllDataItemsResult() : Cursor

}