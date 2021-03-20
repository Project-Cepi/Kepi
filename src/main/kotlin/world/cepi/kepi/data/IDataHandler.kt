package world.cepi.kepi.data

import world.cepi.kepi.data.database.DatabaseHandler
import world.cepi.kepi.data.model.Model

interface IDataHandler {

    /**
     * Represents the [DatabaseHandler] this [IDataHandler] uses
     */
    var databaseHandler: DatabaseHandler

    /**
     * Puts data at a namespace + id
     *
     * @param model The model object to use.
     * @param item The item to use
     */
    operator fun <T> set(model: Model<T>, item: T): Boolean {

        val data = model.asData(item)

        return databaseHandler.put(model.dataNamespace, model.dependsOn?.dataNamespace ?: Model.defaultNamespace, id = data.first, data = data.second)
    }

    fun <T> erase(model: Model<T>, id: ID): Boolean {

        return databaseHandler.erase(model.dataNamespace, model.dependsOn?.dataNamespace ?: Model.defaultNamespace, id)

    }

}