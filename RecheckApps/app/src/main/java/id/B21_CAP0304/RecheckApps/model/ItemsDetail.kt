package id.B21_CAP0304.RecheckApps.model

import androidx.room.PrimaryKey

data class ItemsDetail(
    @PrimaryKey(autoGenerate = true)
    var id : Int? = null,

    var title : String?,

    var count : Int?,

    var date : String?
)
