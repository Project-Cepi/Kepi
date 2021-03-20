package world.cepi.kepi.data

import org.junit.jupiter.api.Test
import world.cepi.kepi.data.DataNamespaceForge

class NamespaceTests {

    @Test
    fun `namespace should be parsed properly`() {
        assert(DataNamespaceForge("hello", "world").toString() == "hello.world")
    }

}