package world.cepi.language

import net.minestom.server.chat.JsonMessage
import net.minestom.server.command.CommandSender
import net.minestom.server.entity.Player

@JvmName("sendMessageLString")
fun CommandSender.sendMessage(message: Localizable<String>, vararg arguments: Any?) {
    if (this is Player) {
        sendMessage(message.localize(settings.locale, arguments))
    } else {
        sendMessage(message.localize(Language.getDefaultLocale(), arguments))
    }
}

@JvmName("sendMessageLJson")
fun CommandSender.sendMessage(message: Localizable<JsonMessage>, vararg arguments: Any?) {
    if (this is Player) {
        sendMessage(message.localize(settings.locale, arguments))
    } else {
        sendMessage(message.localize(Language.getDefaultLocale(), arguments))
    }
}

fun Player.sendActionBarMessage(message: Localizable<JsonMessage>, vararg arguments: Any?) {
    sendActionBarMessage(message.localize(settings.locale, arguments))
}

fun Player.sendTitleMessage(message: Localizable<JsonMessage>, vararg arguments: Any?) {
    sendTitleMessage(message.localize(settings.locale, arguments))
}

fun Player.sendSubtitleMessage(message: Localizable<JsonMessage>, vararg arguments: Any?) {
    sendSubtitleMessage(message.localize(settings.locale, arguments))
}
