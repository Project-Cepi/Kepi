package world.cepi.data

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

    operator fun plus(key: String): DataNamespaceForge =
        DataNamespaceForge(keys + key)

    override fun toString(): String {
        return "${keys.joinToString(".")}::"
    }

}