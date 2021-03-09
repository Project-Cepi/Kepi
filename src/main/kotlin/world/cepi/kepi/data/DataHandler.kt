package world.cepi.kepi.data

import world.cepi.kepi.data.databases.MemoryDatabase

object DataHandler {

    /**
     * Represents the [DatabaseHandler] this [DataHandler] singleton uses
     */
    internal var databaseHandler: DatabaseHandler = MemoryDatabase()

    /**
     * Puts data at a namespace + id
     *
     * @param model The model object to use.
     * @param data The data as JSON (string)
     */
    fun <T> put(model: Model<T>, item: T, id: ID): Boolean {

        val data = model.asData(item)

        return databaseHandler.put(model.dataNamespace, model.dependsOn.dataNamespace, id = data.first, data = data.second)
    }

    fun <T> erase(model: Model<T>, id: ID): Boolean {

        return databaseHandler.erase(model.dataNamespace, model.dependsOn.dataNamespace, id)

    }

}