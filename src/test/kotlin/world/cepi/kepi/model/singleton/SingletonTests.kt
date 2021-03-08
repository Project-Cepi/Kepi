package world.cepi.kepi.model.singleton

import org.junit.jupiter.api.Test

class SingletonTests {

    @Test
    fun `square should parse correctly`() {

        val square = Square(5, 5)

        assert(square == SquareModel.asObject(SquareModel.asData(square).second))
    }

}