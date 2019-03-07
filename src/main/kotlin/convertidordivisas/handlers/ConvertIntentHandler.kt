package convertidordivisas.handlers

import com.amazon.ask.dispatcher.request.handler.HandlerInput
import com.amazon.ask.dispatcher.request.handler.RequestHandler
import com.amazon.ask.model.Response
import com.amazon.ask.model.Slot
import convertidordivisas.data.Constants
import convertidordivisas.data.Intents
import convertidordivisas.dsl.response
import convertidordivisas.extension.*
import convertidordivisas.helpers.exchangeCall
import java.util.*

class ConvertIntentHandler : RequestHandler {
    override fun canHandle(input: HandlerInput) = input matchesName Intents.CONVERT

    override fun handle(input: HandlerInput): Optional<Response> {
        val slots = input.slotsMap

        var speechText = Constants.Speech.CONVERT
        val cardText: String

        val exchangeOrig = slots["divisaorigen"]
        val resolutionOrig = exchangeOrig?.resolutions
                ?.resolutionsPerAuthority
                ?.first()

        if (resolutionOrig.isSuccessMatch) {
            val exchangeDest = slots["divisadestino"]

            val resolutionDest = exchangeDest?.resolutions
                    ?.resolutionsPerAuthority
                    ?.first()

            if (resolutionDest.isSuccessMatch) {
                val idOrig = resolutionOrig?.get(0)?.valueId ?: ""
                val idDest = resolutionDest?.get(0)?.valueId ?: ""

                val amount = slots["monto"]?.toDouble() ?: 0.0

                val result = exchangeCall(
                        source = idOrig,
                        dest = idDest,
                        amount = amount)

                cardText = """$amount ${exchangeOrig?.value} son ${String.format("%.2f", result)} ${exchangeDest?.value}"""
                speechText = """$cardText $speechText"""
            } else {
                cardText = getUnknownSourceText(exchangeDest)
                speechText = """$cardText $speechText"""
            }
        } else {
            cardText = getUnknownSourceText(exchangeOrig)
            speechText = """$cardText $speechText"""
        }

        return response(input) {
            speech = speechText
            reprompt = speechText

            card {
                title = Constants.Cards.CONVERT_TITLE
                text = cardText
            }
        }
    }

    private fun getUnknownSourceText(sourceSlot: Slot?): String =
            """Lo siento, no reconozco ${sourceSlot?.value} como divisa"""
}