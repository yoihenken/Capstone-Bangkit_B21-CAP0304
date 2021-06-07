package id.B21_CAP0304.RecheckApps.model

data class GetItemResponse(
    val `data`: List<ItemDataResponse>
)

data class ItemDataResponse(
    val id: Double = Math.random(),
    var brand: String = "",
    var itemName: String = "",
    var price: Int = 0
)
