package convertidordivisas

import com.amazon.ask.Skill
import com.amazon.ask.SkillStreamHandler
import com.amazon.ask.Skills
import convertidordivisas.handlers.*

private val skill: Skill
    get() {
        return with(Skills.custom()) {
            addRequestHandler(CancelandStopIntentHandler())
            addRequestHandler(ConvertIntentHandler())
            addRequestHandler(HelpIntentHandler())
            addRequestHandler(LaunchRequestHandler())
            addRequestHandler(SessionEndedRequestHandler())
            build()
        }
    }

class ConvertidorDivisasStreamHandler : SkillStreamHandler(skill)
