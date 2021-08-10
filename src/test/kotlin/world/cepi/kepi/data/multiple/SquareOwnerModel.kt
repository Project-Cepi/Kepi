package world.cepi.kepi.data.multiple

import world.cepi.kepi.data.DataNamespace
import world.cepi.kepi.data.model.JsonModel

/**
 * Model with id -- stores multiple ids
 */
object SquareOwnerModel : JsonModel<SquareOwner>(SquareOwner.serializer(), DataNamespace("owner", "square"), {
    it.id
})