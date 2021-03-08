package world.cepi.kepi.data

/**
 * Represents a simple database handler. Can be hooked to anything -- local file storage, local dbs, online dbs, etc.
 */
interface DatabaseHandler {

    /**
     * Puts data at a namespace + id
     *
     * @param forge The namespace to put the [data] at.
     * @param id The ID of the object.
     * @param data The data as JSON (string)
     */
    fun put(forge: DataNamespaceForge, id: String, data: String, subForge: DataNamespaceForge = DataNamespaceForge()): Boolean

    /**
     * Erase data from a namespace + id
     *
     * @param forge The namespace to put the [data] at.
     * @param id The ID of the object.
     */
    fun erase(forge: DataNamespaceForge, id: String): Boolean

}