package world.cepi.kepi.data

/**
 * Represents a data namespace, its "string" format is in the format of a Skript variable.
 *
 * EX:
 *
 * mob.registry
 *
 * mob.registry is the namespace, nested in (registry of (mob)),
 */
open class DataNamespaceForge(
    /**
     * List of keys, in the example above it would be
     *
     * > mob
     * > registry
     */
    val keys: List<String> = listOf()
) {

    constructor(vararg keys: String): this(keys.toList())

    operator fun plus(key: String): DataNamespaceForge =
        DataNamespaceForge(keys + key)

    override fun toString(): String {
        return keys.joinToString(".")
    }

}