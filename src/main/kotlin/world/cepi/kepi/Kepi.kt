package world.cepi.kepi

import world.cepi.Service
import kotlin.reflect.KClass

/**
 * Convenient access to Kepi's [ServiceProvider]
 */
object Kepi : ServiceProvider {

    internal lateinit var provider: ServiceProvider

    override fun <S : Service> isImplemented(service: KClass<S>): Boolean {
        return provider.isImplemented(service)
    }

    override fun <S : Service> fetchService(service: KClass<S>): S {
        return provider.fetchService(service)
    }

    override fun <S : Service> getImplementations(service: KClass<S>): ImplementationMapper<S> {
        return provider.getImplementations(service)
    }

    override fun <S : Service> implementService(service: KClass<S>, implementation: S): Boolean {
        return provider.implementService(service, implementation)
    }

    override val version: String = provider.version

    override val implementationName: String = provider.implementationName

}
