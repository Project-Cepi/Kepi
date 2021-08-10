package world.cepi.kepi.data

import kotlinx.serialization.json.JsonElement
import world.cepi.kepi.data.database.implementations.MapDatabase

/**
 * Represents a data handle for the game.
 * Meant to store per-game data.
 */
object GameDataHandler: DataHandlerFactory({ MapDatabase() })