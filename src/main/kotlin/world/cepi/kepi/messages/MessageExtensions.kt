package world.cepi.kepi.messages

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.TextReplacementConfig
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import net.minestom.server.command.CommandSender
import world.cepi.kepi.messages.translations.formatTranslableMessage
import java.lang.Exception

private val replacementRegex = "%\\d+".toRegex()

/**
 * Creates a formatted prefix].
 *
 * @return The component made from this formatted message
 */
val formattedPrefix = Component.text().content("|").color(NamedTextColor.DARK_GRAY).decoration(TextDecoration.BOLD, true).build()
        .append(Component.space())
        .append(Component.text("", NamedTextColor.GRAY))

/**
 * Replaces all %{number: 1-...} in a component with params.
 */
fun Component.formatPercent(vararg params: Component = arrayOf()): Component =
    this.replaceText(
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

/**
 * Sends a formatted message to the corresponding sender.
 *
 * @param component The origin component, this just appends the prefix.
 */
fun CommandSender.sendFormattedMessage(component: Component, vararg params: Component = arrayOf()) =
    this.sendMessage(
        formattedPrefix
            .append(
                Component.text()
                    .color(NamedTextColor.GRAY)
                    .decoration(TextDecoration.BOLD, false)
                    .append(component.formatPercent(*params))
            )
    )

/**
 * Sends an error message to the corresponding sender.
 *
 * @param component The origin component, this just appends the prefix.
 */
fun CommandSender.sendErrorMessage(component: Component, vararg params: Component = arrayOf()) =
    this.sendMessage(
        Component.text("\uEff7")
            .append(Component.space())
            .append(
                Component.text()
                    .color(NamedTextColor.RED)
                    .append(component.formatPercent(*params))
            )
    )

/**
 * Sends a formatted & translated message to the corresponding sender.
 *
 * @param message The origin message, usually grabbed from the list of translations
 * @param params The replacers, usually used to replace a placeholder in a translation message
 */
fun CommandSender.sendFormattedTranslatableMessage(namespace: String, key: String, vararg params: Component = arrayOf()) =
    this.sendFormattedMessage(this.formatTranslableMessage(namespace, key,  *params))

/**
 * Sends a formatted & translated message to the corresponding sender.
 *
 * @param message The origin message, usually grabbed from the list of translations
 * @param params The replacers, usually used to replace a placeholder in a translation message
 */
fun CommandSender.sendErrorTranslatableMessage(namespace: String, key: String, vararg params: Component = arrayOf()) =
    this.sendErrorMessage(this.formatTranslableMessage(namespace, key,  *params))