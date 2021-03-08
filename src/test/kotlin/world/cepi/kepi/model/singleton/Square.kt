package world.cepi.kepi.model.singleton

import kotlinx.serialization.Serializable

/**
 * Simple square model for non-id based data.
 */
@Serializable
data class Square(val width: Int, val height: Int)