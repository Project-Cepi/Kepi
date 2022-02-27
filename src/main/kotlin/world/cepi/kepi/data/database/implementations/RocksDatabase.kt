package world.cepi.kepi.data.database.implementations

import org.rocksdb.*
import world.cepi.kepi.data.DataNamespace
import world.cepi.kepi.data.database.DatabaseHandler


/**
 * Database located purely in memory. Should be used for testing only.
 */
class RocksDatabase(
    dataNamespace: DataNamespace
) : DatabaseHandler(dataNamespace) {

    init { Companion }

    val options = Options().setCreateIfMissing(true)

    val database = RocksDB.open(options, "./data/${dataNamespace}")

    override fun close() {
        database.close()
        options.close()

    }

    companion object {
        init {
            RocksDB.loadLibrary()
        }
    }

    init {
        val options = Options().setCreateIfMissing(true)

        RocksDB.open(options, "./data/${dataNamespace}")
    }

    /** String (namespace) that stores (namespaces) paired to string (data) */

    override fun erase(key: String) {
        database.delete(key.toByteArray())
    }

    override operator fun get(key: String): String? {
        return database.get(key.toByteArray())?.toString()
    }

    override operator fun set(key: String, data: String) {
        database.put(key.toByteArray(), data.toByteArray())
    }
}