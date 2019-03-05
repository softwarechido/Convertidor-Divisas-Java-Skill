package convertidordivisas.extension

import com.amazon.ask.dispatcher.request.handler.HandlerInput
import com.amazon.ask.request.Predicates.intentName
import java.net.URL
import java.util.function.Predicate

/**
 * Get a url from a string!
 */
fun String.toUrl() = URL(this)

/**
 * The thing is that all extension functions will propagate as static methods and you can invoke them
 * from any single instance of the class. Like adding stuff to the prototype in JS.
 *
 * How can we manage that only certain places can
 */
interface StringPredicates {
    fun String.toPredicate(): Predicate<HandlerInput> = intentName(this)

    infix fun String.orIntent(other: String): Predicate<HandlerInput> {
        return this.toPredicate() orOther other.toPredicate()
    }
}