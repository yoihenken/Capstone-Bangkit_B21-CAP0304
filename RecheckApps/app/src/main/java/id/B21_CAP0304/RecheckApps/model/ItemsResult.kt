package id.B21_CAP0304.RecheckApps.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Entity
@Parcelize
data class ItemsResult (
    @PrimaryKey(autoGenerate = true)
    var id : Int? = null,

    @ColumnInfo(name = "title")
    var title : String?,

    @ColumnInfo(name = "price")
    var price : Int?,

    @ColumnInfo(name = "status")
    var status: String?,

    @ColumnInfo(name = "nameEst")
    var nameEst: String?

) : Parcelable