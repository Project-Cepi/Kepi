package world.cepi.kepi.data.model

import kotlinx.serialization.*
import world.cepi.kepi.data.ID
import world.cepi.kepi.data.model.Model.Companion.jsonParser
import kotlin.reflect.KClass

abstract class KotlinXModule<T : @Serializable Any> : Model<T> {

    abstract fun getID(item: T): ID
    abstract fun getClass(): KClass<T>

    @InternalSerializationApi
    override fun asData(item: T): Pair<ID, String> {
        return getID(item) to jsonParser.encodeToString(getClass().serializer(), item)
    }

    @InternalSerializationApi
    override fun asObject(data: String): T {
        return jsonParser.decodeFromString(getClass().serializer(), data)
    }

}