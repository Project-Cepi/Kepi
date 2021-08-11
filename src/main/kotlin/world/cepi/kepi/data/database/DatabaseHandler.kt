package world.cepi.kepi.data.database

/**
 * Represents a simple database handler. Can be hooked to anything -- local file storage, local dbs, online dbs, etc.
 */
interface DatabaseHandler {

    /**
     * Puts data at a namespace + id
     *
     * @param key the key of the data
     * @param data The data as JSON (string)
     */
    fun put(key: String, data: String)

    /**
     * Erase data from a namespace + id
     *
     * @param key the key of the data
     */
    fun erase(key: String)

    /**
     * Grabs data from a namespace + id
     *
     * @param key The key to grab the data from
     *
     * @return The data at that space, null if not exists
     */
    fun get(key: String): String?

}