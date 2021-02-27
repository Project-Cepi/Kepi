package world.cepi.language

/**
 * Represents an object that can be localized to some player's locale.
 * Usually used with text, but can be used for different types as well.
 *
 * The object prepared for localization may contain placeholders that
 * should be replaced with some given arguments, while localizing.
 * These are used for example to place player names to localizable messages,
 * or any other values that should not be localized in the message.
 *
 * @param V The result of the localization
 */
interface Localizable<V> {

    /**
     * Localizes the object to the given locale, and replaces the placeholders
     * with the given arguments in the order. This corresponds to the x:th
     * element in the arguments replacing the placeholder marked as x.
     *
     * If the object cannot be localized to the given locale, default language
     * is used. See: [LanguageProvider.getDefaultLocale].
     *
     * @param locale The locale, in Minecraft's format.
     * @param arguments The arguments in string form
     *
     * @return The localized object
     */
    fun localize(locale: String, vararg arguments: String): V

    /**
     * Same as [localize], but accepts arguments of Any type. The arguments are
     * checked for null, and their [Any.toString] is invoked to get a string value.
     * If the object is null, "null" string will be substituted.
     *
     * If the argument happens to be of type [Localizable] this will attempt localize
     * it with the same locale as the one provided to this method. If that results in
     * a non-null string as a result, then it is used. This is to allow passing
     * localizables as arguments to localization and having them localized with the same
     * locale.
     *
     * @param locale The locale, in Minecraft's format.
     * @param arguments The arguments in any form, even null
     *
     * @return The localized object
     */
    fun localize(locale: String, vararg arguments: Any?): V {
        // TODO: JsonMessage compound

        val processedArguments = Array<String>(arguments.size) { "null" }

        var i = 0
        for (arg in arguments) {
            if (arg == null) {
                processedArguments[i++] = "null"
                continue
            } else if (arg is Localizable<*>) {
                val tryString: String? = arg.localize(locale, Array<String>(0) { "" }) as? String

                if (tryString != null) {
                    // It indeed was Localizable<String>.
                    processedArguments[i++] = tryString
                    continue
                }
            }

            processedArguments[i++] = arg.toString()
        }

        return localize(locale, processedArguments)
    }

}