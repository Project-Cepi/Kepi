package world.cepi.kepi.impl1_0

import net.minestom.server.extensions.Extension
import world.cepi.kepi.Kepi

class ServiceExtension : Extension() {

    init {
        /*
         * Register the created service provider as an implementation for ServiceProvider,
         * because why not?
         */
        Kepi.implementService(Kepi::class, Kepi)
    }

    override fun initialize() {
        logger.info("[Kepi] has been enabled!")
    }

    override fun terminate() {
        logger.info("[Kepi] has been disabled!")
    }

}