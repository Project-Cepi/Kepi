package world.cepi.config

import java.util.function.BiConsumer

/**
 * Represents a value that is configured, and thus
 * subject to change.
 *
 * @param V The type of the value
 */
interface Configurable<V> {

    /**
     * Gets the current value of this configurable.
     * The output of this method will change when the value
     * is updated.
     *
     * @return The current value of this configurable
     */
    operator fun invoke(): V

    /**
     * Sets a [BiConsumer] callback to be executed whenever the
     * value of this configurable is updated.
     *
     * The first argument passed to the callback is the new value,
     * the second is the old value.
     *
     * The update might be called, even if the new and old values
     * are the same.
     *
     * @param callback The [BiConsumer] callback
     */
    fun onUpdate(callback: BiConsumer<V, V>)

}

