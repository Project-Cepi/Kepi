package world.cepi.kepi.data.model

import kotlinx.serialization.*
import world.cepi.kepi.data.ID
import world.cepi.kepi.data.model.Model.Companion.defaultID
import world.cepi.kepi.data.model.Model.Companion.jsonParser
import kotlin.reflect.KClass

interface XSerializableModule<T : @Serializable Any> : Model<T> {

    fun id(item: T): ID =
        defaultID

    val clazz: KClass<T>

    @InternalSerializationApi
    override fun asData(item: T): Pair<ID, String> {
        return id(item) to jsonParser.encodeToString(clazz.serializer(), item)
    }

    @InternalSerializationApi
    override fun asObject(data: String): T {
        return jsonParser.decodeFromString(clazz.serializer(), data)
    }

}