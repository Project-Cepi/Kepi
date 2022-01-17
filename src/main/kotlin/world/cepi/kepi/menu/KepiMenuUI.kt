package world.cepi.kepi.menu

import com.mattworzala.canvas.fragment
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.minestom.server.entity.Player
import net.minestom.server.entity.PlayerSkin
import net.minestom.server.item.Material
import net.minestom.server.item.metadata.PlayerHeadMeta
import world.cepi.kstom.adventure.noItalic
import world.cepi.kstom.item.item

fun menuUI(player: Player) = fragment(9, 6) {

    this.inventory.title = Component.text("Menu", NamedTextColor.LIGHT_PURPLE)

    item(13, item(Material.PLAYER_HEAD) {
        val builder = this as PlayerHeadMeta.Builder

        builder.skullOwner(player.uuid)
        builder.playerSkin(PlayerSkin.fromUuid(player.uuid.toString()))

        displayName(Component.text(player.username, NamedTextColor.BLUE).noItalic())
        lore(
            Component.empty(),
            Component.text("Level: ", NamedTextColor.GRAY).noItalic()
                .append(Component.text(player.level, NamedTextColor.WHITE))
        )
    })

}