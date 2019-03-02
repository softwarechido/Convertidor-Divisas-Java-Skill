package convertidordivisas.extension

import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

/**
 * Do the safe calls in a separate place. People doesn't have to worry about the errors if we can manage them
 */
inline fun URL.fetchData(block: (BufferedReader) -> Unit) {
    var input: BufferedReader? = null
    try {
        val connection = openConnection()
        input = BufferedReader(InputStreamReader(connection.getInputStream()))
        block(input)
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        input?.close()
    }
}