package world.cepi.kepi.data.database

import net.minestom.server.utils.NamespaceID
import world.cepi.kepi.data.DataNamespace

/**
 * Represents a simple database handler. Can be hooked to anything -- local file storage, local dbs, online dbs, etc.
 */
abstract class DatabaseHandler(val namespace: DataNamespace) : AutoCloseable {

    /**
     * Puts data at a namespace + id
     *
     * @param key the key of the data
     * @param data The data as JSON (string)
     */
    abstract operator fun set(key: String, data: String)

    /**
     * Erase data from a namespace + id
     *
     * @param key the key of the data
     */
    abstract fun erase(key: String)

    /**
     * Grabs data from a namespace + id
     *
     * @param key The key to grab the data from
     *
     * @return The data at that space, null if not exists
     */
    abstract operator fun get(key: String): String?

}