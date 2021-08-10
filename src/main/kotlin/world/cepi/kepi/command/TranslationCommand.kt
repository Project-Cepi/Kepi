package world.cepi.kepi.command

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextColor
import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.command.builder.exception.ArgumentSyntaxException
import world.cepi.kepi.command.subcommand.applyHelp
import world.cepi.kepi.messages.sendFormattedMessage
import world.cepi.kepi.messages.translations.TranslationRegistry
import world.cepi.kstom.command.addSyntax
import world.cepi.kstom.command.arguments.literal

object TranslationCommand : Command("translation") {
    init {
        applyHelp {
            """
                Cepi supports translations with its translation
                manager! If you want to help with translations,
                
                Go to the <green><underlined><click:open_url:https://github.cepi.world/Translations>Translations repository
                if you want to add your own language!
                
                Extra translation help can be found
                in the <blue>/discord
            """.trimIndent()
        }

        val get = "get".literal()

        val translation = ArgumentType.Word("translationKey").map {

            if (!it.matches(Regex("^\\w+:[\\w.]+"))) throw ArgumentSyntaxException("Not a valid pattern!", it, 1)

            TranslationRegistry[it.split(":")[0], it.split(":")[1], "en_us"]
        }.also {
            it.setCallback { sender, exception ->
                sender.sendFormattedMessage(Component.text("Invalid translation! Usage: namespace:some.key"))
            }
        }

        addSyntax(get, translation) {
            sender.sendMessage(
                Component.text("${context.getRaw(translation)} ", NamedTextColor.YELLOW)
                    .append(Component.text("/ ", NamedTextColor.GOLD))
                    .append(Component.text(context[translation] ?: "translation not found", TextColor.color(242, 242, 174)))
            )
        }
    }
}