package world.cepi.kepi.data.singleton

import world.cepi.kepi.data.DataNamespace
import world.cepi.kepi.data.model.JsonModel

/**
 * Singleton model -- storing a `Square` would only store this one instance of the square at the same ID.
 */

object SquareModel : JsonModel<Square>(Square.serializer(), DataNamespace("shape", "square"), isSingleton = true)