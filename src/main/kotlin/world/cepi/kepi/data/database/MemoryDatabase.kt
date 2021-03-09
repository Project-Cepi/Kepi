package world.cepi.kepi.data.database

import world.cepi.kepi.data.DataNamespaceForge
import world.cepi.kepi.data.DatabaseHandler
import world.cepi.kepi.data.ID
import java.util.concurrent.ConcurrentHashMap

/**
 * Database located purely in memory. Should be used for testing only.
 */
class MemoryDatabase : DatabaseHandler {

    /** String (namespace) that stores (namespaces) paired to string (data) */
    val map: MutableMap<
            DataNamespaceForge,
            MutableMap<DataNamespaceForge, MutableMap<ID, String>>
    > = ConcurrentHashMap()

    override fun put(namespace: DataNamespaceForge, childNamespace: DataNamespaceForge, id: ID, data: String): Boolean {
        if (map[namespace] == null) {
            map[namespace] = ConcurrentHashMap()
        }

        if (map[namespace]!![childNamespace] == null) {
            map[namespace]!![childNamespace] = ConcurrentHashMap()
        }

        map[namespace]!![childNamespace]!![id] = data

        return true
    }

    override fun erase(namespace: DataNamespaceForge, childNamespace: DataNamespaceForge, id: ID): Boolean {
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