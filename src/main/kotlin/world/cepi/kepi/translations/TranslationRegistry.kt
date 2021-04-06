package world.cepi.kepi.translations

import net.minestom.server.MinecraftServer
import world.cepi.kepi.SystemLoadStatus
import world.cepi.kstom.Manager
import java.io.BufferedInputStream
import java.io.IOException
import java.net.URL
import java.nio.file.*
import java.nio.file.attribute.BasicFileAttributes
import java.util.*
import java.util.zip.ZipInputStream
import kotlin.io.path.*
import java.util.Properties

import java.io.FileInputStream

import java.io.InputStream




object TranslationRegistry {

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
            else {
                Files.walkFileTree(translationsFolder, object : SimpleFileVisitor<Path>() {
                    override fun visitFile(file: Path?, attrs: BasicFileAttributes?): FileVisitResult {
                        file?.deleteIfExists()
                        return FileVisitResult.CONTINUE;
                    }

                    override fun postVisitDirectory(dir: Path?, exc: IOException?): FileVisitResult {
                        if (dir != translationsFolder)
                            dir?.deleteIfExists()
                        return FileVisitResult.CONTINUE;
                    }
                })
            }

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

    // TODO cache
    /**
     * Grabs a translation from the specified [namespace] at a [key] with a locale.
     */
    @ExperimentalPathApi
    operator fun get(namespace: String, key: String, locale: Locale): String? {
        val path = translationsFolder.resolve(namespace).resolve("bundle_${locale.toLanguageTag().replace('-', '_')}.properties")

        try {
            path.inputStream().use { input ->
                val prop = Properties()

                // load a properties file
                prop.load(input)

                // get the property value and return it
                return prop.getProperty(key)
            }
        } catch (ex: IOException) {
            ex.printStackTrace()
        }

        return null
    }

}