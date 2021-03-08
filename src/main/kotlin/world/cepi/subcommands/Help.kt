package world.cepi.subcommands

import net.minestom.server.chat.ChatColor.*
import net.minestom.server.command.builder.Command

class Help(vararg val messages: String, name: String = "help") : Command(name) {

    companion object {
        const val head = "╓"
        const val tail = "╙"

        const val arm = "─"
        const val armLength = 4

        const val body = "║"
    }

    init {
        setDefaultExecutor { sender, _ ->
            sender.sendMessage("$DARK_GRAY$head${arm.repeat(armLength)}")

            messages.forEach {
                sender.sendMessage("$DARK_GRAY$body $GRAY$it")
            }

            sender.sendMessage("$DARK_GRAY$tail${arm.repeat(armLength)}")
        }
    }

}