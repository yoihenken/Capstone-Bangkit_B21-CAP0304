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
    var title : String? = null,

    @ColumnInfo(name = "brand")
    var brand : String? = null,

    @ColumnInfo(name = "price")
    var price : String? = null,

    @ColumnInfo(name = "status")
    var status: String? = null,

    @ColumnInfo(name = "idEst")
    var idEst: Int? = null

) : Parcelable