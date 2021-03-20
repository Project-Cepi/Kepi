package world.cepi.kepi.data

import world.cepi.kepi.data.database.DatabaseHandler

object DataHandlerFactory {

    fun of(handler: DatabaseHandler): IDataHandler = object: IDataHandler {

        override var databaseHandler: DatabaseHandler = handler

    }

}