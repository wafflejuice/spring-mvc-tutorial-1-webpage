package hello.itemservice.web.basic

import hello.itemservice.domain.item.Item
import hello.itemservice.domain.item.ItemRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/basic/items")
class BasicItemController(
    val itemRepository: ItemRepository
) {
    @GetMapping
    fun items(model: Model): String {
        val items = itemRepository.findAll()
        model.addAttribute("items", items)
        return "basic/items"
    }

    @PostMapping
    fun init() {
        itemRepository.save(Item(itemName = "itemA", price = 10000, quantity = 10))
        itemRepository.save(Item(itemName = "itemB", price = 20000, quantity = 20))
    }
}
