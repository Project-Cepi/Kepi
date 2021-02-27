package world.cepi.kepi.impl1_0

import world.cepi.Service
import world.cepi.kepi.ImplementationMapper
import world.cepi.kepi.Kepi
import world.cepi.kepi.ServiceProvider
import world.cepi.kepi.UnsupportedServiceException
import java.util.concurrent.ConcurrentHashMap
import kotlin.reflect.KClass
import kotlin.reflect.jvm.jvmName

class KepiServiceManager : ServiceProvider {

    override val version: String = "1.0"

    override val implementationName: String = "KepiServiceProvider"

    companion object {

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

    }

    private val serviceMap: MutableMap<KClass<out Service>, KepiImplementationMapper<out Service>> =
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
    private fun <S : Service> ensureMapper(service: KClass<S>): KepiImplementationMapper<S> {
        val mapper = serviceMap[service] as? KepiImplementationMapper<S>?

        if (mapper == null) {
            val createdMapper = KepiImplementationMapper<S>(serviceName(service))
            serviceMap[service] = createdMapper
            return createdMapper
        } else {
            return mapper
        }
    }

    override fun <S : Service> isImplemented(service: KClass<S>): Boolean {
        val mapper = serviceMap[service] as? KepiImplementationMapper<S>?

        if (mapper == null) {
            return false
        } else {
            return mapper.isEmpty()
        }
    }

    override fun <S : Service> fetchService(service: KClass<S>): S {
        val mapper = serviceMap[service] as? KepiImplementationMapper<S>?

        if (mapper == null || mapper.isEmpty()) {
            throw UnsupportedServiceException("service ${serviceName(service)} not implemented")
        } else {
            return mapper.primary!!
        }
    }

    override fun <S : Service> getImplementations(service: KClass<S>): ImplementationMapper<S> {
        return ensureMapper(service)
    }

    override fun <S : Service> implementService(service: KClass<S>, implementation: S): Boolean {
        return ensureMapper(service).addImplementation(implementation)
    }

}