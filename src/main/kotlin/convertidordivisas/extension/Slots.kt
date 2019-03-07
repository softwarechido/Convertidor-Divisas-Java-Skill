package convertidordivisas.extension

import com.amazon.ask.model.Slot

/**
 * Shortcut
 */
fun Slot.toDouble(): Double = value.toDouble()