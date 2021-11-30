package world.cepi.kepi.item

import net.minestom.server.entity.Player
import net.minestom.server.item.ItemStack

object AddCreationalItem {

    fun put(player: Player, itemStack: ItemStack): CreationalItemResult {

        if (player.inventory.itemStacks.none { it.isAir }) {
            return CreationalItemResult.CouldNotPut
        }

        if (player.itemInMainHand == ItemStack.AIR) {
            player.itemInMainHand = itemStack
            return CreationalItemResult.MainHandItemResult
        }

        // The player is holding an item + there is space left, take the current item and place it
        // in the remaining slot.
        val index = player.inventory.itemStacks.indexOfFirst { it.isAir }
        player.inventory.setItemStack(index, player.itemInMainHand)
        player.itemInMainHand = itemStack

        return CreationalItemResult.InInventoryItemResult(index)
    }

}