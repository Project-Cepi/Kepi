package world.cepi.kepi.impl1_0

import net.minestom.server.extensions.Extension
import world.cepi.kepi.Kepi
import world.cepi.kepi.ServiceProvider

class ServiceExtension : Extension() {

    private val serviceManager: KepiServiceManager = KepiServiceManager()

    init {
        /*
         * Insert the freshly created service provider to the Kepi object so
         * it can be accessed conveniently.
         */
        Kepi.provider = serviceManager

        /*
         * Register the created service provider as an implementation for ServiceProvider,
         * because why not?
         */
        serviceManager.implementService(ServiceProvider::class, serviceManager)
    }

    override fun initialize() {

    }

    override fun terminate() {

    }

}