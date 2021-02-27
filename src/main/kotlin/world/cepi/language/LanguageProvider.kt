package world.cepi.language

import net.minestom.server.chat.JsonMessage
import world.cepi.Service
import kotlin.reflect.KCallable
import kotlin.reflect.KProperty
import kotlin.reflect.full.declaredMembers

interface LanguageProvider : Service {

    fun getDefaultLocale(): String

    fun getLocalizedJsonMessage(extension: String, id: String): Localizable<JsonMessage>

    fun getLocalizedString(extension: String, id: String): Localizable<String>

}