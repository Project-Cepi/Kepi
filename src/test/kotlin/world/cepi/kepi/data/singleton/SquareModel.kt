package world.cepi.kepi.data.singleton

import world.cepi.kepi.data.DataNamespaceForge
import world.cepi.kepi.data.model.XSerializableModule

/**
 * Singleton model -- storing a `Square` would only store this one instance of the square at the same ID.
 */
object SquareModel : XSerializableModule<Square>(Square::class, true) {
    override val dataNamespace: DataNamespaceForge = DataNamespaceForge("shape", "sqare")

}