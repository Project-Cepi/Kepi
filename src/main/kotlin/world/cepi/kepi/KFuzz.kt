package world.cepi.kepi

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.event.ClickEvent
import net.kyori.adventure.text.event.HoverEvent
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import world.cepi.example.SubfuzzyArguments

fun KFuzzArgument(id: String, prefix: String, vararg subargs: String) = SubfuzzyArguments(id, *subargs) { input, possibility, chance ->
    sendMessage(
        Component.text("You inputted ", NamedTextColor.GRAY)
            .append(Component.text("$prefix $input", NamedTextColor.YELLOW))
            .append(Component.newline())
            .append(Component.text("- ", NamedTextColor.DARK_GRAY))
            .append(Component.text("Did you mean ", NamedTextColor.GRAY)
                .append(Component.text("$prefix $possibility", NamedTextColor.GREEN))
                .append(Component.text("? ", NamedTextColor.GRAY))
                .append(Component.text("(Click Here)").decorate(TextDecoration.ITALIC))
                .hoverEvent(HoverEvent.showText(
                    Component.text("Click here to run $prefix $possibility", NamedTextColor.YELLOW)
                ))
                .clickEvent(ClickEvent.runCommand("$prefix $possibility"))
            )
    )
}