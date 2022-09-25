package hello.itemservice.domain.item

data class Item(
    var id: Long = 0L,
    var itemName: String,
    var price: Int,
    var quantity: Int
)
