package world.cepi.kepi.model.singleton

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import world.cepi.kepi.data.DataNamespaceForge
import world.cepi.kepi.data.ID
import world.cepi.kepi.data.model.KotlinXModule
import world.cepi.kepi.data.model.Model
import world.cepi.kepi.data.model.Model.Companion.defaultID
import world.cepi.kepi.data.model.Model.Companion.jsonParser
import kotlin.reflect.KClass

/**
 * Singleton model -- storing a `Square` would only store this one instance of the square at the same ID.
 */
object SquareModel : KotlinXModule<Square> {

    override val dataNamespace: DataNamespaceForge = DataNamespaceForge("shape", "sqare")

    override val clazz: KClass<Square>
        get() = Square::class

    override val isSingleton = true

}