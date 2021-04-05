package world.cepi.kepi.data

import world.cepi.kepi.data.database.implementations.MemoryDatabase

/**
 * Represents a data handle for permenant content data.
 *
 * This represents game objects in the game made by Game Designers.
 */
object ContentDataHandler: DataHandlerFactory({ MemoryDatabase() })