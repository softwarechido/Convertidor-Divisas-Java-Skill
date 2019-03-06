package convertidordivisas.extension

import com.amazon.ask.model.Slot

fun Slot.toDouble(): Double = value.toDouble()