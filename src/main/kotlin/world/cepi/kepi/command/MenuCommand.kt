package world.cepi.kepi.command

import com.mattworzala.canvas.ext.canvas
import world.cepi.kepi.menu.menuUI
import world.cepi.kstom.command.kommand.Kommand

object MenuCommand : Kommand({
    default {
        player.canvas.render { menuUI(player) }
    }
}, "menu")