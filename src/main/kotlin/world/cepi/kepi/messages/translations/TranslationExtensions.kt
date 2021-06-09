package world.cepi.kepi.messages.translations

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.TextReplacementConfig
import net.kyori.adventure.text.format.NamedTextColor
import net.minestom.server.command.CommandSender
import net.minestom.server.entity.Player
import world.cepi.kepi.messages.formatPercent
import java.lang.Exception
import java.util.*

fun CommandSender.formatTranslableMessage(
    namespace: String = "common",
    key: String,
    vararg params: Component = arrayOf()
): Component =
    Component.text(
        TranslationRegistry[namespace, key, (this as? Player)?.settings?.locale ?: "en_US"]
            ?: "$namespace.$key"
    ).formatPercent(*params)