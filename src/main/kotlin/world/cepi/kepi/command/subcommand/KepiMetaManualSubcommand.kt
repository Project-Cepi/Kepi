package world.cepi.kepi.command.subcommand

import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.arguments.Argument
import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.command.builder.exception.ArgumentSyntaxException
import world.cepi.kstom.command.SyntaxContext
import world.cepi.kstom.command.addSyntax
import world.cepi.kstom.command.arguments.generation.generateSyntaxes
import world.cepi.kstom.command.arguments.literal
import kotlin.reflect.KClass

open class KepiMetaManualSubcommand<T : Any>(
    /** All meta subclasses */
    allClasses: Collection<KClass<out T>>,

    argumentPerClassGenerator: (KClass<out T>, String) -> Argument<*> = { _, className -> className.literal() },

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
    addLambda: SyntaxContext.(T, String) -> Unit,

    /** What to execute when meta is removed */
    removeLambda: SyntaxContext.(KClass<out T>, String) -> Unit,

    vararg previousArgs: Argument<*>
) : Command(name) {

    init {
        val set = "set".literal()
        val remove = "remove".literal()

        val metaClass = ArgumentType.Word("metaName").from(
            *allClasses
                .map { it.simpleName!!.lowercase().dropLast(dropString.length) }
                .toTypedArray()
        ).map { sealedClassName -> allClasses
            .firstOrNull { it.simpleName!!.lowercase().dropLast(dropString.length) == sealedClassName }
            ?: throw ArgumentSyntaxException("Meta is invalid", sealedClassName, 1)
        }

        allClasses.forEach { clazz ->
            val syntaxes = generateSyntaxes(clazz)

            val clazzArgumentName = clazz.simpleName!!.lowercase().dropLast(dropString.length)

            val clazzArgument = argumentPerClassGenerator(clazz, clazzArgumentName)

            syntaxes.applySyntax(this, *previousArgs, set, clazzArgument) { instance ->
                addLambda(this, instance, clazzArgumentName)
            }

            addSyntax(*previousArgs, remove, clazzArgument) {
                removeLambda(this, context[metaClass], clazzArgumentName)
            }

        }

        addSyntax(*previousArgs, remove, metaClass) {

            val clazzArgumentName = context[metaClass].simpleName!!.lowercase().dropLast(dropString.length)

            removeLambda(this, context[metaClass], clazzArgumentName)
        }
    }

}