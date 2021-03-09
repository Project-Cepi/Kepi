package world.cepi.kepi.data

import world.cepi.kepi.data.database.DatabaseHandler
import world.cepi.kepi.data.database.implementations.MemoryDatabase
import world.cepi.kepi.data.model.Model

object DataHandler {

    /**
     * Represents the [DatabaseHandler] this [DataHandler] singleton uses
     */
    internal var databaseHandler: DatabaseHandler = MemoryDatabase()

    /**
     * Puts data at a namespace + id
     *
     * @param model The model object to use.
     * @param item The item to use
     */
    fun <T> put(model: Model<T>, item: T): Boolean {

        val data = model.asData(item)

        return databaseHandler.put(model.dataNamespace, model.dependsOn?.dataNamespace ?: Model.defaultNamespace, id = data.first, data = data.second)
    }

    fun <T> erase(model: Model<T>, id: ID): Boolean {

        return databaseHandler.erase(model.dataNamespace, model.dependsOn?.dataNamespace ?: Model.defaultNamespace, id)

    }

}