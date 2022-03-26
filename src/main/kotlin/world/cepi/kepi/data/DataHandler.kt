package world.cepi.kepi.data

import world.cepi.kepi.data.database.DatabaseHandler
import world.cepi.kepi.data.model.Model
import java.lang.Integer.parseInt

/**
 * Handles data with a backing [DatabaseHandler]
 *
 * Allows for manipulation of data via a [Model]
 */
interface DataHandler : AutoCloseable {

    /**
     * Represents the [DatabaseHandler] this [DataHandler] uses
     */
    var databaseHandler: DatabaseHandler

    /**
     * Puts an [item] in this [DataHandler]
     *
     * @param model The model object to use.
     * @param item The item to use
     */
    operator fun <T> set(model: Model<T>, item: T) {

        val data = model.asData(item)

        if (!model.noIndexes) {
            val lastIndex = parseInt(databaseHandler[model.dataNamespace.toString() + "::lastIndex"] ?: "0")

            databaseHandler[model.dataNamespace.toString() + "::$lastIndex"] = data.first

            databaseHandler[model.dataNamespace.toString() + "::lastIndex"] = (lastIndex + 1).toString()
        }

        databaseHandler.set(model.dataNamespace with data.first, data = data.second)
    }

    operator fun <T> get(model: Model<T>, index: Int): T? {
        return get(model, databaseHandler[model.dataNamespace.toString() + "::$index"] ?: return null)
    }

    operator fun <T> get(model: Model<T>, id: String): T? {
        return model.asObject(databaseHandler[model.dataNamespace with id] ?: return null)
    }

    fun <T> getAll(model: Model<T>): List<Pair<T, Int>> {
        val lastIndex = parseInt(databaseHandler[model.dataNamespace.toString() + "::lastIndex"] ?: "0")

        return (0..lastIndex).mapNotNull { (this[model, it] ?: return@mapNotNull null) to it }
    }

    /**
     * Removes an item from a specific [id]
     *
     * @param model The model object to base the ID off of
     * @param id The ID where the object for removal is located at.
     */
    fun <T> erase(model: Model<T>, id: String) {
        databaseHandler.erase(model.dataNamespace with id)
    }

}