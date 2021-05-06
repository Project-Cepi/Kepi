package world.cepi.kepi.data.database.implementations

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import net.minestom.server.MinecraftServer
import net.minestom.server.storage.systems.FileStorageSystem
import world.cepi.kepi.data.DataNamespace
import world.cepi.kepi.data.ID
import world.cepi.kepi.data.database.DatabaseHandler
import java.util.concurrent.ConcurrentHashMap

/**
 * Database located purely in memory. Should be used for testing only.
 */
class MinestomDatabase(uniqueID: ID) : DatabaseHandler<JsonElement> {

    val storage = MinecraftServer.getStorageManager().getLocation(uniqueID.id)!!

    override fun put(namespace: DataNamespace, id: ID, data: JsonElement): Boolean {
        storage.set((namespace + id.id).toString(), data.toString().encodeToByteArray())
        return true
    }

    override fun erase(namespace: DataNamespace, id: ID): Boolean {
        storage.delete((namespace + id.id).toString())
        return true
    }

    override fun get(namespace: DataNamespace, id: ID): JsonElement? {
        return storage.get((namespace + id.id).toString())?.let { Json.parseToJsonElement(String(it)) }
    }
}