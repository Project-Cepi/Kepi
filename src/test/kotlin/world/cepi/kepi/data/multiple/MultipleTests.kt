package world.cepi.kepi.data.multiple

import io.kotest.core.spec.style.StringSpec
import org.junit.jupiter.api.Assertions.assertEquals
import world.cepi.kepi.data.DataHandlerFactory
import world.cepi.kepi.data.database.implementations.MapDatabase
import world.cepi.kepi.data.singleton.Square

class MultipleTests : StringSpec({
    "square owner should parse correctly" {

        val squareOwner = SquareOwner("bob", Square(5, 5))
        val otherSquareOwner = SquareOwner("jeff", Square(10, 10))

        assertEquals(squareOwner, SquareOwnerModel.asObject(SquareOwnerModel.asData(squareOwner).second))

        TestDataHandler.main[SquareOwnerModel] = squareOwner
        assertEquals(squareOwner, TestDataHandler.main[SquareOwnerModel, "bob"])

        TestDataHandler.main[SquareOwnerModel] = otherSquareOwner
        assertEquals(otherSquareOwner, TestDataHandler.main[SquareOwnerModel, "jeff"])
        assertEquals(otherSquareOwner, TestDataHandler.main[SquareOwnerModel, 1])
    }

}) {
    object TestDataHandler: DataHandlerFactory({ MapDatabase(it) })
}