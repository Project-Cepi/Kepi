package world.cepi.kepi.namespace

import org.junit.jupiter.api.Test
import world.cepi.kepi.data.DataNamespaceForge

class NamespaceTests {

    @Test
    fun `namespace should be parsed properly`() {
        assert(DataNamespaceForge("hello", "world").toString() == "hello.world")
    }

}