package world.cepi.kepi

import net.minestom.server.MinecraftServer
import net.minestom.server.extensions.Extension
import world.cepi.kepi.command.KepiCommand
import world.cepi.kepi.messages.translations.TranslationRegistry
import kotlin.io.path.ExperimentalPathApi

class Kepi : Extension() {

    override fun initialize() {

        try {
            TranslationRegistry.grab()
        } catch (exception: Exception) {
            logger.error("An unexpected error occured loading translations.")
            MinecraftServer.getExceptionManager().handleException(exception)
        }

        MinecraftServer.getCommandManager().register(KepiCommand)

        logger.info("[Kepi] has been enabled!")
    }

    override fun terminate() {

        MinecraftServer.getCommandManager().unregister(KepiCommand)

        logger.info("[Kepi] has been disabled!")
    }
}