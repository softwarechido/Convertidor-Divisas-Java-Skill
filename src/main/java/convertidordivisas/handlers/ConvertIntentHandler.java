/*
     Copyright 2019 Amazon.com, Inc. or its affiliates. All Rights Reserved.

     Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file
     except in compliance with the License. A copy of the License is located at

         http://aws.amazon.com/apache2.0/

     or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for
     the specific language governing permissions and limitations under the License.
*/

package convertidordivisas.handlers;

import java.util.Map;
import java.util.Optional;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.response.ResponseBuilder;
import convertidordivisas.helpers.ConvertidorWebService;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Slot;
import com.amazon.ask.model.slu.entityresolution.Resolution;
import com.amazon.ask.model.slu.entityresolution.StatusCode;
import static com.amazon.ask.request.Predicates.intentName;

public class ConvertIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("ConvertirIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {

        String speechText = " <break time=\"1s\"/> Me puedes decirme Convertir divisa o cancela para salir, ¿Cómo te puedo ayudar?";
        String card = "";

        IntentRequest intentRequest = (IntentRequest) input.getRequestEnvelope().getRequest();
        Map<String,Slot> slots = intentRequest.getIntent().getSlots();

        Slot divisaOrigen = slots.get("divisaorigen");
        Slot divisaDestino = slots.get("divisadestino");
        Slot monto = slots.get("monto");

        Double cantidad = Double.parseDouble(monto.getValue());

        Resolution resolutionOrigen = divisaOrigen.getResolutions().getResolutionsPerAuthority().get(0);
        StatusCode statusOrigen = resolutionOrigen.getStatus().getCode();
        
        if (null != statusOrigen && statusOrigen.equals(StatusCode.ER_SUCCESS_MATCH)){
            
            String idOrigen = resolutionOrigen.getValues().get(0).getValue().getId();

            Resolution resolutionDestino = divisaDestino.getResolutions().getResolutionsPerAuthority().get(0);
            StatusCode statusDestino = resolutionDestino.getStatus().getCode();
            
            if ( null != statusDestino && statusDestino.equals(StatusCode.ER_SUCCESS_MATCH) ) {
                String idDestino = resolutionDestino.getValues().get(0).getValue().getId();
                System.out.println("convertidorDivisas:" + "idOrigen:" + idOrigen + ", idDestino:" + idDestino + ", Cantidad:"+cantidad);
                Double resultado = ConvertidorWebService.convertir(idOrigen, idDestino, cantidad);
                speechText = monto.getValue() + " " + divisaOrigen.getValue()+" son "+ 
                    String.format("%.2f",resultado) + " " + divisaDestino.getValue() + speechText;
                card = monto.getValue() + " " + divisaOrigen.getValue()+ " son " + String.format("%.2f",resultado) + " " + divisaDestino.getValue(); 
            }
            else {
                speechText = "Lo siento, no reconozco " + divisaDestino.getValue() + " como divisa " + speechText;
                card = "Lo siento, no reconozco " + divisaDestino.getValue() + " como divisa "; 
            }
        }
        else {
            speechText = "Lo siento, no reconozco " + divisaOrigen.getValue() + " como divisa " + speechText;           
            card = "Lo siento, no reconozco " + divisaOrigen.getValue() + " como divisa "; 
        }

        ResponseBuilder responseBuilder = input.getResponseBuilder();
        responseBuilder.withSpeech(speechText);
        responseBuilder.withSimpleCard("Convertir Divisa", card);
        responseBuilder.withReprompt(speechText);

        return responseBuilder.build();
    }

}
