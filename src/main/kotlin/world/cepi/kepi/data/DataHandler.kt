package world.cepi.kepi.data

import world.cepi.kepi.data.database.DatabaseHandler
import world.cepi.kepi.data.model.Model

/**
 * Handles data with a backing [DatabaseHandler]
 *
 * Allows for manipulation of data via a [Model]
 */
interface DataHandler {

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
    operator fun <T> set(model: Model<T>, item: T): Boolean {

        val data = model.asData(item)

        return databaseHandler.put(model.dataNamespace with data.first, data = data.second)
    }

    operator fun <T> get(model: Model<T>, id: String): T? {
        return model.asObject(databaseHandler.get(model.dataNamespace with id) ?: return null)
    }

    /**
     * Removes an item from a specific [id]
     *
     * @param model The model object to base the ID off of
     * @param id The ID where the object for removal is located at.
     */
    fun <T> erase(model: Model<T>, id: String): Boolean {

        return databaseHandler.erase(model.dataNamespace with id)

    }

}