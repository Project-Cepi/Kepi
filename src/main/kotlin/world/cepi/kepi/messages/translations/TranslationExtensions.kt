package world.cepi.kepi.messages.translations

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.TextReplacementConfig
import net.kyori.adventure.text.format.NamedTextColor
import net.minestom.server.command.CommandSender
import net.minestom.server.entity.Player
import java.lang.Exception
import java.util.*

fun CommandSender.formatTranslableMessage(namespace: String = "common", key: String): Component {
    return Component.text(TranslationRegistry.get(namespace, key, (this as? Player)?.locale ?: Locale.ENGLISH) ?: "$namespace.$key")
}