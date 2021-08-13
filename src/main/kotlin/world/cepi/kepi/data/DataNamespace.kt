package world.cepi.kepi.data

import javax.xml.crypto.Data

/**
 * Represents a data namespace, its "string" format is in the format of a Skript variable.
 *
 * EX:
 *
 * mob_registry
 *
 * mob_registry is the namespace, nested in (registry of (mob)),
 */
class DataNamespace(
    /**
     * List of keys, in the example above it would be
     *
     * > mob
     * > registry
     */
    vararg val keys: String
) {

    constructor(keys: List<String>): this(*keys.toTypedArray())

    operator fun plus(key: String): DataNamespace =
        DataNamespace(*keys, key)

    operator fun plus(namespace: DataNamespace): DataNamespace =
        DataNamespace(*keys, *namespace.keys)

    override fun equals(other: Any?) = keys == other

    override fun toString(): String {
        return keys.joinToString("_")
    }

    override fun hashCode(): Int {
        return keys.contentHashCode()
    }

}

fun String.asNamespace() = DataNamespace(this.split("_"))

infix fun DataNamespace.with(id: String) = this.toString() + id
