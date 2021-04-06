package world.cepi.kepi.data.model

import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.serializer
import world.cepi.kepi.data.ID
import kotlin.reflect.KClass

/**
 * Represents a [Model] that is backed by a Serializable object.
 *
 * @param clazz The clazz that the serializable object is in.
 *
 * @param isSingleton Whether this object is a singleton or not.
 */
abstract class XSerializableModel<T: @kotlinx.serialization.Serializable Any>(
    val clazz: KClass<T>,
    override val isSingleton: Boolean
) : Model<T> {
    @InternalSerializationApi
    override fun asData(item: T): Pair<ID, String> = Pair(id(item), Model.jsonParser.encodeToString(clazz.serializer(), item))

    @InternalSerializationApi
    override fun asObject(data: String): T = Model.jsonParser.decodeFromString(clazz.serializer(), data)

    open fun id(item: T) = Model.defaultID

}