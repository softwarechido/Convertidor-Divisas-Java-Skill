package convertidordivisas.dsl

import com.amazon.ask.dispatcher.request.handler.HandlerInput
import com.amazon.ask.model.Response
import java.util.*

/**
 * This annotation allow to avoid inner calls between blocks of different context. So NO ONE can say:
 * ```
 * response(…) {
 *      card {
 *          card {
 *
 *          }
 *      }
 * }
 * ```
 *
 * Also we remove access to top data, avoiding code like:
 *
 * ```
 * response(…) {
 *      card {
 *          speech = …
 *      }
 * }
 * ```
 */
@DslMarker
annotation class ResponseDSL


@ResponseDSL
class ResponseBuilderDSL {
    var speech: String? = null
    var reprompt: String? = null
    var endSession: Boolean? = null

    private var cardBuilderDSL: CardBuilderDSL? = null

    fun card(cardBlock: CardBuilderDSL.() -> Unit) {
        cardBuilderDSL = CardBuilderDSL()
        cardBuilderDSL?.cardBlock()
    }

    fun card(): CardBuilderDSL? = cardBuilderDSL
}

@ResponseDSL
class CardBuilderDSL {
    var title: String? = null
    var text: String? = null
}

/**
 * Create a response using DSL Block. This allows you to call a block like:
 * ```
 * response(from = input) {
 *      speech = …
 *      reprompt = …
 *      card {
 *          title = …
 *          text = …
 *      }
 * }
 * ```
 * Further customization can be added in this function to check for data. In the end it doesn't matter that much
 * because the Amazon builders take care of empty values.
 *
 * @param from a [HandlerInput] from which we create the response
 * @param block a function with receiver to execute as the block
 */
inline fun response(from: HandlerInput, block: ResponseBuilderDSL.() -> Unit = {}): Optional<Response> {
    val responseDSL = ResponseBuilderDSL().apply(block)

    val responseBuilder = from.responseBuilder.withSpeech(responseDSL.speech)

    responseDSL.card()?.let { responseBuilder.withSimpleCard(it.title, it.text) }

    return responseBuilder
            .withReprompt(responseDSL.reprompt)
            .withShouldEndSession(responseDSL.endSession)
            .build()
}