package world.cepi.kepi.command

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.event.ClickEvent
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextColor
import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.instance.block.BlockHandler
import world.cepi.kstom.command.arguments.literal
import world.cepi.kstom.command.kommand.Kommand

object BlockHandlerCommand : Kommand({

    val get by literal

    val relativeBlockPosition = ArgumentType.RelativeBlockPosition("blockpos")

    syntax(get, relativeBlockPosition).onlyPlayers {
        val block = player.instance!!
            .getBlock((!relativeBlockPosition).from(player))

        val handler = block.handler()

        if (handler == null) {
            player.sendMessage("No block handler found!")
            return@onlyPlayers
        }

        player.sendMessage(
            Component.text(handler.namespaceId.toString(), NamedTextColor.GRAY)
                .append(Component.text(" // ", NamedTextColor.DARK_GRAY))
                .append(Component.text(handler::class.simpleName ?: "Unknown Name", NamedTextColor.BLUE))
                .append(Component.text(" (" + (handler::class.qualifiedName ?: "Unknown Qualified Name") + ")", TextColor.color(145, 145, 145)))
                .append(Component.newline())
                .append(Component.newline())
                .let textComponent@ {
                    block.nbt()?.toSNBT()?.let { snbt ->
                        return@textComponent it.append(
                            Component.text(snbt, NamedTextColor.GRAY)
                                .hoverEvent(Component.text("Click to copy", NamedTextColor.YELLOW))
                                .clickEvent(ClickEvent.copyToClipboard(snbt))
                        )
                    }

                    it
                }

        )
    }

    val remove by literal

    syntax(remove, relativeBlockPosition) {
        val block = player.instance!!
            .getBlock((!relativeBlockPosition).from(player))

        player.instance!!.setBlock((!relativeBlockPosition).from(player), block.withHandler(BlockHandler.Dummy.get(block.namespace().namespace())))
    }

    val change by literal

    val block = ArgumentType.BlockState("block")
    syntax(change, relativeBlockPosition, block) {

        val currentBlock = player.instance!!.getBlock((!relativeBlockPosition).from(player))

        player.instance!!.setBlock(
            (!relativeBlockPosition).from(player),
            (!block)
                .withNbt(currentBlock.nbt())
                .withHandler(currentBlock.handler())
        )
    }

}, "blockhandler")

