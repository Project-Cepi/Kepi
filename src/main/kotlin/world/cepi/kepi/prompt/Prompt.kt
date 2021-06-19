package world.cepi.kepi.prompt

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.sendBlocking
import kotlinx.coroutines.channels.trySendBlocking
import net.kyori.adventure.text.TextComponent
import net.minestom.server.command.builder.arguments.ArgumentType
import world.cepi.kstom.command.addSyntax
import java.util.*

class Prompt(
    val playerId: UUID,
    vararg val options: PromptOption,
)

private val activePrompts: MutableMap<Prompt, Channel<Prompt>> = mutableMapOf()

class PromptOption(
    val text: TextComponent,
    val value: String
) {
    var isChosen = false
    val id: UUID = UUID.randomUUID()

    init {
        PromptCommand.addSyntax(ArgumentType.Word("choice").from(id.toString())) { sender, args ->
            val choiceId = UUID.fromString(args["choice"])
            val prompt = activePrompts.keys.firstOrNull { it.options.any { it.id == choiceId } } ?: return@addSyntax

            val newPrompt = Prompt(prompt.playerId,
                    *prompt.options.map {
                if (it.id == choiceId) { it.isChosen = true }
                it
            }.toTypedArray())

            activePrompts[prompt]?.trySendBlocking(newPrompt)

        }
    }
}