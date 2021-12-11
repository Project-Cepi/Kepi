package world.cepi.kepi

import me.xdrop.fuzzywuzzy.FuzzySearch
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.event.ClickEvent
import net.kyori.adventure.text.event.HoverEvent
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import net.minestom.server.command.CommandSender
import net.minestom.server.command.builder.arguments.Argument
import world.cepi.example.SubfuzzyArguments

fun CommandSender.sendFuzzyMessage(prefix: String, input: String, possibility: String) {
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

fun KFuzzArgument(id: String, prefix: String, vararg subargs: String) = SubfuzzyArguments(id, *subargs) { input, possibility, chance ->
    sendFuzzyMessage(prefix, input, possibility)
}

fun Argument<*>.attatchFuzz(prefix: String, vararg alternativeInputs: String) = this.setCallback { sender, exception ->

    FuzzySearch.extractTop(exception.input, alternativeInputs.toList(), 1).firstOrNull()?.let {
        sender.sendFuzzyMessage(prefix, exception.input, it.string)
    }
}