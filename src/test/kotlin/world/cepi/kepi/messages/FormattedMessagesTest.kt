package world.cepi.kepi.messages

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer

class FormattedMessagesTest : StringSpec({
    "one paramater should pass correctly" {
        PlainTextComponentSerializer.plainText().serialize(
            Component.text("Hello %1!").formatPercent(
                Component.text("World")
            )
        ) shouldBe "Hello World!"
    }

    "multiple paramaters should pass correctly" {
        PlainTextComponentSerializer.plainText().serialize(
            Component.text("Hey %1, %2, and %3!").formatPercent(
                Component.text("A"),
                Component.text("B"),
                Component.text("C")
            )
        ) shouldBe "Hey A, B, and C!"
    }

    "many paramaters should pass" {
        PlainTextComponentSerializer.plainText().serialize(
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
        ) shouldBe "Letters: ABCABCABCABC"
    }

    "errors should pass to the string without any issue" {
        PlainTextComponentSerializer.plainText().serialize(
            Component.text("Hello %1!").formatPercent()
        ) shouldBe "Hello !!!"
    }
})