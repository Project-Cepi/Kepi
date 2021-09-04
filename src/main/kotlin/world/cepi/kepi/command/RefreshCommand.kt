package world.cepi.kepi.command

import net.minestom.server.command.builder.Command
import net.minestom.server.entity.Player

object RefreshCommand : Command("refreshcommand") {

    init {
        setDefaultExecutor { sender, context ->
            (sender as? Player)?.refreshCommands()
        }
    }

}