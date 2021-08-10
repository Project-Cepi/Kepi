package world.cepi.kepi.data.database.implementations

import world.cepi.kepi.data.database.DatabaseHandler
import java.util.concurrent.ConcurrentHashMap

/**
 * Database located purely in memory. Should be used for testing only.
 */
class MapDatabase(
    val map: ConcurrentHashMap<String, String> = ConcurrentHashMap()
) : DatabaseHandler {

    /** String (namespace) that stores (namespaces) paired to string (data) */

    override fun erase(key: String): Boolean {
        map.remove(key)

        return true
    }

    override fun get(key: String): String? {
        return map[key]
    }

    override fun put(key: String, data: String): Boolean {
        map[key] = data

        return true
    }
}