package world.cepi.kepi.data.singleton

import world.cepi.kepi.data.DataNamespace
import world.cepi.kepi.data.model.XSerializableModel

/**
 * Singleton model -- storing a `Square` would only store this one instance of the square at the same ID.
 */
object SquareModel : XSerializableModel<Square>(Square::class, true) {
    override val dataNamespace: DataNamespace = DataNamespace("shape", "sqare")

}