package world.cepi.kepi.messages

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class FormattedMessagesTest {

    @Test
    fun `check input of one param`() {
        assertEquals(
            PlainTextComponentSerializer
                .plainText()
                .serialize(
                    Component.text("Hello %1!").formatPercent(
                        Component.text("World")
                    )
                ), "Hello World!")
    }

    @Test
    fun `check input of single-digit param`() {
        assertEquals(
            PlainTextComponentSerializer
                .plainText()
                .serialize(
                    Component.text("Hey %1, %2, and %3!").formatPercent(
                        Component.text("A"),
                        Component.text("B"),
                        Component.text("C")
                    )
                ), "Hey A, B, and C!")
    }

    @Test
    fun `check input of multi-digit param`() {
        assertEquals(
            PlainTextComponentSerializer
                .plainText()
                .serialize(
                    Component.text("Letters: %1%2%3%4%5%6%7%8%9%10%11%12").formatPercent(
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
                ), "Letters: ABCABCABCABC")
    }

    @Test
    fun `make sure !! works correctly`() {
        assertEquals(
            PlainTextComponentSerializer
                .plainText()
                .serialize(
                    Component.text("Hello %1!").formatPercent()
                ), "Hello !!!")
    }

}