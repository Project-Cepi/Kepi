package world.cepi.kepi.command

import net.minestom.server.command.builder.Command
import world.cepi.kepi.command.subcommand.applyHelp

object TranslationCommand : Command("translation") {
    init {
        applyHelp { _ ->
            """
                Cepi supports translations with its translation
                manager! If you want to help with translations,
                
                Go to the <ylelow><click:open_url:https://github.cepi.world/Translations>Translations repository
                if you want to add your own language!
                
                Extra translation help can be found
                in the <blue>/discord
            """.trimIndent()
        }
    }
}