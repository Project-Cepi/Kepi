package world.cepi.kepi.command

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.event.ClickEvent
import net.kyori.adventure.text.format.NamedTextColor
import net.minestom.server.command.builder.Command
import net.minestom.server.entity.Player
import net.minestom.server.tag.Tag
import world.cepi.kepi.messages.translations.TranslationRegistry
import world.cepi.kstom.command.addSyntax
import world.cepi.kstom.command.arguments.literal

object KepiCommand : Command("kepi") {

    init {
        val status = "status".literal()
        val data = "data".literal()

        addSyntax(status) {

            sender.sendMessage(
                Component.text("Translations: ", NamedTextColor.GRAY)
                    .append(TranslationRegistry.loadingStatus.component)
            )

        }

        addSyntax(data) {

            val player = sender as? Player ?: return@addSyntax

            sender.sendMessage(
                Component.text("Click to copy your player data", NamedTextColor.GRAY)
                    .clickEvent(
                        ClickEvent.copyToClipboard(
                            player.getTag(Tag.NBT)?.toSNBT() ?: "{}"
                        )
                    )
            )
        }
    }

}