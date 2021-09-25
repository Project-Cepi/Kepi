package world.cepi.kepi.command.subcommand

import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.command.builder.exception.ArgumentSyntaxException
import world.cepi.kepi.data.DataHandler
import world.cepi.kepi.data.model.Model
import world.cepi.kstom.command.arguments.literal
import world.cepi.kstom.command.arguments.suggest
import world.cepi.kstom.command.kommand.Kommand

open class KepiRegistrySubcommand<T>(
    val dataHandler: DataHandler,
    val model: Model<T>,
    val getFromRegistry: SyntaxContext.(T) -> Unit,
    val addToRegistry: SyntaxContext.(String) -> T?,
    val addCallback: SyntaxContext.(T) -> Unit = { },
    val removeCallback: SyntaxContext.(String) -> Unit = { },
    name: String = "registry"
) : Kommand(name = name) {
    val newItem = ArgumentType.Word("newName").map { value ->
        if (dataHandler.getAll(model).any { model.grabID(it.first) == value })
            throw ArgumentSyntaxException("Registered name already exists", value, 1)

        value
    }

    val registeredItem = ArgumentType.Word("registered").map { value ->
        dataHandler.getAll(model).firstOrNull { model.grabID(it.first) == value }?.first
            ?: throw ArgumentSyntaxException("Invalid mob", value, 1)
    }.suggest {
        dataHandler.getAll(model).map { model.grabID(it.first) }
    }

    init {
        val add by literal
        val get by literal
        val remove by literal

        syntax(get, registeredItem) {
            getFromRegistry(this, context[registeredItem])
        }

        syntax(remove, registeredItem) {
            dataHandler.erase(model, model.grabID(context[registeredItem]).also { removeCallback(this, it) })
        }

        syntax(add, newItem) {
            dataHandler[model] = addToRegistry(this, context[newItem])?.also { addCallback(this, it) } ?: return@syntax
        }
    }
}