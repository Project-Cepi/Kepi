package world.cepi.kepi.data.model

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import world.cepi.kepi.data.DataNamespace
import world.cepi.kepi.data.ID
import world.cepi.kepi.data.asNamespace

/**
 * Represents a storable object, anywhere.
 *
 * Has 2 qualities:
 *
 * Namespace
 * Serializable
 *
 * The [DataNamespace] represents its position in whatever data space it uses.
 *
 * EX, `mob.registry.` has 2 keys
 * mob and registry,
 * and a hanging dot where an id would go.
 *
 * HIGHLY reccomended to use a kotlin Object
 *
 */
interface Model<T, D> {

    companion object {

        const val default = "default"
        val defaultID = ID(default)
        val defaultNamespace = default.asNamespace()

        val jsonParser = Json {

        }
    }

    /**
     * Represents where this Model is located.
     */
    val dataNamespace: DataNamespace

    /**
     * Represents the model this model depends on, for optimization purposes.
     * EX, if there is a LevelModel for a Player,
     * depend on a PlayerModel.
     */
    val dependsOn: Model<*, *>?
        get() = null

    /**
     * Turns object T into data
     *
     * @param item The generic to turn into data
     *
     * @return A pair of ID to valid JSON data (json will be parsed to whatever)
     * Reccomended to use kotlinx.serialization.
     */
    fun asData(item: T): Pair<ID, D>

    /**
     * Data into object T
     *
     * @param data the JSON data to turn into a [T]
     *
     * @return The [T] object.
     * Reccomended to use kotlinx.serialization.
     */
    fun asObject(data: D): T

    /**
     * Represents if all data returned from [asData] will have the same ID.
     * Used for optimization purposes.
     */
    val isSingleton: Boolean
        get() = false

}