package world.cepi.kepi.data.database.implementations

import world.cepi.kepi.data.DataNamespace
import world.cepi.kepi.data.database.DatabaseHandler
import java.util.concurrent.ConcurrentHashMap

/**
 * Database located purely in memory. Should be used for testing only.
 */
class MapDatabase(
    dataNamespace: DataNamespace,
    val map: ConcurrentHashMap<String, String> = ConcurrentHashMap()
) : DatabaseHandler(dataNamespace) {

    override fun close() {

    }

    /** String (namespace) that stores (namespaces) paired to string (data) */

    override fun erase(key: String) {
        map.remove(key)
    }

    override operator fun get(key: String): String? {
        return map[key]
    }

    override operator fun set(key: String, data: String) {
        map[key] = data
    }
}