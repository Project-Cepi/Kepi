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
)

@Deprecated("Use the actual IncompletePrompt instead")
internal typealias Prompt = IncompletePrompt

class CompletePrompt (
    val chosenOption: PromptOption,
    val chooser: Player,
    val otherOptions: List<PromptOption>
)

internal val activePrompts: MutableMap<IncompletePrompt, Channel<CompletePrompt>> = mutableMapOf()

class PromptOption(
    val text: TextComponent,
    val value: String
) {
    var isChosen = false
    val id: UUID = UUID.randomUUID()

    init {
        PromptCommand.addSyntax(ArgumentType.Word("choice").from(id.toString())) { sender, args ->
            if (sender.isConsole) return@addSyntax

            val choiceId = UUID.fromString(args["choice"])
            val prompt = activePrompts.keys.firstOrNull { it.options.any { it.id == choiceId } } ?: return@addSyntax
            val chosenOption = prompt.options.first { it.id == choiceId }

            val newPrompt = CompletePrompt(
                chosenOption,
                sender.asPlayer(),
                prompt.options.filter { it != chosenOption }
            )

            activePrompts[prompt]?.trySendBlocking(newPrompt)

        }
    }
}