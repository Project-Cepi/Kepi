package world.cepi.kepi.model.singleton

import world.cepi.kepi.data.DataNamespaceForge
import world.cepi.kepi.data.model.XSerializableModule
import kotlin.reflect.KClass

/**
 * Singleton model -- storing a `Square` would only store this one instance of the square at the same ID.
 */
object SquareModel : XSerializableModule<Square> {

    override val dataNamespace: DataNamespaceForge = DataNamespaceForge("shape", "sqare")

    override val clazz: KClass<Square>
        get() = Square::class

    override val isSingleton = true

}