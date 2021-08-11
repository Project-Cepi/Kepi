package world.cepi.kepi

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor

enum class SystemLoadStatus(val component: Component) {

    ENABLED(Component.text("enabled", NamedTextColor.GREEN)),
    LOADING(Component.text("loading", NamedTextColor.WHITE)),
    ERROR(Component.text("error", NamedTextColor.RED))

}