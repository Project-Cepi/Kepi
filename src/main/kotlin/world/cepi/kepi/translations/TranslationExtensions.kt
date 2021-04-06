package world.cepi.kepi.translations

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.TextReplacementConfig
import net.kyori.adventure.text.format.NamedTextColor
import net.minestom.server.entity.Player
import java.lang.Exception
import java.util.*

val replacementRegex = "%\\d+".toRegex()

fun Player.formatTranslableMessage(namespace: String = "common", key: String, vararg params: Component = arrayOf() ): Component {
    return Component.text(TranslationRegistry.get(namespace, key, this.locale ?: Locale.ENGLISH) ?: "$namespace.$key").replaceText(
        TextReplacementConfig.builder()
            .match(replacementRegex.toPattern())
            .replacement { replaceComponent ->
                try {
                    return@replacement params[replaceComponent.content()
                        .substring(1) // Remove the % prefix
                        .toInt() - 1 // Arrays in replacements start at 1
                    ]
                } catch (e: Exception) {
                    return@replacement Component.text("!!", NamedTextColor.RED)
                }
            }
            .build()
    )
}