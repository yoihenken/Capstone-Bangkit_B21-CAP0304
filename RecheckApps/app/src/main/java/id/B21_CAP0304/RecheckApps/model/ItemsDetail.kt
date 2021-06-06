package id.B21_CAP0304.RecheckApps.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class ItemsDetail(
    @PrimaryKey(autoGenerate = true)
    var id : Int? = null,

    @ColumnInfo(name = "title")
    var title : String? = null,

    @ColumnInfo(name = "count")
    var count : Int? = null,

    @ColumnInfo(name = "date")
    var date : String? = null
) : Parcelable
