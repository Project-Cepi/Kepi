package world.cepi.kepi

import net.minestom.server.extensions.Extension
import world.cepi.kepi.translations.TranslationRegistry
import kotlin.io.path.ExperimentalPathApi

class Kepi : Extension() {
    @ExperimentalPathApi
    override fun initialize() {

        TranslationRegistry.grab()

        logger.info("[Kepi] has been enabled!")
    }

    override fun terminate() {
        logger.info("[Kepi] has been disabled!")
    }
}