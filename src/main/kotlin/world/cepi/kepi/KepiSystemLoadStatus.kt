package world.cepi.kepi

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor

enum class KepiSystemLoadStatus(val component: Component) {

    ENABLED(Component.text("enabled", NamedTextColor.GREEN)),
    LOADING(Component.text("Loading", NamedTextColor.WHITE)),
    ERROR(Component.text("error", NamedTextColor.RED))

}