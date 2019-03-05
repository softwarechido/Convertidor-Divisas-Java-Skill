package convertidordivisas.extension

import com.amazon.ask.dispatcher.request.handler.HandlerInput
import com.amazon.ask.model.Request
import com.amazon.ask.request.Predicates
import java.util.function.Predicate

/**
 * Make things more readable with sentences
 */
inline fun <reified T : Request> HandlerInput.isRequestType(): Boolean {
    return matches(Predicates.requestType(T::class.java))
}

/**
 * Extension function to create a readable way for matching a name in string format
 *
 * @param name a String representing an intent
 */
infix fun HandlerInput.matchesName(name: String): Boolean = matches(Predicates.intentName(name))

/**
 * Overriding the `get` operator allow us to write cool stuff between `[]`
 * like:
 *
 *          `input[Intents.Amazon.STOP orIntent Intents.Amazon.CANCEL]`
 *
 * in [convertidordivisas.handlers.CancelAndStopIntentHandler]
 * Unfortunately if we want to do similar stuff with other types we need to override the operator
 * once for every type parameter. We can do a generic implementation also.
 */
operator fun HandlerInput.get(predicate: Predicate<HandlerInput>): Boolean = matches(predicate)