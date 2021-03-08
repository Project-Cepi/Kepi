package world.cepi.kepi.data.databaseimpls

import world.cepi.kepi.data.DataNamespaceForge
import world.cepi.kepi.data.DatabaseHandler

/**
 * Database located purely in memory. Should be used for testing only.
 */
class MemoryDatabase : DatabaseHandler {
    override fun put(namespace: DataNamespaceForge, id: String, data: String, subForge: DataNamespaceForge): Boolean {
        TODO("Not yet implemented")
    }

    override fun erase(namespace: DataNamespaceForge, id: String, subForge: DataNamespaceForge): Boolean {
        TODO("Not yet implemented")
    }
}