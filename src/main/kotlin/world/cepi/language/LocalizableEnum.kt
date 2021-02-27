package world.cepi.language

interface LocalizableEnum<V> : Localizable<V> {

    val contained: Localizable<V>

    override fun localize(locale: String, vararg arguments: String): V {
        return contained.localize(locale, arguments)
    }

}