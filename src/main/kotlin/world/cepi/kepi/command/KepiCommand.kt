package world.cepi.kepi.command

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.event.ClickEvent
import net.kyori.adventure.text.format.NamedTextColor
import net.minestom.server.entity.Player
import net.minestom.server.tag.Tag
import world.cepi.kepi.messages.translations.TranslationRegistry
import world.cepi.kstom.command.arguments.literal
import world.cepi.kstom.command.kommand.Kommand

object KepiCommand : Kommand({

    val status by literal
    val data by literal

    syntax(status) {

        sender.sendMessage(
            Component.text("Translations: ", NamedTextColor.GRAY)
                .append(TranslationRegistry.loadingStatus.component)
        )

    }

    syntax(data) {

        val player = sender as? Player ?: return@syntax

        sender.sendMessage(
            Component.text("Click to copy your player data", NamedTextColor.GRAY)
                .clickEvent(
                    ClickEvent.copyToClipboard(
                        player.tagHandler().asCompound().toSNBT()
                    )
                )
        )
    }


}, "kepi")