package world.cepi.kepi.model.singleton

import kotlinx.serialization.InternalSerializationApi
import org.junit.jupiter.api.Test

class SingletonTests {
    
    @InternalSerializationApi
    @Test
    fun `square should parse correctly`() {

        val square = Square(5, 5)

        assert(square == SquareModel.asObject(SquareModel.asData(square).second))
    }

}