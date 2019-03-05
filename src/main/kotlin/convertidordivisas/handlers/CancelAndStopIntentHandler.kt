/*
     Copyright 2018 Amazon.com, Inc. or its affiliates. All Rights Reserved.

     Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file
     except in compliance with the License. A copy of the License is located at

         http://aws.amazon.com/apache2.0/

     or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for
     the specific language governing permissions and limitations under the License.
*/

package convertidordivisas.handlers

import com.amazon.ask.dispatcher.request.handler.HandlerInput
import com.amazon.ask.dispatcher.request.handler.RequestHandler
import com.amazon.ask.model.Response
import convertidordivisas.data.Constants
import convertidordivisas.dsl.response
import convertidordivisas.extension.StringPredicates
import convertidordivisas.extension.get
import convertidordivisas.helpers.Intents
import java.util.*

class CancelAndStopIntentHandler : RequestHandler, StringPredicates {
    override fun canHandle(input: HandlerInput) =
            input[Intents.Amazon.STOP orIntent Intents.Amazon.CANCEL]

    override fun handle(input: HandlerInput): Optional<Response> =
            response(input) {
                speech = Constants.Speech.STOP_BYE

                card {
                    title = Constants.Cards.STOP_TITLE
                    text = Constants.Cards.STOP_TEXT
                }

                endSession = true
            }
}
