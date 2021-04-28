package world.cepi.kepi.data.model

import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.serializer
import world.cepi.kepi.data.DataNamespace
import world.cepi.kepi.data.ID
import kotlin.reflect.KClass

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
    override val isSingleton: Boolean = false,
    val id: ID = Model.defaultID
) : Model<T> {

    override fun asData(item: T): Pair<ID, JsonElement> = id to Model.jsonParser.encodeToJsonElement(serializer, item)
    override fun asObject(data: JsonElement): T = Model.jsonParser.decodeFromJsonElement(serializer, data)
}