package world.cepi.kepi.item

import net.minestom.server.entity.Player
import net.minestom.server.item.ItemStack
import world.cepi.gooey.InventoryManager

object AddCreationalItem {

    fun put(player: Player, itemStack: ItemStack): CreationalItemResult {
        if (player.inventory.itemStacks.none { it.isAir }) {
            return CreationalItemResult.CouldNotPut
        }

        if (InventoryManager.slot(player.heldSlot.toInt()) != null) {
            // The player is holding a non-movable item. Move the created item to a new slot.
            val index = player.inventory.itemStacks.indexOfFirst { it.isAir }
            player.inventory.setItemStack(index, itemStack)
            if (index in 0..8) {
                player.setHeldItemSlot(index.toByte())
            }
            return CreationalItemResult.InInventoryItemResult(index)
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