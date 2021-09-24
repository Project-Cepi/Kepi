package world.cepi.kepi.command

import net.minestom.server.entity.Player
import world.cepi.kstom.command.kommand.Kommand

object RefreshCommand : Kommand({

    default {
        (sender as? Player)?.refreshCommands()
    }


}, "refreshcommand")