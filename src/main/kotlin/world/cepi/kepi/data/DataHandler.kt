package world.cepi.kepi.data

import world.cepi.kepi.data.database.DatabaseHandler
import world.cepi.kepi.data.database.implementations.MemoryDatabase
import world.cepi.kepi.data.model.Model

object DataHandler: IDataHandler {

    override var databaseHandler: DatabaseHandler = MemoryDatabase()

}