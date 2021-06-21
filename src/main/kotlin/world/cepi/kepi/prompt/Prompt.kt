package world.cepi.kepi.prompt

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.sendBlocking
import kotlinx.coroutines.channels.trySendBlocking
import net.kyori.adventure.text.TextComponent
import net.minestom.server.command.CommandSender
import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.entity.Player
import world.cepi.kstom.command.addSyntax
import java.util.*

class IncompletePrompt(
    vararg val options: PromptOption,
) {
    val id = UUID.randomUUID()

    init {
        options.forEachIndexed { index, option -> option.optionNumber = index }

        val choiceArgument = ArgumentType.Integer("choice")
            .between(options.first().optionNumber!!, options.last().optionNumber!!)

        PromptCommand.addSyntax(choiceArgument) { sender, args ->
            if (sender.isConsole) return@addSyntax
            val player = sender as Player

            val choiceId: Int = args[choiceArgument]
            val prompt = player.activePrompt ?: return@addSyntax
            val chosenOption = prompt.options.first { it.optionNumber == choiceId }

            val newPrompt = CompletePrompt(
                chosenOption,
                sender.asPlayer(),
                prompt.options.filter { it != chosenOption }
            )

            activePrompts[prompt]?.trySendBlocking(newPrompt)
        }
    }

}

class CompletePrompt (
    val chosenOption: PromptOption,
    val chooser: Player,
    val otherOptions: List<PromptOption>
)

internal val activePrompts: MutableMap<IncompletePrompt, Channel<CompletePrompt>> = mutableMapOf()

private val playerPrompts: MutableMap<Player, IncompletePrompt> = mutableMapOf()
var Player.activePrompt: IncompletePrompt?
    get() = playerPrompts[this]
    set(value) { if (value != null) playerPrompts[this] = value }

class PromptOption(
    val text: TextComponent,
    val value: String
) {
    var optionNumber: Int? = null
}