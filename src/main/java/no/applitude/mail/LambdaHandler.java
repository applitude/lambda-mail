package no.applitude.mail;

import com.amazonaws.services.lambda.runtime.Context;

public class LambdaHandler{
    public Object handler(LambdaMessage lm, Context c) throws Exception{
    
        if( lm.isValid() ){
            SendMail.send( lm.getTitle(), lm.getBody(), lm.getTo() );
        }

        return lm;
    }
}

