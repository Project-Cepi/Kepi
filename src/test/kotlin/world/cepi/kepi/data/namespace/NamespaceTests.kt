package world.cepi.kepi.data.namespace

import org.junit.jupiter.api.Test
import world.cepi.kepi.data.DataNamespace

class NamespaceTests {

    @Test
    fun `namespace should be parsed properly`() {
        assert(DataNamespace("hello", "world").toString() == "hello.world")
    }

}