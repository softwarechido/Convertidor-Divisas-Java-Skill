package convertidordivisas.extension

import java.net.URL

interface StringURLConverter {
    fun String.asUrl(): URL = URL(this)
}