package no.applitude.mail;

import java.util.Arrays;

public class LambdaMessage{

    private String title;
    private String body;
    private String to;
    
    private boolean valid;

    public void setTo(String to){

        if( to.length() > 50 ){
            return;
        }
        
        int atPos = 0;
        
        char[] addr = to.toCharArray();

        // detect invalid characters
        for(int i=0; i<addr.length; i++){
        
            char c = addr[i];
        
            if( (c < 40) || (c > 125) ){
                return;
            }
            
            // Upper-case to lower-case
            if( (c < 91) && (c > 64) ){
                addr[i] = (char)( c + 32 );
            }
            
            if( c == '@' ){
                
                if( (atPos == 0) && (i > 0) ){
                    atPos = i;
                }else{
                    return;
                }
            }
        }
        
        // if @ is later than this the address is invalid
        if( atPos > ( addr.length - 3) ){
            return;
        }
        
        char[] domain = Arrays.copyOfRange( addr, (atPos + 1), addr.length );
        
        String testDomain = new String( domain );
        
        // recipient domain is incorrect
        if( !( testDomain.equalsIgnoreCase( Constants.DOMAIN ) && ( Constants.DOMAIN.trim().length() > 0 ) ) ){
            return;
        }
        
        this.to = new String( addr );
        this.valid = true;

    }
    
    public boolean isValid(){
        return valid;
    }
    
    public void setBody(String body){
        this.body = body;
    }
    
    public void setTitle(String title){
        this.title = title;
    }
    
    public String getTitle(){
        return title;
    }
    public String getTo(){
        return to;
    }
    public String getBody(){
        return body;
    }
}
