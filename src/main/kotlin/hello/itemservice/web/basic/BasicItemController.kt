package hello.itemservice.web.basic

import hello.itemservice.domain.item.Item
import hello.itemservice.domain.item.ItemRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import javax.annotation.PostConstruct


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

    @GetMapping("/{itemId}")
    fun item(@PathVariable itemId: Long, model: Model): String {
        val item = itemRepository.findById(itemId)
        model.addAttribute("item", item)
        return "basic/item"
    }

    @GetMapping("/add")
    fun addForm(): String {
        return "basic/addForm"
    }

    /*
    @PostMapping("/add")
    fun addItemV1(
        @RequestParam itemName: String,
        @RequestParam price: Int,
        @RequestParam quantity: Int,
        model: Model
    ): String {
        val item = Item(
            itemName = itemName,
            price = price,
            quantity = quantity
        )

        itemRepository.save(item)
        model.addAttribute("item", item)
        return "basic/item"
    }
     */

    /*
    @PostMapping("/add")
    fun addItemV2(
        @ModelAttribute("item") item: Item,
        model: Model
    ): String {
        itemRepository.save(item)
        //model.addAttribute("item", item); //자동 추가, 생략 가능
        return "basic/item"
    }
     */

    /*
    @PostMapping("/add")
    fun addItemV3(
        @ModelAttribute item: Item,
        model: Model
    ): String {
        itemRepository.save(item)
        return "basic/item"
    }
     */

    /*
    @PostMapping("/add")
    fun addItemV4(
        item: Item
    ): String {
        itemRepository.save(item)
        return "basic/item"
    }
     */

    /*
    @PostMapping("/add")
    fun addItemV5(
        item: Item
    ): String {
        itemRepository.save(item)
        return "redirect:/basic/items/${item.id}"
    }
     */

    @PostMapping("/add")
    fun addItemV6(
        item: Item,
        redirectAttributes: RedirectAttributes
    ): String {
        val savedItem = itemRepository.save(item)
        redirectAttributes.addAttribute("itemId", savedItem.id)
        redirectAttributes.addAttribute("status", true) // query parameter
        return "redirect:/basic/items/{itemId}"
    }

    @GetMapping("/{itemId}/edit")
    fun editForm(
        @PathVariable itemId: Long,
        model: Model
    ): String {
        val item = itemRepository.findById(itemId)
        model.addAttribute("item", item)
        return "basic/editForm"
    }

    @PostMapping("/{itemId}/edit")
    fun edit(
        @PathVariable itemId: Long,
        @ModelAttribute item: Item
    ): String {
        itemRepository.update(itemId, item)
        return "redirect:/basic/items/{itemId}"
    }

    @PostConstruct
    fun init() {
        itemRepository.save(Item(itemName = "itemA", price = 10000, quantity = 10))
        itemRepository.save(Item(itemName = "itemB", price = 20000, quantity = 20))
    }
}
