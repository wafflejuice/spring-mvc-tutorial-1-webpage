package hello.itemservice.domain.item

import org.springframework.stereotype.Repository
import java.util.concurrent.ConcurrentHashMap

@Repository
class ItemRepository {
    val store: ConcurrentHashMap<Long, Item> = ConcurrentHashMap()
    var sequence: Long = 0L

    fun save(item: Item): Item {
        item.id = ++sequence
        store[item.id] = item
        return item
    }

    fun findById(id: Long): Item? {
        return store[id]
    }

    fun findAll(): List<Item> {
        return store.values.toList()
    }

    fun update(itemId: Long, updateParam: Item) {
        val findItem = findById(itemId)
        findItem?.itemName = updateParam.itemName
        findItem?.price = updateParam.price
        findItem?.quantity = updateParam.quantity
    }

    fun clearStore() {
        store.clear()
    }
}
