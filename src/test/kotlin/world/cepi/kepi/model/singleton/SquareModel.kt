package world.cepi.kepi.model.singleton

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import world.cepi.kepi.data.DataNamespaceForge
import world.cepi.kepi.data.ID
import world.cepi.kepi.data.Model
import world.cepi.kepi.data.Model.Companion.defaultID
import world.cepi.kepi.data.Model.Companion.jsonParser

/**
 * Singleton model -- storing a `Square` would only store this one instance of the square at the same ID.
 */
object SquareModel : Model<Square> {

    override val dataNamespace: DataNamespaceForge = DataNamespaceForge("shape", "sqare")

    override fun asData(item: Square): Pair<ID, String> {
        return defaultID to jsonParser.encodeToString(item)
    }

    override fun asObject(data: String): Square {
        return jsonParser.decodeFromString(data)
    }

    override val isSingleton = true

}