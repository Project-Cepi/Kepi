package world.cepi.kepi.impl1_0

import world.cepi.Service
import world.cepi.kepi.ImplementationMapper
import java.util.concurrent.ConcurrentHashMap

class KepiImplementationMapper(private val serviceName: String) : ImplementationMapper {

    private var primaryImplementation: Service? = null
    private val implementationMap: MutableMap<String, Service> = ConcurrentHashMap()

    override fun add(implementation: Service): Boolean {
        if (implementationMap[implementation.implementationName] != null) {
            throw IllegalStateException(
                "duplicate implementation ${implementation.implementationName} for service $serviceName"
            )
        }

        implementationMap[implementation.implementationName] = implementation

        return if (primaryImplementation == null) {
            primaryImplementation = implementation
            true
        } else {
            false
        }
    }

    override val size: Int
        get() = implementationMap.size

    override val primary: Service?
        get() = primaryImplementation

    override val asMap: Map<String, Service>
        get() = implementationMap

    override val asCollection: Collection<Service>
        get() = implementationMap.values

}