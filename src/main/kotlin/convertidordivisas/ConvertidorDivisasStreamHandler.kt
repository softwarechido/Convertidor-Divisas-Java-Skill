package convertidordivisas

import com.amazon.ask.SkillStreamHandler
import com.amazon.ask.request.Predicates.intentName
import convertidordivisas.dsl.buildHandler
import convertidordivisas.dsl.handler
import convertidordivisas.dsl.response
import convertidordivisas.dsl.skill
import convertidordivisas.handlers.*

class ConvertidorDivisasStreamHandler : SkillStreamHandler(skill {
    handler { CancelAndStopIntentHandler() }
    handler { ConvertIntentHandler() }
    handler { HelpIntentHandler() }
    handler { LaunchRequestHandler() }
    handler { SessionEndedRequestHandler() }

    /**
     * Weird experiment for dynamic generation…
     */
    handler {
        buildHandler {
            predicate {
                matches(intentName("SierIntent"))
            }

            handler = {
                response(this) {
                    speech = "Yeah… this won't be called"
                }
            }
        }
    }
})