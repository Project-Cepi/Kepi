package world.cepi.kepi.data

import world.cepi.kepi.data.databaseimpls.MemoryDatabase

object DataHandler {

    /**
     * Represents the [DatabaseHandler] this [DataHandler] singleton uses
     */
    internal var databaseHandler: DatabaseHandler = MemoryDatabase()

    /**
     * In memory list of all the models.
     */
    val models: MutableSet<Model<*>> = mutableSetOf()

    /**
     * Puts data at a namespace + id
     *
     * @param model The model object to use.
     * @param data The data as JSON (string)
     */
    fun <T> put(model: Model<T>, item: T, id: String): Boolean {

        val data = model.asData(item)

        return databaseHandler.put(model.dataNamespace, id = data.first, data = data.second)
    }

    fun <T> erase(model: Model<T>, id: String): Boolean {

        return databaseHandler.erase(model.dataNamespace, id)

    }

}