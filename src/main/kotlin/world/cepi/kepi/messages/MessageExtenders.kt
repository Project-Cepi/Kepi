package world.cepi.kepi.messages

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.TextReplacementConfig
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.Style
import net.kyori.adventure.text.format.TextDecoration
import net.kyori.adventure.text.serializer.plain.PlainComponentSerializer
import net.minestom.server.command.CommandSender
import java.lang.Exception
import java.util.regex.Pattern

private val replacementRegex = "%\\d+".toRegex()

/**
 * Creates a formatted message.
 *
 * @param params The replacers, usually used to replace a placeholder in a translation message
 *
 * @return The component made from this formatted message
 */
fun createFormattedMessage(component: Component, vararg params: Component = arrayOf()): Component {

    return Component.text().content("|").color(NamedTextColor.DARK_GRAY).decoration(TextDecoration.BOLD, true).build()
        .append(Component.space())
        .append(Component.text("", NamedTextColor.GRAY))
        .append(
            component
                .replaceText(
                    TextReplacementConfig.builder()
                        .match(replacementRegex.toPattern())
                        .replacement { replaceComponent ->
                            try {
                                return@replacement params[replaceComponent.content()
                                    .substring(1) // Remove the % prefix
                                    .toInt() - 1 // Arrays in replacements start at 1
                                ]
                            } catch (e: Exception) {
                                return@replacement Component.text("!!", NamedTextColor.RED)
                            }
                        }
                        .build()
                )
                .color(NamedTextColor.GRAY)
                .decoration(TextDecoration.BOLD, false)
        )
}

/**
 * Sends a formatted message to the corresponding sender.
 *
 * @param message The origin message, usually grabbed from the list of translations
 * @param params The replacers, usually used to replace a placeholder in a translation message
 */
fun CommandSender.sendFormattedMessage(component: Component, vararg params: Component = arrayOf()): Component {

    val formattedComponent = createFormattedMessage(component, *params)

    this.sendMessage(formattedComponent)

    return formattedComponent
}

/**
 * Sends a formatted message to the corresponding sender.
 *
 * @param message The origin message, usually grabbed from the list of translations
 * @param params The replacers, usually used to replace a placeholder in a translation message
 */
fun CommandSender.sendFormattedMessage(message: String, vararg params: Component = arrayOf()): Component =
    this.sendFormattedMessage(Component.text(message), *params)