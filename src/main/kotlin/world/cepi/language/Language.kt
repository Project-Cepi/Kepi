package world.cepi.language

import net.minestom.server.chat.JsonMessage

object Language: LanguageProvider {

    internal lateinit var provider: LanguageProvider

    override fun getDefaultLocale(): String {
        return provider.getDefaultLocale()
    }

    override fun getLocalizedJsonMessage(extension: String, id: String): Localizable<JsonMessage> {
        return provider.getLocalizedJsonMessage(extension, id)
    }

    override fun getLocalizedString(extension: String, id: String): Localizable<String> {
        return provider.getLocalizedString(extension, id)
    }

    override val version: String = provider.version

    override val implementationName: String = provider.implementationName

}

