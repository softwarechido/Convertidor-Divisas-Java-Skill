package convertidordivisas

import com.amazon.ask.Skill
import com.amazon.ask.SkillStreamHandler
import com.amazon.ask.Skills
import com.amazon.ask.builder.CustomSkillBuilder
import com.amazon.ask.dispatcher.request.handler.HandlerInput
import com.amazon.ask.model.Response
import com.amazon.ask.request.handler.GenericRequestHandler
import convertidordivisas.handlers.*
import java.util.*

class ConvertidorDivisasStreamHandler : SkillStreamHandler(skill {
    addRequestHandler(CancelandStopIntentHandler())
    addRequestHandler(ConvertIntentHandler())
    addRequestHandler(HelpIntentHandler())
    addRequestHandler(LaunchRequestHandler())
    addRequestHandler(SessionEndedRequestHandler())
})

inline fun skill(buildSkillBlock: CustomSkillBuilder.() -> Unit): Skill {
    val customSkill = Skills.custom()
    customSkill.buildSkillBlock()
    return customSkill.build()
}