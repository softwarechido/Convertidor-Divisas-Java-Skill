package convertidordivisas.extension

import java.net.URL

/**
 * Get a url from a string!
 */
fun String.asUrl() = URL(this)