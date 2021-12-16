package world.cepi.kepi.command

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.event.ClickEvent
import net.kyori.adventure.text.format.NamedTextColor
import world.cepi.kepi.messages.sendFormattedMessage
import world.cepi.kstom.command.kommand.Kommand

object YardCommand : Kommand({

    default {
        sender.sendFormattedMessage(
            Component.text("Yard (general documentation on all of cepi): ")
                .append(
                    Component.text("https://project-cepi.github.io", NamedTextColor.YELLOW)
                        .clickEvent(ClickEvent.openUrl("https://project-cepi.github.io"))
                )
                .append(Component.text(")", NamedTextColor.GRAY))
        )
    }

}, "yard")