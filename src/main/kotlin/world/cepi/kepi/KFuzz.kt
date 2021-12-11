package world.cepi.kepi

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.event.ClickEvent
import net.kyori.adventure.text.event.HoverEvent
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import net.minestom.server.command.CommandSender
import net.minestom.server.command.builder.arguments.ArgumentLiteral
import world.cepi.example.SubFuzz
import world.cepi.example.attatchFuzz
import kotlin.reflect.KProperty

fun CommandSender.sendFuzzyMessage(prefix: String, input: String, possibility: String) {
    sendMessage(
        Component.text("You inputted ", NamedTextColor.GRAY)
            .append(Component.text("/$prefix $input", NamedTextColor.YELLOW))
            .append(Component.newline())
            .append(Component.text("- ", NamedTextColor.DARK_GRAY))
            .append(Component.text("Did you mean ", NamedTextColor.GRAY)
                .append(Component.text("/$prefix $possibility", NamedTextColor.GREEN))
                .append(Component.text("? ", NamedTextColor.GRAY))
                .append(Component.text("(Click Here)").decorate(TextDecoration.ITALIC))
                .hoverEvent(HoverEvent.showText(
                    Component.text("Click here to run $prefix $possibility", NamedTextColor.YELLOW)
                ))
                .clickEvent(ClickEvent.runCommand("/$prefix $possibility"))
            )
    )
}

class KFuzz(val prefix: String, vararg possibilities: String) : SubFuzz(possibilities.toList(), { input, possibility, _ ->
    sendFuzzyMessage(prefix, input, possibility)
})

class LiteralFuzz(val fuzz: SubFuzz) {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): ArgumentLiteral {
        return ArgumentLiteral(property.name).attatchFuzz(fuzz) as ArgumentLiteral
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: ArgumentLiteral) {

    }
}

fun literalFuzz(fuzz: SubFuzz) = LiteralFuzz(fuzz)