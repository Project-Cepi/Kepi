package world.cepi.kepi.model.singleton

import kotlinx.serialization.encodeToString
import world.cepi.kepi.data.DataNamespaceForge
import world.cepi.kepi.data.Model
import world.cepi.kepi.data.Model.Companion.default
import world.cepi.kepi.data.Model.Companion.jsonParser

/**
 * Singleton model -- storing a `Square` would only store this one instance of the square at the same ID.
 */
object SquareModel : Model<Square> {

    override val dataNamespace: DataNamespaceForge = DataNamespaceForge("shape", "sqare")

    override fun asData(item: Square): Pair<String, String> {
        return default to jsonParser.encodeToString(item)
    }

    override val isSingleton = true

}