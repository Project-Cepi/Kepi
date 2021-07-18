package world.cepi.kepi.messages.translations

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

object TranslationRegistry {

    /** Namespace, (Locale as Code -- Properties)*/
    private val cache: MutableMap<String, MutableMap<String, Properties>> = mutableMapOf()

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
            Manager.exception.handleException(exception)
            loadingStatus = SystemLoadStatus.ERROR
        }
    }

    /**
     * Grabs a translation from the specified [namespace] at a [key] with a [locale].
     */
    operator fun get(namespace: String, key: String, locale: String): String? {

        run {
            // Check if the cache contains this namespace & locale.
            if (cache.contains(namespace) && cache[namespace]!!.containsKey(locale)) {
                // Return the value if so (if it isnt a string return null)
                return cache[namespace]!![locale]!![key] as? String
            }
        }

        val pathNamespace = translationsFolder
            .resolve(namespace)

        val path = pathNamespace.resolve("bundle_${locale.replace('-', '_')}.properties")
            .let {
                // If the path is not found attempt to use en_US instead
                if (!it.exists())
                    return@let pathNamespace.resolve("bundle_en_us.properties")
                else
                    return@let it
            }

        try {
            Files.newInputStream(path).use { input ->
                val prop = Properties()

                // load a properties file
                prop.load(input)

                // Set the namespace map if it isnt there
                if (cache[namespace] == null) cache[namespace] = mutableMapOf()

                // cache the proeprties
                cache[namespace]!![locale] = prop

                // get the property value and return it
                return prop.getProperty(key)
            }
        } catch (ex: IOException) {
            ex.printStackTrace()
        }

        return null
    }

}