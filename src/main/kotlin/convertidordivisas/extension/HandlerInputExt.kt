package convertidordivisas.extension

import com.amazon.ask.dispatcher.request.handler.HandlerInput
import com.amazon.ask.model.Request
import com.amazon.ask.request.Predicates

/**
 * Make things more readable with sentences
 */
inline fun <reified T : Request> HandlerInput.isRequestType(): Boolean {
    return matches(Predicates.requestType(T::class.java))
}