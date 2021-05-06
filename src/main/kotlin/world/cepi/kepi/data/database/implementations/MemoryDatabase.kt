package world.cepi.kepi.data.database.implementations

import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import world.cepi.kepi.data.DataNamespace
import world.cepi.kepi.data.ID
import world.cepi.kepi.data.database.DatabaseHandler
import java.util.concurrent.ConcurrentHashMap

/**
 * Database located purely in memory. Should be used for testing only.
 */
class MemoryDatabase : DatabaseHandler<JsonElement> {

    /** String (namespace) that stores (namespaces) paired to string (data) */
    val map: ConcurrentHashMap<
            DataNamespace, ConcurrentHashMap<ID, JsonElement?>
            > = ConcurrentHashMap()


    override fun put(namespace: DataNamespace, id: ID, data: JsonElement): Boolean {
        if (map[namespace] == null) map[namespace] = ConcurrentHashMap()
        map[namespace]!![id] = data

        return true
    }

    override fun get(namespace: DataNamespace, id: ID): JsonElement? {
        return map[namespace]?.get(id)
    }

    override fun erase(namespace: DataNamespace, id: ID): Boolean {
        if (map[namespace] == null) {
            return false
        }

        map[namespace]!!.remove(id)

        return true
    }

    override fun putList(namespace: DataNamespace, id: ID, data: Collection<JsonElement>): Boolean {
        return put(namespace, id, JsonArray(data.toList()))
    }
}