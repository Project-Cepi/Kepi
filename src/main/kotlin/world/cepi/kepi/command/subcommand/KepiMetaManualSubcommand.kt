package world.cepi.kepi.command.subcommand

import net.minestom.server.command.builder.arguments.Argument
import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.command.builder.exception.ArgumentSyntaxException
import org.slf4j.LoggerFactory
import world.cepi.kstom.command.arguments.generation.ClassArgumentGenerator
import world.cepi.kstom.command.arguments.generation.CustomArgumentGenerator
import world.cepi.kstom.command.arguments.generation.argumentsFromClass
import world.cepi.kstom.command.arguments.generation.generateSyntaxes
import world.cepi.kstom.command.arguments.literal
import world.cepi.kstom.command.kommand.Kommand
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
    addLambda: Kommand.SyntaxContext.(T, String) -> Unit,

    /** What to execute when meta is removed */
    removeLambda: Kommand.SyntaxContext.(KClass<out T>, String) -> Unit,

    vararg previousArgs: Argument<*>
) : Kommand({

    val logger = LoggerFactory.getLogger(KepiMetaManualSubcommand::class.java)

    val set by literal
    val remove by literal

    val metaClass = ArgumentType.Word("metaName").from(
        *allClasses
            .map { it.simpleName!!.lowercase().dropLast(dropString.length) }
            .toTypedArray()
    ).map { sealedClassName -> allClasses
        .firstOrNull { it.simpleName!!.lowercase().dropLast(dropString.length) == sealedClassName }
        ?: throw ArgumentSyntaxException("Meta is invalid", sealedClassName, 1)
    }

    allClasses.forEach { clazz ->
        try {
            val syntaxes = ClassArgumentGenerator(clazz)

            val clazzArgumentName = clazz.simpleName!!.lowercase().dropLast(dropString.length)

            val clazzArgument = argumentPerClassGenerator(clazz, clazzArgumentName)

            syntaxes.applySyntax(this, *previousArgs, set, clazzArgument) { instance ->
                addLambda(this, instance, clazzArgumentName)
            }

            syntax(*previousArgs, remove, clazzArgument) {
                removeLambda(this, context[metaClass], clazzArgumentName)
            }
        } catch (exception: Exception) {
            logger.warn("Could not generate class syntaxes for $clazz: ${exception.localizedMessage}")
        }
    }

    syntax(*previousArgs, remove, metaClass) {

        val clazzArgumentName = (!metaClass).simpleName!!.lowercase().dropLast(dropString.length)

        removeLambda(this, (!metaClass), clazzArgumentName)
    }


}, name)