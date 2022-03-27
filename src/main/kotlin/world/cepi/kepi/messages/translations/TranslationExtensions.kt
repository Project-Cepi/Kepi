package world.cepi.kepi.messages.translations

import net.kyori.adventure.text.Component
import net.minestom.server.command.CommandSender
import net.minestom.server.entity.Player
import world.cepi.kepi.messages.formatPercent
import world.cepi.kstom.adventure.asMini

fun CommandSender.formatTranslableMessage(
    namespace: String = "common",
    key: String,
    vararg params: Component = arrayOf()
): Component =
    this.translableMessage(namespace, key)
        .asMini().formatPercent(*params)

fun CommandSender.translableMessage(
    namespace: String = "common",
    key: String
) = TranslationRegistry[namespace, key, (this as? Player)?.settings?.locale ?: "en_US"]
    ?: "$namespace.$key"