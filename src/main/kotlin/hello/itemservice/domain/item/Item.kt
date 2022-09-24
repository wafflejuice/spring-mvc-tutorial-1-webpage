package hello.itemservice.domain.item

data class Item(
    var id: Long,
    var itemName: String,
    var price: Int,
    var quantity: Int
)
