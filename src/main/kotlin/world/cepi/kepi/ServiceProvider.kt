package world.cepi.kepi

import world.cepi.Service
import kotlin.reflect.KClass

/**
 * The Kepi service manager.
 *
 * Keeps track of all the implementations for different services in Cepi.
 */
interface ServiceProvider : Service {

    /**
     * Checks if there is at least one implementation available for the
     * given service.
     *
     * @param service The service's interface
     *
     * @return True, if there is an implementation available, false otherwise.
     */
    fun has(service: KClass<out Service>): Boolean

    /**
     * Fetches the primary implementation for the given service.
     *
     * @param service The service's interface
     *
     * @return The primary implementation for the service
     *
     * @throws UnsupportedServiceException If implementation for the
     * service is not available.
     */
    fun fetch(service: KClass<out Service>): Service

    /**
     * Gets all the implementations for the given service. The collection of
     * the implementations will be updated whenever an implementation is loaded
     * or unloaded.
     *
     * @param service The service's interface
     *
     * @return The implementations for the service in [ImplementationMapper] form.
     */
    fun getImplementations(service: KClass<out Service>): ImplementationMapper

    /**
     * Adds a new implementation for the given service.
     *
     * @param service The service's interface
     * @param implementation The implementation for the service
     *
     * @return True, if this was the first implementation for the given service,
     * false otherwise.
     *
     * @throws IllegalStateException If there already is an implementation for
     * the service with the same name as this.
     */
    fun implementService(service: KClass<out Service>, implementation: Service): Boolean

}