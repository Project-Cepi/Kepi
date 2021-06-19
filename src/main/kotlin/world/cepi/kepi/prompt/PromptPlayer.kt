package world.cepi.kepi.prompt

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import net.kyori.adventure.text.TextComponent
import net.kyori.adventure.text.event.ClickEvent
import net.minestom.server.command.CommandSender
import net.minestom.server.entity.Player

suspend fun CommandSender.prompt(prompt: Prompt, timeout: Long = -1): Prompt {
    val promptText = prompt.options.map {
        it.text.clickEvent(ClickEvent.runCommand("acceptPrompt ${it.id}"))
    }

    val finalPrompt = promptText[0]
    promptText.forEachIndexed { index, textComponent ->
        if (index != 0) finalPrompt.append(textComponent)
    }

    val rendezvousChannel = Channel<Prompt>(0)
    activePrompts[prompt] = rendezvousChannel

    this.sendMessage(finalPrompt)

    return rendezvousChannel.receive()
}

fun CommandSender.promptBlocking(prompt: Prompt, timeout: Long) = runBlocking { prompt(prompt, timeout) }