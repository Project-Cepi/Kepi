package world.cepi.kepi.data.model

import kotlinx.serialization.KSerializer
import world.cepi.kepi.data.DataNamespace

/**
 * Represents a [Model] that is backed by a serializable object.
 *
 * @param serializer The [KSerializer] corresponding to [T].
 * @param isSingleton Whether this object is a singleton or not. Default false.
 * @param dataNamespace The [DataNamespace] of this model
 */
open class JsonModel<T: @kotlinx.serialization.Serializable Any>(
    private val serializer: KSerializer<T>,
    override val dataNamespace: DataNamespace,
    val idGenerator: (T) -> String = { Model.default },
    override val isSingleton: Boolean = false,
) : Model<T> {

    override fun asObjectData(item: T): String = Model.jsonParser.encodeToString(serializer, item)
    override fun grabID(item: T) = idGenerator(item)
    override fun asObject(data: String): T = Model.jsonParser.decodeFromString(serializer, data)
}