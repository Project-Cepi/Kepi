package world.cepi.kepi.command.subcommand

import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.command.builder.exception.ArgumentSyntaxException
import world.cepi.kstom.command.SyntaxContext
import world.cepi.kstom.command.addSyntax
import world.cepi.kstom.command.arguments.generation.generateSyntaxes
import world.cepi.kstom.command.arguments.literal
import kotlin.reflect.KClass

open class KepiMetaSubcommand<T : Any>(

    /** The root sealed class of this command */
    sealedRootClass: KClass<T>,

    /**
     * The name of the command.
     *
     * If no [dropString] is specified, will be
     * used to drop the name from the metaClass arg.
     */
    name: String = "meta",

    /** The string to drop from the class name (subclasses of the [sealedRootClass] */
    dropString: String = name,

    /*+ What to execute when meta is added */
    addLambda: SyntaxContext.(T) -> Unit,

    /** What to execute when meta is removed */
    removeLambda: SyntaxContext.(KClass<out T>) -> Unit
) : Command(name) {

    init {
        val set = "set".literal()
        val remove = "remove".literal()

        val metaClass = ArgumentType.Word("metaName").from(
            *sealedRootClass.sealedSubclasses
                .map { it.simpleName!!.lowercase().dropLast(dropString.length) }
                .toTypedArray()
        ).map { sealedClassName -> sealedRootClass.sealedSubclasses
            .firstOrNull { it.simpleName!!.lowercase().dropLast(dropString.length) == sealedClassName }
            ?: throw ArgumentSyntaxException("Meta is invalid", sealedClassName, 1)
        }

        sealedRootClass.sealedSubclasses.forEach { clazz ->
            val syntaxes = generateSyntaxes(clazz)

            val clazzArgumentName = clazz.simpleName!!.lowercase().dropLast(4)

            syntaxes.applySyntax(this, set, clazzArgumentName.literal()) { instance ->
                addLambda(this, instance)
            }

        }

        addSyntax(remove, metaClass) {
            removeLambda(this, context[metaClass])
        }
    }

}