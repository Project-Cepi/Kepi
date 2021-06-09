package world.cepi.kepi.subcommands

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.minestom.server.command.builder.Command
import world.cepi.kstom.adventure.asMini

open class Help(val messages: String, name: String = "help", vararg aliases: String = arrayOf("?")) : Command(name, *aliases) {

    companion object {
        const val arm = "─"
        const val armLength = 5
    }

    init {
        setDefaultExecutor { sender, _ ->
            sender.sendMessage(Component.text(arm.repeat(armLength), NamedTextColor.DARK_GRAY))

            messages.trim().split("\n").forEach {
                sender.sendMessage(Component.text().color(NamedTextColor.GRAY).append(it.asMini()))
            }

            sender.sendMessage(Component.text(arm.repeat(armLength), NamedTextColor.DARK_GRAY))
        }
    }

}

/**
 * Applies the help subcommand to this command
 *
 * @param messages The messages to send to the player
 * @param name The name of the subcommand
 * @param aliases The aliases of the subcommand
 * @param shouldAddSubcommand If the subcommand should be added in the first place.
 */
fun Command.applyHelp(
    messages: String,
    name: String = "help",
    vararg aliases: String = arrayOf("?"),
    shouldAddSubcommand: Boolean = true
) {

    val help = Help(messages, name, *aliases)

    this.defaultExecutor = help.defaultExecutor

    if (shouldAddSubcommand) this.addSubcommand(help)
}