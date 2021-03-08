package world.cepi.kepi

import kotlinx.serialization.encodeToString
import world.cepi.kepi.data.DataNamespaceForge
import world.cepi.kepi.data.Model
import world.cepi.kepi.data.Model.Companion.jsonParser

class SquareModel : Model<Square> {

    override val dataNamespace: DataNamespaceForge = DataNamespaceForge("shape", "sqare")

    override fun asData(item: Square): Pair<String, String> {
        return "baseShape" to jsonParser.encodeToString(item)
    }

}