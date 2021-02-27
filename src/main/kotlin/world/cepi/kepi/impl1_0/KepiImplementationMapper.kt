package world.cepi.kepi.impl1_0

import world.cepi.Service
import world.cepi.kepi.ImplementationMapper
import java.util.concurrent.ConcurrentHashMap
import kotlin.reflect.KClass

class KepiImplementationMapper<S : Service>(private val serviceName: String) : ImplementationMapper<S> {

    private var primaryImplementation: S? = null
    private val implementationMap: MutableMap<String, S> = ConcurrentHashMap()

    fun addImplementation(implementation: S): Boolean {
        if (implementationMap[implementation.implementationName] != null) {
            throw IllegalStateException(
                "duplicate implementation ${implementation.implementationName} for service $serviceName"
            )
        }

        implementationMap[implementation.implementationName] = implementation

        if (primaryImplementation == null) {
            primaryImplementation = implementation
            return true
        } else {
            return false
        }
    }

    override val size: Int
        get() = implementationMap.size

    override val primary: S?
        get() = primaryImplementation

    override val asMap: Map<String, S>
        get() = implementationMap

    override val asCollection: Collection<S>
        get() = implementationMap.values

}