package world.cepi.kepi.data.databaseimpls

import world.cepi.kepi.data.DataNamespaceForge
import world.cepi.kepi.data.DatabaseHandler
import world.cepi.kepi.data.ID
import java.util.concurrent.ConcurrentHashMap

/**
 * Database located purely in memory. Should be used for testing only.
 */
class MemoryDatabase : DatabaseHandler {

    /** String (namespace) that stores (namespaces) paired to string (data) */
    val map: MutableMap<String, MutableMap<String, String>> = ConcurrentHashMap()

    override fun put(namespace: DataNamespaceForge, id: ID, data: String): Boolean {
        if (map[namespace.toString()] == null) map[namespace.toString()] = ConcurrentHashMap()
        map[namespace.toString()]?.put(id.id, data)

        return true
    }

    override fun erase(namespace: DataNamespaceForge, id: ID): Boolean {
        if (map[namespace.toString()] == null) map[namespace.toString()] = ConcurrentHashMap()
        map[namespace.toString()]?.remove(id.id)

        return true
    }
}