package no.applitude.mail;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class SendMail {

    public static void send(String title, String body, String to) {
        Properties props = new Properties();
        
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", Constants.HOST);
        props.put("mail.smtp.user", Constants.USER);
        props.put("mail.smtp.password", Constants.PASS);
        props.put("mail.smtp.port", Constants.PORT);
        props.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(props);
        
        InternetAddress toAddress = null;
        
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(Constants.USER));

            toAddress = new InternetAddress(to);

            message.addRecipient(Message.RecipientType.TO, toAddress);
                

            message.setSubject(title);
            message.setText(body);
            Transport transport = session.getTransport("smtp");
            transport.connect(Constants.HOST, Constants.USER, Constants.PASS);
            
            transport.sendMessage(message, message.getAllRecipients());
            
            transport.close();
        }
        catch (AddressException ae) {
            ae.printStackTrace();
        }
        catch (MessagingException me) {
            me.printStackTrace();
        }
    }
}
