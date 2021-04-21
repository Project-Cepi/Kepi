package world.cepi.kepi.data.database.implementations

import world.cepi.kepi.data.DataNamespace
import world.cepi.kepi.data.ID
import world.cepi.kepi.data.database.DatabaseHandler
import java.util.concurrent.ConcurrentHashMap

/**
 * Database located purely in memory. Should be used for testing only.
 */
class MemoryDatabase : DatabaseHandler {

    /** String (namespace) that stores (namespaces) paired to string (data) */
    val map: ConcurrentHashMap<
            DataNamespace,
            ConcurrentHashMap<DataNamespace, ConcurrentHashMap<ID, String?>>
    > = ConcurrentHashMap()

    override fun put(namespace: DataNamespace, childNamespace: DataNamespace, id: ID, data: String): Boolean {
        if (map[namespace] == null) {
            map[namespace] = ConcurrentHashMap()
        }

        if (map[namespace]!![childNamespace] == null) {
            map[namespace]!![childNamespace] = ConcurrentHashMap()
        }

        map[namespace]!![childNamespace]!![id] = data

        return true
    }

    override fun get(namespace: DataNamespace, childNamespace: DataNamespace, id: ID): String? {
        if (map[namespace] == null) {
            map[namespace] = ConcurrentHashMap()
        }

        if (map[namespace]!![childNamespace] == null) {
            map[namespace]!![childNamespace] = ConcurrentHashMap()
        }

        return map[namespace]!![childNamespace]!![id]
    }

    override fun erase(namespace: DataNamespace, childNamespace: DataNamespace, id: ID): Boolean {
        if (map[namespace] == null) {
            map[namespace] = ConcurrentHashMap()
        }

        if (map[namespace]!![childNamespace] == null) {
            map[namespace]!![childNamespace] = ConcurrentHashMap()
        }

        map[namespace]!![childNamespace]!!.remove(id)

        return true
    }
}