package world.cepi.kepi.translations

import net.minestom.server.MinecraftServer
import world.cepi.kepi.SystemLoadStatus
import world.cepi.kstom.Manager
import java.io.BufferedInputStream
import java.net.URL
import java.nio.file.Path
import java.nio.file.Paths
import java.util.zip.ZipInputStream
import kotlin.io.path.*

object TranslationRegistry {

    /** Non-magic string for bundle. */
    const val bundle = "bundle"

    /** URL to grab the zip from */
    const val url = "https://github.com/Project-Cepi/Translations/releases/download/latest/pack.zip"

    /** Buffer size. We have a few kilos to spare. */
    const val bufferSize = 4096

    /** Cache folder for translations */
    val translationsFolder: Path = Paths.get("./translations")

    var loadingStatus: SystemLoadStatus = SystemLoadStatus.LOADING

    /**
     * Initializes and unpacks the translations necessary
     */
    @ExperimentalPathApi
    internal fun grab() {
        try {
            if (!translationsFolder.exists()) translationsFolder.createDirectories()

            ZipInputStream(BufferedInputStream(URL(url).openStream(), bufferSize)).use { zipInputStream ->
                generateSequence { zipInputStream.nextEntry }
                    .map { entry ->

                        val file = translationsFolder.resolve(entry.name)

                        if (entry.isDirectory) {
                            file.createDirectories()
                            return@map
                        }

                        if (!file.exists()) file.createFile() // create file

                        // write to file
                        file.outputStream().use { fileOutputStream ->
                            zipInputStream.copyTo(fileOutputStream, bufferSize)
                        }
                    }.toList()
            }

            loadingStatus = SystemLoadStatus.ENABLED

        } catch (exception: Exception) {
            Manager.extension.getExtension("Kepi")?.logger?.error("An unexpected error occured loading translations.")
            MinecraftServer.getExceptionManager().handleException(exception)
            loadingStatus = SystemLoadStatus.ERROR
        }
    }

    // TODO
    operator fun get(namespace: String, locale: Locale): String? {
        val path = translationsFolder.resolve(namespace).resolve("${bundle}_${locale.name}.properties")

        return null
    }

}