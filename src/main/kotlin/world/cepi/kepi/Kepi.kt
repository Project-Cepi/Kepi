package world.cepi.kepi

import net.kyori.adventure.text.Component
import net.minestom.server.extensions.Extension
import world.cepi.kepi.command.*
import world.cepi.kepi.messages.translations.TranslationRegistry
import world.cepi.kstom.Manager
import world.cepi.kstom.command.arguments.generation.CallbackGenerator
import world.cepi.kstom.command.register
import world.cepi.kstom.command.unregister

class Kepi : Extension() {

    override fun initialize() {

        CallbackGenerator.errorSymbol = Component.text("\uE006")

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
        BlockHandlerCommand.register()
        YardCommand.register()

        logger.info("[Kepi] has been enabled!")
    }

    override fun terminate() {

        KepiCommand.unregister()
        HelpCommand.unregister()
        TranslationCommand.unregister()
        RefreshCommand.unregister()
        BlockHandlerCommand.unregister()
        YardCommand.unregister()

        logger.info("[Kepi] has been disabled!")
    }
}