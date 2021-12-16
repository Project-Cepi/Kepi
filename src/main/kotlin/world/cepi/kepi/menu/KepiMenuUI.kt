package world.cepi.kepi.menu

import com.mattworzala.canvas.fragment
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor

fun menuUI() = fragment(9, 6) {

    this.inventory.title = Component.text("Menu", NamedTextColor.BLUE)

}