package world.cepi.kepi

import world.cepi.Service
import world.cepi.kepi.impl1_0.KepiImplementationMapper
import java.util.concurrent.ConcurrentHashMap
import kotlin.reflect.KClass
import kotlin.reflect.jvm.jvmName

/**
 * Convenient access to Kepi's [ServiceProvider]
 */
object Kepi : ServiceProvider {

    override val version: String = "1.0"

    override val implementationName: String = "KepiServiceProvider"

    /**
     * Gets a non-null service name for the given service.
     *
     * @param service The service's interface
     *
     * @return A name for the service
     */
    fun serviceName(service: KClass<out Service>): String {
        return if (service.qualifiedName == null) {
            service.jvmName
        } else {
            service.qualifiedName!!
        }
    }


    private val serviceMap: MutableMap<KClass<out Service>, KepiImplementationMapper> =
        ConcurrentHashMap()

    /**
     * Ensures that there exists an [KepiImplementationMapper] for the given service.
     * If one doesn't already exist in [serviceMap], a new mapper will be created and
     * inserted there.
     *
     * @param service The service's interface
     *
     * @return The mapper
     */
    private fun ensureMapper(service: KClass<out Service>): KepiImplementationMapper {

        return if (serviceMap[service] == null) {
            val createdMapper = KepiImplementationMapper(serviceName(service))
            serviceMap[service] = createdMapper
            createdMapper
        } else {
            serviceMap[service]!!
        }

    }

    override fun has(service: KClass<out Service>): Boolean {
        val mapper = serviceMap[service]

        return mapper?.empty ?: false
    }

    override fun fetch(service: KClass<out Service>): Service {
        val mapper = serviceMap[service]

        if (mapper == null || mapper.empty) {
            throw UnsupportedServiceException("service ${serviceName(service)} not implemented")
        } else {
            return mapper.primary!!
        }
    }

    override fun getImplementations(service: KClass<out Service>): ImplementationMapper {
        return ensureMapper(service)
    }

    override fun implementService(service: KClass<out Service>, implementation: Service): Boolean {
        return ensureMapper(service).add(implementation)
    }

}
