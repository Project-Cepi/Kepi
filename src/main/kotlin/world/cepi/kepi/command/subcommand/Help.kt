package world.cepi.kepi.command.subcommand

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.minestom.server.command.CommandSender
import net.minestom.server.command.builder.Command
import net.minestom.server.entity.Player
import world.cepi.kepi.messages.translations.formatTranslableMessage
import world.cepi.kepi.messages.translations.translableMessage
import world.cepi.kstom.adventure.asMini

open class Help(
    name: String = "help",
    val translationLambda: (sender: CommandSender) -> String,
    vararg aliases: String = arrayOf("?")
) : Command(name, *aliases) {

    constructor(
        messages: String,
        name: String = "help", vararg aliases: String = arrayOf("?")
    ): this(name, { _ -> messages }, *aliases)

    companion object {
        const val arm = "─"
        const val armLength = 5
    }

    init {
        setDefaultExecutor { sender, _ ->
            sender.sendMessage(Component.text(arm.repeat(armLength), NamedTextColor.DARK_GRAY))

            translationLambda(sender).trim().split("\n").forEach {
                sender.sendMessage(Component.text().color(NamedTextColor.GRAY).append(it.asMini()))
            }

            sender.sendMessage(Component.text(arm.repeat(armLength), NamedTextColor.DARK_GRAY))
        }
    }

}

/**
 * Applies the help subcommand to this command
 *
 * @param name The name of the subcommand
 * @param aliases The aliases of the subcommand
 * @param shouldAddSubcommand If the subcommand should be added in the first place.
 * @param translationLambda The messages to send to the player
 */
fun Command.applyHelp(
    name: String = "help",
    vararg aliases: String = arrayOf("?"),
    shouldAddSubcommand: Boolean = true,
    translationLambda: (CommandSender) -> String
) {

    val help = Help(name, translationLambda, *aliases)

    this.defaultExecutor = help.defaultExecutor

    if (shouldAddSubcommand) this.addSubcommand(help)
}

fun Command.applyHelp(
    name: String = "help",
    vararg aliases: String = arrayOf("?"),
    shouldAddSubcommand: Boolean = true,
    translationNamespace: String,
    translationKey: String
) {

    val help = Help(name, { sender ->
        sender.translableMessage(translationNamespace, translationKey)
    }, *aliases)

    this.defaultExecutor = help.defaultExecutor

    if (shouldAddSubcommand) this.addSubcommand(help)
}