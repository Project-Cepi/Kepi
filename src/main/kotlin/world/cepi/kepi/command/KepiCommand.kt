package world.cepi.kepi.command

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.minestom.server.command.builder.Command
import world.cepi.kepi.messages.translations.TranslationRegistry
import world.cepi.kstom.command.addSyntax
import world.cepi.kstom.command.arguments.literal

object KepiCommand : Command("kepi") {

    init {
        val status = "status".literal()

        addSyntax(status) { sender ->

            sender.sendMessage(
                Component.text("Translations: ", NamedTextColor.GRAY)
                    .append(TranslationRegistry.loadingStatus.component)
            )

        }
    }

}