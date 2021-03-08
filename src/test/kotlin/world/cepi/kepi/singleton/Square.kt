package world.cepi.kepi.singleton

import kotlinx.serialization.Serializable

/**
 * Simple square model for non-id based data.
 */
@Serializable
class Square(val width: Int, val height: Int)