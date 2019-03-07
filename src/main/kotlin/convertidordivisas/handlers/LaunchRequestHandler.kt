/*
     Copyright 2019 Amazon.com, Inc. or its affiliates. All Rights Reserved.

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
import com.amazon.ask.model.LaunchRequest
import convertidordivisas.data.Constants
import convertidordivisas.dsl.response
import convertidordivisas.extension.isRequestType

class LaunchRequestHandler : RequestHandler {
    override fun canHandle(input: HandlerInput) = input.isRequestType<LaunchRequest>()

    override fun handle(input: HandlerInput) =
            response(from = input) {
                speech = Constants.Speech.WELCOME
                reprompt = Constants.Speech.WELCOME

                card {
                    title = Constants.Cards.LAUNCH_TITLE
                    text = Constants.Cards.LAUNCH_TEXT
                }
            }


}
