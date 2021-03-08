package world.cepi.kepi.data

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonBuilder

/**
 * Represents a storable object, anywhere.
 *
 * Has 2 qualities:
 *
 * Namespace
 * Serializable
 *
 * The [DataNamespaceForge] represents its position in whatever data space it uses.
 *
 * EX, `mob.registry.` has 2 keys
 * mob and registry,
 * and a hanging dot where an id would go.
 *
 * HIGHLY reccomended to use a kotlin Object
 *
 */
interface Model<T> {

    companion object {

        const val default = "default"

        val jsonParser = Json {

        }
    }

    /**
     * Represents where this Model is located.
     */
    val dataNamespace: DataNamespaceForge

    /**
     * Represents the model this model depends on, for optimization purposes.
     * EX, if there is a LevelModel for a Player,
     * depend on a PlayerModel.
     */
    val dependsOn: Model<*>?
        get() = null

    /**
     * Turns object T into data
     *
     * @param item The generic to turn into data
     *
     * @return A pair of ID to valid JSON data (json will be parsed to whatever)
     * Reccomended to use kotlinx.serialization.
     */
    fun asData(item: T): Pair<String, String>

    /**
     * Represents if all data returned from [asData] will have the same ID.
     * Used for optimization purposes.
     */
    val isSingleton: Boolean
        get() = false

}