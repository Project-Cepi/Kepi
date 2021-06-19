package world.cepi.kepi.prompt

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import net.kyori.adventure.text.TextComponent
import net.kyori.adventure.text.event.ClickEvent
import net.minestom.server.command.CommandSender
import net.minestom.server.entity.Player

suspend fun Player.prompt(prompt: IncompletePrompt, timeout: Long = -1): CompletePrompt {
    val promptText = prompt.options.map {
        it.text.clickEvent(ClickEvent.runCommand("acceptPrompt ${it.id}"))
    }

    val finalPrompt = promptText[0]
    promptText.forEachIndexed { index, textComponent ->
        if (index != 0) finalPrompt.append(textComponent)
    }

    val rendezvousChannel = Channel<CompletePrompt>(0)
    activePrompts[prompt] = rendezvousChannel

    this.sendMessage(finalPrompt)

    return rendezvousChannel.receive()
}

fun Player.promptBlocking(prompt: IncompletePrompt, timeout: Long) = runBlocking { prompt(prompt, timeout) }