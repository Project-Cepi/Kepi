package world.cepi.kepi.data.namespace

import io.kotest.core.spec.style.StringSpec
import world.cepi.kepi.data.DataNamespace

class NamespaceTests : StringSpec({

    "namespaces should be parsed properly" {
        assert(DataNamespace("hello", "world").toString() == "hello_world")
    }

})