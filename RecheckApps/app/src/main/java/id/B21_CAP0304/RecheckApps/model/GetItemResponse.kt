package id.B21_CAP0304.RecheckApps.model

import java.math.BigInteger

data class GetItemResponse(
    val `data`: List<ItemDataResponse>
)

data class ItemDataResponse(
    val id: Double = Math.random(),
    var brand: String = "",
    var itemName: String = "",
    var price: BigInteger = 0.toBigInteger(),
    var status: Boolean = false
)
