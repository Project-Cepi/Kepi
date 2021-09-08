package world.cepi.kepi.command

import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.entity.Player
import world.cepi.kstom.command.addSyntax

object BlockHandlerCommand : Command("blockhandler") {

    init {
        val relativeBlockPosition = ArgumentType.RelativeBlockPosition("blockpos")

        addSyntax(relativeBlockPosition) {
            val player = sender as? Player ?: return@addSyntax

            val handler = player.instance!!
                .getBlock(context[relativeBlockPosition].from(player))
                .handler()

            if (handler == null) {
                player.sendMessage("No block handler found!")
                return@addSyntax
            }

            player.sendMessage(
                "${handler.namespaceId}: ${handler::class.simpleName ?: "Unknown Name"}"
            )
        }
    }

}