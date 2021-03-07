package world.cepi.messages

import net.minestom.server.chat.ChatColor
import net.minestom.server.command.CommandSender
import world.cepi.kstom.plus

/**
 * Sends a formatted message to the corresponding sender.
 *
 * @param message The origin message, usually grabbed from the list of translations
 * @param params The replacers, usually used to replace a placeholder in a translation message
 */
fun CommandSender.sendFormattedMessage(message: String, vararg params: String = arrayOf("")) {

    var parsedMessage = message

    params.forEachIndexed { index, item ->
        parsedMessage = parsedMessage.replaceFirst("%${index + 1}", item)
    }

    this.sendMessage(ChatColor.DARK_GRAY + ChatColor.BOLD + "| " + ChatColor.RESET + ChatColor.GRAY + parsedMessage)
}