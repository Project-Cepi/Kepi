package world.cepi.kepi.messages

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.TextReplacementConfig
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.Style
import net.kyori.adventure.text.format.TextDecoration
import net.kyori.adventure.text.serializer.plain.PlainComponentSerializer
import net.minestom.server.command.CommandSender
import java.util.regex.Pattern

private val replacementRegex = "%(\\d+)".toRegex()

/**
 * Sends a formatted message to the corresponding sender.
 *
 * @param message The origin message, usually grabbed from the list of translations
 * @param params The replacers, usually used to replace a placeholder in a translation message
 */
fun CommandSender.sendFormattedMessage(component: Component, vararg params: Component = arrayOf()) {
    
    this.sendMessage(
        Component.text().content("|").color(NamedTextColor.DARK_GRAY).decoration(TextDecoration.BOLD, true).build()
            .append(Component.space())
            .append(Component.text("", NamedTextColor.GRAY))
            .append(
                component
                    .replaceText(
                        TextReplacementConfig.builder()
                            .match(replacementRegex.toPattern())
                            .replacement { component -> Component.text(replacementRegex.find(component.content())!!.groupValues[0]) }
                            .build()
                    )
                    .color(NamedTextColor.GRAY)
                    .decoration(TextDecoration.BOLD, false)
            )
    )
}

/**
 * Sends a formatted message to the corresponding sender.
 *
 * @param message The origin message, usually grabbed from the list of translations
 * @param params The replacers, usually used to replace a placeholder in a translation message
 */
fun CommandSender.sendFormattedMessage(message: String, vararg params: Component = arrayOf()) {

    this.sendFormattedMessage(Component.text(message), *params)

}