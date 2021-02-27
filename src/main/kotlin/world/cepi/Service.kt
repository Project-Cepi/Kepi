package world.cepi

/**
 * Represents a root class of some Cepi service.
 */
interface Service {

    /**
     * The version of the service.
     *
     * This value must be constant.
     */
    val version: String

    /**
     * The name of the service implementation.
     *
     * This value must be constant.
     */
    val implementationName: String

}