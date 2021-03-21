package world.cepi.kepi.subcommands

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.minestom.server.command.builder.Command

class Help(vararg val messages: Component, name: String = "help") : Command(name) {

    companion object {
        const val head = "╓"
        const val tail = "╙"

        const val arm = "─"
        const val armLength = 4

        const val body = "║"
    }

    init {
        setDefaultExecutor { sender, _ ->
            sender.sendMessage(Component.text("$head${arm.repeat(armLength)}", NamedTextColor.DARK_GRAY))

            sender.sendMessage(Component.text("$body ", NamedTextColor.DARK_GRAY)
                .append(messages.reduce { acc, component -> acc.append(component).color(NamedTextColor.GRAY) }))

            sender.sendMessage(Component.text("$tail${arm.repeat(armLength)}", NamedTextColor.DARK_GRAY))
        }
    }

}