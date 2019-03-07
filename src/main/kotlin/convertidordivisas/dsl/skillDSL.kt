package convertidordivisas.dsl

import com.amazon.ask.Skill
import com.amazon.ask.Skills
import com.amazon.ask.builder.CustomSkillBuilder
import com.amazon.ask.dispatcher.request.handler.HandlerInput
import com.amazon.ask.dispatcher.request.handler.RequestHandler
import com.amazon.ask.model.Response
import com.amazon.ask.request.handler.GenericRequestHandler
import java.util.*

/**
 * Let's build a skill!!
 */
inline fun skill(buildSkillBlock: CustomSkillBuilder.() -> Unit): Skill {
    val customSkill = Skills.custom()
    customSkill.buildSkillBlock()
    return customSkill.build()
}

/**
 * Some extensions for keep things "readable"
 */
inline fun CustomSkillBuilder.handler(block: () -> GenericRequestHandler<HandlerInput, Optional<Response>>) {
    addRequestHandler(block())
}

/**
 * Weird set of elements for a weird experiment…
 */
@DslMarker
annotation class DynamicSkill

@DynamicSkill
class HandlerBuilder(var predicate: HandlerInput.() -> Boolean = { false },
                     var handler: HandlerInput.() -> Optional<Response> = { Optional.empty() }) {
    fun predicate(block: HandlerInput.() -> Boolean) {
        this.predicate = block
    }
}

inline fun buildHandler(buildBlock: HandlerBuilder.() -> Unit): RequestHandler {
    val handlerBuilder = HandlerBuilder()
    handlerBuilder.buildBlock()

    return object : RequestHandler {
        override fun canHandle(input: HandlerInput): Boolean =
                handlerBuilder.predicate(input)

        override fun handle(input: HandlerInput): Optional<Response> =
                handlerBuilder.handler(input)
    }
}