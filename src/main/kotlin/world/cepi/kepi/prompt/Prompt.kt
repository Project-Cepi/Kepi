package world.cepi.kepi.prompt

import net.kyori.adventure.text.TextComponent
import java.util.*

class Prompt(
    vararg val options: PromptOption
)

class PromptOption(
    val text: TextComponent,
    val id: UUID,
    val value: String
) {
    var isChosen = false
}