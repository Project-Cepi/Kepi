package world.cepi.kepi.command.subcommand

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.minestom.server.command.CommandSender
import net.minestom.server.command.builder.Command
import world.cepi.kepi.messages.translations.translableMessage
import world.cepi.kstom.adventure.asMini
import world.cepi.kstom.command.kommand.Kommand

open class Help(
    val translationLambda: (sender: CommandSender) -> String,
) : Command("help", "?") {

    constructor(
        messages: String,
    ): this({ _ -> messages })

    constructor(
        namespace: String, key: String,
    ): this({ sender -> sender.translableMessage(namespace, key) })

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
 * @param translationLambda The messages to send to the player
 * @param shouldAddSubcommand If the subcommand should be added in the first place.
 */
fun Kommand.applyHelp(
    shouldAddSubcommand: Boolean = true,
    translationLambda: (CommandSender) -> String
) {

    val help = Help(translationLambda)

    this.command.defaultExecutor = help.defaultExecutor

    if (shouldAddSubcommand) this.command.addSubcommand(help)
}

fun Kommand.applyHelp(
    translationNamespace: String,
    translationKey: String,
    shouldAddSubcommand: Boolean = true,
) {

    val help = Help(translationNamespace, translationKey)

    this.command.defaultExecutor = help.defaultExecutor

    if (shouldAddSubcommand) this.command.addSubcommand(help)
}