package convertidordivisas.helpers

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class ConvertidorWebServiceTest {
    @Test
    fun testErrorIsAvailable() {
        val content = callURL("https://api.exchangeratesapi.io/latest?base=sier")
        println(content)
    }
}