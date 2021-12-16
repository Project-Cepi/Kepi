package world.cepi.kepi

import com.mattworzala.canvas.ext.canvas
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import net.minestom.server.event.player.PlayerUseItemEvent
import net.minestom.server.extensions.Extension
import net.minestom.server.item.Material
import world.cepi.gooey.InventoryManager
import world.cepi.kepi.command.*
import world.cepi.kepi.menu.menuUI
import world.cepi.kepi.messages.translations.TranslationRegistry
import world.cepi.kstom.Manager
import world.cepi.kstom.command.arguments.generation.CallbackGenerator
import world.cepi.kstom.command.register
import world.cepi.kstom.command.unregister
import world.cepi.kstom.event.listenOnly
import world.cepi.kstom.item.item

class Kepi : Extension() {

    override fun initialize() {

        val menuItem = item(Material.NETHER_STAR) {
            displayName(
                Component.text("Menu", NamedTextColor.LIGHT_PURPLE)
                    .decoration(TextDecoration.ITALIC, false)
            )
        }


        InventoryManager[8] = menuItem
        eventNode.listenOnly<PlayerUseItemEvent> {
            if (itemStack == menuItem) {
                player.canvas.render { menuUI() }
            }
        }

        CallbackGenerator.errorSymbol = Component.text("\uE006")

        try {
            TranslationRegistry.grab()
        } catch (exception: Exception) {
            logger.error("An unexpected error occured loading translations.")
            Manager.exception.handleException(exception)
        }

        KepiCommand.register()
        HelpCommand.register()
        TranslationCommand.register()
        RefreshCommand.register()
        BlockHandlerCommand.register()
        YardCommand.register()
        MenuCommand.register()

        logger.info("[Kepi] has been enabled!")
    }

    override fun terminate() {

        KepiCommand.unregister()
        HelpCommand.unregister()
        TranslationCommand.unregister()
        RefreshCommand.unregister()
        BlockHandlerCommand.unregister()
        YardCommand.unregister()
        MenuCommand.unregister()

        logger.info("[Kepi] has been disabled!")
    }
}