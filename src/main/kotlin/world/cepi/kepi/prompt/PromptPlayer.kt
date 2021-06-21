package world.cepi.kepi.prompt

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ClosedReceiveChannelException
import net.kyori.adventure.text.TextComponent
import net.kyori.adventure.text.event.ClickEvent
import net.minestom.server.command.CommandSender
import net.minestom.server.entity.Player
import net.minestom.server.event.player.PlayerDisconnectEvent
import java.util.concurrent.TimeoutException

suspend fun Player.prompt(prompt: IncompletePrompt, timeout: Long = -1, throwCancelled: Boolean = false): CompletePrompt? {
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

    suspend fun recieveRendezvous(): CompletePrompt? = try {
        rendezvousChannel.receive()
    } catch (e: ClosedReceiveChannelException) {
        if (!throwCancelled) null else throw e
    }

    val deferredPrompt = coroutineScope {
        async {
            if (timeout != -1L) withTimeout(timeout) {
                recieveRendezvous()
            } else recieveRendezvous()
        }.await()
    }

    this.addEventCallback(PlayerDisconnectEvent::class.java) { rendezvousChannel.cancel() }

    return deferredPrompt
}

fun Player.promptBlocking(
    prompt: IncompletePrompt,
    timeout: Long,
    throwCancelled: Boolean = false
) = runBlocking { prompt(prompt, timeout, throwCancelled) }
fun Player.promptAsync(
    prompt: IncompletePrompt,
    timeout: Long,
    throwCancelled: Boolean = false,
    scope: CoroutineScope = GlobalScope)
= scope.async { prompt(prompt, timeout, throwCancelled) }