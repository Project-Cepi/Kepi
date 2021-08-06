package world.cepi.kepi.data.database

import world.cepi.kepi.data.DataNamespace
import world.cepi.kepi.data.ID

/**
 * Represents a simple database handler. Can be hooked to anything -- local file storage, local dbs, online dbs, etc.
 */
interface DatabaseHandler<D> {

    /**
     * Puts data at a namespace + id
     *
     * @param namespace The namespace to put the [data] at.
     * @param id The ID of the object.
     * @param data The data as JSON (string)
     */
    fun put(namespace: DataNamespace, id: ID, data: D): Boolean

    fun putList(namespace: DataNamespace, id: ID, data: Collection<D>): Boolean

    /**
     * Erase data from a namespace + id
     *
     * @param namespace The namespace to erase the data from.
     * @param id The ID of the object.
     * @param childSpace The namespace in the [id]. Meant to stop namespace cloning.
     */
    fun erase(namespace: DataNamespace, id: ID): Boolean

    /**
     * Grabs data from a namespace + id
     *
     * @param namespace The namespace to get the data from.
     * @param id The ID of the object.
     * @param childSpace The namespace in the [id]. Meant to stop namespace cloning.
     *
     * @return The data at that space, null if not exists
     */
    fun get(namespace: DataNamespace, id: ID): D?

}