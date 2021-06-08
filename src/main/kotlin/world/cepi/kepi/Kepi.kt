package world.cepi.kepi

import net.minestom.server.MinecraftServer
import net.minestom.server.extensions.Extension
import world.cepi.kepi.command.KepiCommand
import world.cepi.kepi.messages.translations.TranslationRegistry
import world.cepi.kepi.subcommands.HelpCommand
import world.cepi.kstom.command.register
import world.cepi.kstom.command.unregister
import kotlin.io.path.ExperimentalPathApi

class Kepi : Extension() {

    override fun initialize() {

        try {
            TranslationRegistry.grab()
        } catch (exception: Exception) {
            logger.error("An unexpected error occured loading translations.")
            MinecraftServer.getExceptionManager().handleException(exception)
        }

        KepiCommand.register()
        HelpCommand.register()

        logger.info("[Kepi] has been enabled!")
    }

    override fun terminate() {

        KepiCommand.unregister()
        HelpCommand.unregister()

        logger.info("[Kepi] has been disabled!")
    }
}