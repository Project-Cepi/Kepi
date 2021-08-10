package world.cepi.kepi.data.multiple

import kotlinx.serialization.Serializable
import world.cepi.kepi.data.singleton.Square

/**
 * Owner of a square object
 */
@Serializable
data class SquareOwner(val id: String, val square: Square)