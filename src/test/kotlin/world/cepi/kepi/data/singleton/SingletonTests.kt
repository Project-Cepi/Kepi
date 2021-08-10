package world.cepi.kepi.data.singleton

import kotlinx.serialization.InternalSerializationApi
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SingletonTests {
    
    @InternalSerializationApi
    @Test
    fun `square should parse correctly`() {

        val square = Square(5, 5)

        assertEquals(square, SquareModel.asObject(SquareModel.asData(square).second))
    }

}