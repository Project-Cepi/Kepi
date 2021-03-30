package world.cepi.kepi.translations

import java.io.BufferedInputStream
import java.io.File
import java.net.URL
import java.util.zip.ZipFile
import java.util.zip.ZipInputStream

object TranslationRegistry {

    const val url = "https://github.com/Project-Cepi/Translations/releases/download/latest/pack.zip"
    val translationsFolder = File("./translations")

    /**
     * Initializes and unpacks the translations necessary
     */
    internal fun grab() {

        if (!translationsFolder.exists()) translationsFolder.mkdirs()

        ZipInputStream(BufferedInputStream(URL(url).openStream(), 1024)).use { zipInputStream ->
            generateSequence { zipInputStream.nextEntry }
                .filterNot { it.isDirectory }
                .map {
                    val file = File(translationsFolder, it.name)
                    file.mkdirs()
                    file.outputStream().use { fileOutputStream ->
                        zipInputStream.copyTo(fileOutputStream)
                    }
                }.toList()
        }

    }

    operator fun get(namespace: String, locale: Locale) {

    }

}