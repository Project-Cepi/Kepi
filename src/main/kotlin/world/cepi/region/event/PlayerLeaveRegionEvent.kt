package world.cepi.region.event

import net.minestom.server.entity.Player
import net.minestom.server.event.player.PlayerMoveEvent
import net.minestom.server.utils.Position
import world.cepi.region.Region

/**
 * Represents an event that is called when player attempts to leave a [Region].
 */
class PlayerLeaveRegionEvent(player: Player, private val region: Region, positionOutsideRegion: Position)
    : PlayerMoveEvent(player, positionOutsideRegion), RegionEvent {

    /**
     * @return The [Region] that is being entered.
     */
    override fun getRegion(): Region {
        return region
    }

}

