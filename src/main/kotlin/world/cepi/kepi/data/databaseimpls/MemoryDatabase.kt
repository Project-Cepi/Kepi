package world.cepi.kepi.data.databaseimpls

import world.cepi.kepi.data.DataNamespaceForge
import world.cepi.kepi.data.DatabaseHandler

/**
 * Database located purely in memory. Should be used for testing only.
 */
class MemoryDatabase : DatabaseHandler {

    /** String (namespace) that stores (namespaces) paired to string (data) */
    val map: MutableMap<String, MutableMap<String, String>> = mutableMapOf()

    override fun put(namespace: DataNamespaceForge, id: String, data: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun erase(namespace: DataNamespaceForge, id: String): Boolean {
        TODO("Not yet implemented")
    }
}