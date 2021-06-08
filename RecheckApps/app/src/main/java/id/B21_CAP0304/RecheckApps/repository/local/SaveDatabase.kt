package id.B21_CAP0304.RecheckApps.repository.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import id.B21_CAP0304.RecheckApps.model.ItemsDetail
import id.B21_CAP0304.RecheckApps.model.ItemsResult

@Database(entities = [ItemsDetail::class, ItemsResult::class], version = 1, exportSchema = false)
abstract class SaveDatabase : RoomDatabase() {

    abstract fun saveDao() : SaveDao

    companion object{
        private var instance : SaveDatabase? = null

        @Synchronized
        fun getInstance(context: Context) : SaveDatabase{
            if (instance == null)
                instance = Room.databaseBuilder(
                    context.applicationContext, SaveDatabase::class.java, "DB_RECHECK"
                )
                    .fallbackToDestructiveMigration()
                    .build()
            return instance!!
        }
    }
}