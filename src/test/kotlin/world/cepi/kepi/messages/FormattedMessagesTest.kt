package world.cepi.kepi.messages

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.plain.PlainComponentSerializer
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class FormattedMessagesTest {

    @Test
    fun `check input of one param`() {
        assertEquals(
            PlainComponentSerializer
                .plain()
                .serialize(
                    createFormattedMessage(
                        Component.text("Hello %1!"),
                        Component.text("World")
                    )
                ), "| Hello World!")
    }

    @Test
    fun `check input of single-digit param`() {
        assertEquals(
            PlainComponentSerializer
                .plain()
                .serialize(
                    createFormattedMessage(
                        Component.text("Hey %1, %2, and %3!"),
                        Component.text("A"),
                        Component.text("B"),
                        Component.text("C")
                    )
                ), "| Hey A, B, and C!")
    }

    @Test
    fun `check input of multi-digit param`() {
        assertEquals(
            PlainComponentSerializer
                .plain()
                .serialize(
                    createFormattedMessage(
                        Component.text("Letters: %1%2%3%4%5%6%7%8%9%10%11%12"),
                        Component.text("A"), // 1
                        Component.text("B"), // 2
                        Component.text("C"), // 3
                        Component.text("A"), // 4
                        Component.text("B"), // 5
                        Component.text("C"), // 6
                        Component.text("A"), // 7
                        Component.text("B"), // 8
                        Component.text("C"), // 9
                        Component.text("A"), // 10
                        Component.text("B"), // 11
                        Component.text("C")  // 12
                    )
                ), "| Letters: ABCABCABCABC")
    }

}