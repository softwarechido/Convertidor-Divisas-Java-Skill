package convertidordivisas.handlers

import com.amazon.ask.dispatcher.request.handler.HandlerInput
import com.amazon.ask.dispatcher.request.handler.RequestHandler
import com.amazon.ask.model.Response
import com.amazon.ask.model.Slot
import convertidordivisas.data.Constants
import convertidordivisas.data.Intents
import convertidordivisas.dsl.response
import convertidordivisas.extension.*
import convertidordivisas.helpers.ConvertidorWebService
import java.util.*

class ConvertIntentHandler : RequestHandler {
    override fun canHandle(input: HandlerInput) = input matchesName Intents.CONVERT

    override fun handle(input: HandlerInput): Optional<Response> {
        val slots = input.slotsMap

        var speechText = Constants.Speech.CONVERT
        val cardText: String

        val divisaOrig = slots["divisaorigen"]
        val resolutionOrig = divisaOrig?.resolutions
                ?.resolutionsPerAuthority
                ?.first()

        if (resolutionOrig.isSuccessMatch) {
            val divisaDest = slots["divisadestino"]

            val resolutionDest = divisaDest?.resolutions
                    ?.resolutionsPerAuthority
                    ?.first()

            if (resolutionDest.isSuccessMatch) {
                val amount = slots["monto"]
                val quantity = amount?.toDouble()

                val idOrig = resolutionOrig?.get(0)?.valueId
                val idDest = resolutionDest?.get(0)?.valueId

                val result = ConvertidorWebService.convertir(idOrig, idDest, quantity)

                cardText = """${amount?.value} ${divisaOrig?.value} son ${String.format("%.2f", result)} ${divisaDest?.value}"""
                speechText = """$cardText $speechText"""
            } else {
                cardText = getUnknownSourceText(divisaDest)
                speechText = """$cardText $speechText"""
            }
        } else {
            cardText = getUnknownSourceText(divisaOrig)
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