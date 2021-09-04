package world.cepi.kepi

import net.minestom.server.extensions.Extension
import world.cepi.kepi.command.KepiCommand
import world.cepi.kepi.messages.translations.TranslationRegistry
import world.cepi.kepi.command.HelpCommand
import world.cepi.kepi.command.RefreshCommand
import world.cepi.kepi.command.TranslationCommand
import world.cepi.kstom.Manager
import world.cepi.kstom.command.register
import world.cepi.kstom.command.unregister

class Kepi : Extension() {

    override fun initialize() {

        try {
            TranslationRegistry.grab()
        } catch (exception: Exception) {
            logger.error("An unexpected error occured loading translations.")
            Manager.exception.handleException(exception)
        }

        KepiCommand.register()
        HelpCommand.register()
        TranslationCommand.register()
        RefreshCommand.register()

        logger.info("[Kepi] has been enabled!")
    }

    override fun terminate() {

        KepiCommand.unregister()
        HelpCommand.unregister()
        TranslationCommand.unregister()
        RefreshCommand.unregister()

        logger.info("[Kepi] has been disabled!")
    }
}