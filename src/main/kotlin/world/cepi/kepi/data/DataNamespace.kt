package world.cepi.kepi.data

import javax.xml.crypto.Data

/**
 * Represents a data namespace, its "string" format is in the format of a Skript variable.
 *
 * EX:
 *
 * mob.registry
 *
 * mob.registry is the namespace, nested in (registry of (mob)),
 */
@JvmInline
value class DataNamespace(
    /**
     * List of keys, in the example above it would be
     *
     * > mob
     * > registry
     */
    val keys: List<String> = listOf()
) {

    constructor(vararg keys: String): this(keys.toList())

    operator fun plus(key: String): DataNamespace =
        DataNamespace(keys + key)

    operator fun plus(namespace: DataNamespace): DataNamespace =
        DataNamespace(keys + namespace.keys)

    override fun toString(): String {
        return keys.joinToString(".")
    }

}

fun String.asNamespace() = DataNamespace(this.split("."))