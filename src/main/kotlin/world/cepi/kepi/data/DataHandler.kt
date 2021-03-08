package world.cepi.kepi.data

object DataHandler {

    internal var databaseHandler: DatabaseHandler? = null
    val models: MutableSet<Model<*>> = mutableSetOf()

    /**
     * Puts data at a namespace + id
     *
     * @param model The model object to use.
     * @param data The data as JSON (string)
     */
    fun <T> put(model: Model<T>, id: String, item: T): Boolean {

        val data = model.asData(item)

        return databaseHandler?.put(model.dataNamespace, id = data.first, data = data.second) ?: false
    }

}