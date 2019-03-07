package convertidordivisas.extension

import com.amazon.ask.model.IntentRequest
import com.amazon.ask.model.Slot

/**
 * Shortcut, yep, just that
 */
val IntentRequest.slotsMap: Map<String, Slot>
    get() = intent.slots