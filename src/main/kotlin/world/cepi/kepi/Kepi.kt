package world.cepi.kepi

import net.minestom.server.extensions.Extension;

class Kepi : Extension() {

    override fun initialize() {
        logger.info("[Kepi] has been enabled!")
    }

    override fun terminate() {
        logger.info("[Kepi] has been disabled!")
    }

}