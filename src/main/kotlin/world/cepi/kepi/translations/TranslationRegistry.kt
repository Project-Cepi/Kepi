package world.cepi.kepi.translations

import java.io.BufferedInputStream
import java.io.File
import java.net.URL
import java.nio.file.LinkOption
import java.nio.file.Paths
import java.util.zip.ZipInputStream
import kotlin.io.path.*

object TranslationRegistry {

    /** URL to grab the zip from */
    const val url = "https://github.com/Project-Cepi/Translations/releases/download/latest/pack.zip"
    val translationsFolder = Paths.get("./translations")

    /**
     * Initializes and unpacks the translations necessary
     */
    @ExperimentalPathApi
    internal fun grab() {

        if (!translationsFolder.exists()) translationsFolder.createDirectories()

        ZipInputStream(BufferedInputStream(URL(url).openStream(), 1024)).use { zipInputStream ->
            generateSequence { zipInputStream.nextEntry }
                .filterNot { it.isDirectory }
                .map {
                    val file = translationsFolder.resolve(it.name)

                    file.parent.createDirectories() // create above folder
                    if (!file.exists()) file.createFile() // create file

                    // write to file
                    file.outputStream().use { fileOutputStream ->
                        zipInputStream.copyTo(fileOutputStream)
                    }
                }.toList()
        }

    }

    operator fun get(namespace: String, locale: Locale) {

    }

}