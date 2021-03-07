package world.cepi.kepi

/**
 * Thrown when a service that is not implemented is requested.
 *
 * @param message The message used as the reason for this exception
 */
class UnsupportedServiceException(override val message: String) : RuntimeException(message)