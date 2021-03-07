package world.cepi.region.event

import world.cepi.region.Region

/**
 * Represents a [Region] related event.
 */
interface RegionEvent {

    /**
     * The [Region] in question.
     */
    val region: Region

}
