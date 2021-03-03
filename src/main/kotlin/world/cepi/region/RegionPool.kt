package world.cepi.region

/**
 * Represents a collection of [Region]s. Regions in different pools
 * can overlap, but regions inside the same pool cannot.
 *
 * Regions in the same pool also need to have unique names.
 */
interface RegionPool {

    /**
     * The name of this region pool
     */
    val name: String

    /**
     * A collection of all the [Region]s in this pool
     */
    val regions: Collection<Region>

    /**
     * The amount of [Region]s in this pool
     */
    val size: Int
        get() = regions.size

    /**
     * Checks if a given [Region] resides inside this
     * pool.
     *
     * @param region The given region
     *
     * @return True, if given region is inside this pool.
     * False otherwise.
     */
    fun contains(region: Region): Boolean

    /**
     * Gets the [Region] inside this pool with the given name.
     *
     * @param name The given name
     *
     * @return The region inside this pool with that name,
     * or null, if it doesn't exist.
     */
    fun getRegion(name: String): Region?

    /**
     * Creates a new [Region] inside this region pool.
     *
     * The creates region doesn't have any volume to begin with.
     *
     * @param name The unique name of the region
     *
     * @return The created region
     *
     * @throws IllegalStateException If the name provided was not unique.
     */
    fun createRegion(name: String): Region

    /**
     * Removes a given [Region] from this pool.
     *
     * Calling methods of a removed region or using it as a
     * parameter will throw an exception.
     *
     * @param region The given region
     *
     * @throws IllegalStateException If the given region was not part of
     * this pool.
     */
    fun remove(region: Region)

}
