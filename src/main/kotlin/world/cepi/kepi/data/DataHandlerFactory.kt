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
open class DataHandlerFactory<D>(val databaseHandlerFactory: (DataNamespace) -> DatabaseHandler<D>) {

    companion object {
        const val mainDataHandler = "main"
    }

    val handlers: Map<DataNamespace, DataHandler<D>> = ConcurrentHashMap()

    fun of(namespace: DataNamespace): DataHandler<D> {

        handlers as MutableMap

        return if (!handlers.containsKey(namespace)) object : DataHandler<D> {
            override var databaseHandler: DatabaseHandler<D> = databaseHandlerFactory(namespace)
        }
        else handlers[namespace]!!

    }

    fun main(): DataHandler<D> = of(mainDataHandler.asNamespace())

}