package world.cepi.region

import net.minestom.server.data.DataContainer
import net.minestom.server.entity.Entity
import net.minestom.server.entity.EntityType
import net.minestom.server.entity.Player
import net.minestom.server.instance.Instance
import net.minestom.server.utils.BlockPosition

/**
 * Represents a 3-dimensional non-uniform region.
 * (Though it can be uniform, if you define it like so.)
 */
interface Region : DataContainer {

    /**
     * The name of this region
     */
    val name: String

    /**
     * The [RegionPool] that contains/manages this region.
     *
     * If the region has been deleted, the value is undefined.
     */
    val pool: RegionPool

    /**
     * @return An unmodifiable collection of worlds (Minestom [Instance]s),
     * that contain at least some part of this region.
     */
    fun getWorlds(): Collection<Instance>

    /**
     * @return True, if this region contains at least one block.
     * (Has any size.) False otherwise.
     */
    fun isDefined(): Boolean

    /**
     * Calculates the volume of this region.
     *
     * @return The volume of this region in blocks.
     */
    fun getVolume(): Int

    /**
     * Checks if the given block position is inside of this
     * region in the given [Instance].
     *
     * @param pos, The block position
     * @param world, the Instance
     *
     * @return True, only if the block is inside this region.
     */
    fun isInside(pos: BlockPosition, world: Instance): Boolean

    /**
     * Adds the given selection to this region.
     *
     * @param pos1 The first corner of the selection
     * @param pos2 The second corner of the selection
     * @param world The [Instance] where the selection is supposed to be in.
     *
     * @return The amount of blocks that were added in total. This can be less than the
     * actual selection, if part of the selected area was already in the region.
     */
    fun addBlocks(pos1: BlockPosition, pos2: BlockPosition, world: Instance): Int

    /**
     * Removes the given selection from this region
     *
     * @param pos1 The first corner of the selection
     * @param pos2 The second corner of the selection
     * @param world The [Instance] where the selection is supposed to be in.
     *
     * @return The amount of blocks that were removed in total. This can be less than the
     * actual selection, if the selected area was not entirely inside the region.
     */
    fun removeBlocks(pos1: BlockPosition, pos2: BlockPosition, world: Instance): Int

    /**
     * Creates an iterator that iterates through the block
     * positions inside the given chunk that are inside this region.
     *
     * @param chunkX The chunk x-coordinate
     * @param chunkZ The chunk z-coordinate
     * @param world The [Instance] where the chunk resides.
     *
     * @return Iterator for blocks inside this region in the chunk.
     */
    fun iterateChunk(chunkX: Int, chunkZ: Int, world: Instance) : Iterator<BlockPosition>

    /**
     * Creates a collection of all the players that are
     * currently inside this region.
     *
     * @return Collection of players inside this region
     */
    fun getPlayers(): MutableCollection<Player>

    /**
     * Creates a collection of all the entities that are
     * currently inside this region.
     *
     * @return Collection of entities inside this region
     */
    fun getEntities(): MutableCollection<Entity>

    /**
     * Creates a collection of all the entities which type
     * matches at least one of the given types, and that are currently
     * inside this region.
     *
     * @param types The given entity types. Null not allowed as an member.
     *
     * @return Collection of entities inside this region with a given type
     */
    fun getEntities(vararg types: EntityType): MutableCollection<Entity>

}
