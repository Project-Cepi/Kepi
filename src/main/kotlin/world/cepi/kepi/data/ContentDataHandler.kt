package world.cepi.kepi.data

import kotlinx.serialization.json.JsonElement
import world.cepi.kepi.data.database.implementations.MapDatabase

/**
 * Represents a data handle for permenant content data.
 *
 * This represents game objects in the game made by Game Designers.
 */
object ContentDataHandler: DataHandlerFactory({ MapDatabase() })