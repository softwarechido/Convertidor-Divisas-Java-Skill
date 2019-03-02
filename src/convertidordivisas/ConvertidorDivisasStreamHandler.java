package convertidordivisas;

import com.amazon.ask.Skill;
import com.amazon.ask.Skills;
import com.amazon.ask.builder.CustomSkillBuilder;
import com.amazon.ask.SkillStreamHandler;
import convertidordivisas.handlers.CancelandStopIntentHandler;
import convertidordivisas.handlers.ConvertIntentHandler;
import convertidordivisas.handlers.HelpIntentHandler;
import convertidordivisas.handlers.SessionEndedRequestHandler;
import convertidordivisas.handlers.LaunchRequestHandler;

public class ConvertidorDivisasStreamHandler extends SkillStreamHandler {

    private static Skill getSkill() {
        
        CustomSkillBuilder requestHandlers = Skills.custom();        
        requestHandlers.addRequestHandler(new CancelandStopIntentHandler());
        requestHandlers.addRequestHandler(new ConvertIntentHandler());
        requestHandlers.addRequestHandler(new HelpIntentHandler());
        requestHandlers.addRequestHandler(new LaunchRequestHandler());
        requestHandlers.addRequestHandler(new SessionEndedRequestHandler());
        
        return requestHandlers.build();
    }

    public ConvertidorDivisasStreamHandler() {
        super(getSkill());
    }

}
