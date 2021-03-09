package world.cepi.kepi.data.database

import world.cepi.kepi.data.DataNamespaceForge
import world.cepi.kepi.data.ID

/**
 * Represents a simple database handler. Can be hooked to anything -- local file storage, local dbs, online dbs, etc.
 */
interface DatabaseHandler {

    /**
     * Puts data at a namespace + id
     *
     * @param namespace The namespace to put the [data] at.
     * @param id The ID of the object.
     * @param data The data as JSON (string)
     */
    fun put(namespace: DataNamespaceForge, childNamespace: DataNamespaceForge, id: ID, data: String): Boolean

    /**
     * Erase data from a namespace + id
     *
     * @param namespace The namespace to put the [data] at.
     * @param id The ID of the object.
     * @param childSpace The namespace in the [id]. Meant to stop namespace cloning.
     */
    fun erase(namespace: DataNamespaceForge, childNamespace: DataNamespaceForge, id: ID): Boolean

}