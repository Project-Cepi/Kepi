package world.cepi.kepi

import com.mattworzala.canvas.ext.canvas
import net.kyori.adventure.sound.Sound
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import net.minestom.server.event.player.PlayerUseItemEvent
import net.minestom.server.extensions.Extension
import net.minestom.server.item.Material
import net.minestom.server.sound.SoundEvent
import world.cepi.gooey.InventoryManager
import world.cepi.kepi.command.*
import world.cepi.kepi.data.ContentDataHandler
import world.cepi.kepi.data.GameDataHandler
import world.cepi.kepi.menu.menuUI
import world.cepi.kepi.messages.translations.TranslationRegistry
import world.cepi.kstom.Manager
import world.cepi.kstom.command.arguments.generation.CallbackGenerator
import world.cepi.kstom.command.register
import world.cepi.kstom.command.unregister
import world.cepi.kstom.event.listenOnly
import world.cepi.kstom.item.displayName
import world.cepi.kstom.item.item
import world.cepi.kstom.util.log
import world.cepi.kstom.util.node


class Kepi : Extension() {

    companion object {
        val newItemSound = Sound.sound(SoundEvent.ENTITY_PLAYER_LEVELUP, Sound.Source.MASTER, 1f, 2f)
        val editItemSound = Sound.sound(SoundEvent.ENTITY_ITEM_PICKUP, Sound.Source.MASTER, 2f, 0.5f)
    }

    override fun initialize(): LoadStatus {
        
        val menuItem = item(Material.NETHER_STAR) {
            displayName(
                Component.text("Menu", NamedTextColor.BLUE)
                    .decoration(TextDecoration.ITALIC, false)
                    .append(Component.text(" (Right click to open)", NamedTextColor.GRAY).decorate(TextDecoration.ITALIC))
            )
        }


        InventoryManager[8] = menuItem
        node.listenOnly<PlayerUseItemEvent> {
            if (itemStack == menuItem) {
                player.canvas.render { menuUI(player) }
            }
        }

        CallbackGenerator.errorSymbol = Component.text("\uE006")

        try {
            TranslationRegistry.grab()
        } catch (exception: Exception) {
            log.error("An unexpected error occured loading translations.")
            Manager.exception.handleException(exception)
        }

        KepiCommand.register()
        HelpCommand.register()
        TranslationCommand.register()
        RefreshCommand.register()
        BlockHandlerCommand.register()
        YardCommand.register()
        MenuCommand.register()

        log.info("[Kepi] has been enabled!")

        return LoadStatus.SUCCESS
    }

    override fun terminate() {

        KepiCommand.unregister()
        HelpCommand.unregister()
        TranslationCommand.unregister()
        RefreshCommand.unregister()
        BlockHandlerCommand.unregister()
        YardCommand.unregister()
        MenuCommand.unregister()

        ContentDataHandler.handlers.values.forEach { it.close() }
        GameDataHandler.handlers.values.forEach { it.close() }

        log.info("[Kepi] has been disabled!")
    }
}