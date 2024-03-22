import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.Locale

class NumberFormatTest {
    @Test
    fun currency_check() {
        var format = NumberFormat.getCurrencyInstance(
            Locale("en", "US")
        )

        val value = BigDecimal("250000")

        println(format.format(value))

        format = NumberFormat.getCurrencyInstance(
            Locale("en", "GB")
        )
        println(format.format(value))

        format = NumberFormat.getCurrencyInstance(
            Locale("ja", "JP")
        )
        println(format.format(value))


        format = NumberFormat.getCurrencyInstance(
            Locale("fr", "FR")
        )

        println(format.format(value))

        format = NumberFormat.getCurrencyInstance(
            Locale("ru", "RU")
        )

        println(format.format(value))

        format = NumberFormat.getCurrencyInstance(
            Locale("ko", "KR")
        )

        println(format.format(value))

        format = NumberFormat.getCurrencyInstance(
            Locale("zh-CN", "CN")
        )

        println(format.format(value))

        format = NumberFormat.getCurrencyInstance(
            Locale("pt-BR", "BR")
        )

        println(format.format(value))

        format = NumberFormat.getCurrencyInstance(
            Locale("en-AU", "AU")
        )

        println(format.format(value))

        format = NumberFormat.getCurrencyInstance(
            Locale("en-CA", "CA")
        )

        println(format.format(value))

        format = NumberFormat.getCurrencyInstance(
            Locale("vi", "VN")
        )

        println(format.format(value))

        format = NumberFormat.getCurrencyInstance(
            Locale("hi", "IN")
        )

        println(format.format(value))
    }
}