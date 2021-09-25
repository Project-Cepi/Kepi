package world.cepi.kepi.data.singleton

import io.kotest.core.spec.style.StringSpec
import org.junit.jupiter.api.Assertions.assertEquals

class SingletonTests : StringSpec({

    "squares should parse correctly" {

        val square = Square(5, 5)

        assertEquals(square, SquareModel.asObject(SquareModel.asData(square).second))
    }

})