package world.cepi.kepi.messages

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.TextReplacementConfig
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.Style
import net.kyori.adventure.text.format.TextDecoration
import net.minestom.server.command.CommandSender

/**
 * Sends a formatted message to the corresponding sender.
 *
 * @param message The origin message, usually grabbed from the list of translations
 * @param params The replacers, usually used to replace a placeholder in a translation message
 */
fun CommandSender.sendFormattedMessage(component: Component, vararg params: Component = arrayOf()) {

    var mutableComponent = component

    params.forEachIndexed { index, item ->
        mutableComponent = component.replaceText(TextReplacementConfig.builder().match("%${index + 1}").replacement(item).build())
    }

    this.sendMessage(
        Component.text("|")
            .style(Style.style(NamedTextColor.DARK_GRAY, TextDecoration.BOLD))
            .append(Component.text("", NamedTextColor.GRAY))
            .append(Component.space()).append(mutableComponent)
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

/**
 * Sends a formatted message to the corresponding sender.
 *
 * @param message The origin message, usually grabbed from the list of translations
 * @param params The replacers, usually used to replace a placeholder in a translation message
 */
fun CommandSender.sendFormattedMessage(message: String, vararg params: String = arrayOf()) {

    this.sendFormattedMessage(Component.text(message), *params.map { Component.text(it) }.toTypedArray())

}