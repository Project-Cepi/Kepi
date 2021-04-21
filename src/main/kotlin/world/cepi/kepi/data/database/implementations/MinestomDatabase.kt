package world.cepi.kepi.data.database.implementations

import net.minestom.server.MinecraftServer
import net.minestom.server.storage.systems.FileStorageSystem
import world.cepi.kepi.data.DataNamespace
import world.cepi.kepi.data.ID
import world.cepi.kepi.data.database.DatabaseHandler
import java.util.concurrent.ConcurrentHashMap

/**
 * Database located purely in memory. Should be used for testing only.
 */
class MinestomDatabase(uniqueID: ID) : DatabaseHandler {

    val storage = MinecraftServer.getStorageManager().getLocation(uniqueID.id)!!

    override fun put(namespace: DataNamespace, childNamespace: DataNamespace, id: ID, data: String): Boolean {
        storage.set((namespace + childNamespace + id.id).toString(), data.encodeToByteArray())
        return true
    }

    override fun erase(namespace: DataNamespace, childNamespace: DataNamespace, id: ID): Boolean {
        storage.delete((namespace + childNamespace + id.id).toString())
        return true
    }

    override fun get(namespace: DataNamespace, childNamespace: DataNamespace, id: ID): String? {
        return storage.get((namespace + childNamespace + id.id).toString())?.let { String(it) }
    }
}