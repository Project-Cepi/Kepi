package world.cepi.kepi

import world.cepi.Service

/**
 * An object that holds all the implementations for some service.
 *
 * @param S The [Service]
 */
interface ImplementationMapper<S : Service> {

    /**
     * Checks if this mapper has any implementations.
     *
     * If the service's mapper is empty, then the service has
     * not yet been implemented.
     *
     * @return True, if this mapper is empty, false otherwise.
     */
    fun isEmpty(): Boolean {
        return size() == 0
    }

    /**
     * The amount of implementations this mapper contains for
     * the service.
     */
    val size: Int

    /**
     * The primary implementation of service. Typically the
     * first implementation that was loaded for the service.
     *
     * Null, if there is no implementations in this mapper.
     *
     * @see [isEmpty]
     */
    val primary: S?

    /**
     * A map representation of the implementations in this mapper,
     * where the name of the implementation as specified by
     * [Service.implementationName] is the key and the implementation
     * itself is the value.
     */
    val asMap: Map<String, S>

    /**
     * A collection representation of the implementations in this
     * mapper, where the values are the implementations.
     */
    val asCollection: Collection<S>

}