package world.cepi.kepi.data

import world.cepi.kepi.data.database.DatabaseHandler
import java.util.concurrent.ConcurrentHashMap

/**
 * Represents a factory that creates and stores data based on namespaces.
 *
 * Since Custom Worlds are a thing, a namespace is provided.
 *
 * If data is being stored in the default Cepi (main) instance,
 *
 * [main] is the needed function
 */
open class DataHandlerFactory(val databaseHandlerFactory: (DataNamespace) -> DatabaseHandler) {

    companion object {
        const val mainDataHandler = "main"
    }

    val handlers: Map<DataNamespace, DataHandler> = ConcurrentHashMap()

    fun of(namespace: DataNamespace): DataHandler {

        handlers as MutableMap

        return if (!handlers.containsKey(namespace)) object : DataHandler {
            override var databaseHandler: DatabaseHandler = databaseHandlerFactory(namespace)
        }
        else handlers[namespace]!!

    }

    fun main(): DataHandler = of(mainDataHandler.asNamespace())

}