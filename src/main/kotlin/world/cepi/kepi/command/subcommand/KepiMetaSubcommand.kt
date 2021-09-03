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

open class KepiMetaSubcommand<T : Any>(

    /** The root sealed class of this command */
    sealedRootClass: KClass<out T>,

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
) : KepiMetaManualSubcommand<T>(sealedRootClass.sealedSubclasses, argumentPerClassGenerator, name, dropString, addLambda, removeLambda, *previousArgs)