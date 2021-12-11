package world.cepi.kepi

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.minestom.server.command.CommandSender

const val errorUnicode = "\uE006"

fun CommandSender.missingArgumentMessage(vararg arguments: String) {
    sendMessage(
        Component.text("Missing ", NamedTextColor.GRAY)
            .append(Component.text(arguments.joinToString(", "), NamedTextColor.RED))
            .append(Component.text(" argument${if (arguments.size == 1) "" else "s"}.", NamedTextColor.GRAY))
    )
}