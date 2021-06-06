package id.B21_CAP0304.RecheckApps.model

data class GetItemResponse(
    val `data`: List<Data>
)

data class Data(
    val brand: String,
    val itemName: String
)