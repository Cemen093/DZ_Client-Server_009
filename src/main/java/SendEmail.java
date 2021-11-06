import com.sun.mail.util.MailSSLSocketFactory;

import java.security.GeneralSecurityException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {
    public static void main(String[] args) throws GeneralSecurityException {
        //Реализовать приложение на JavaFX (поставить через maven)
        // для массовой рассылки электронной почты.

        // Sender's email ID needs to be mentioned
        String myEmail = "Semyonp93@gmail.com";
        final String username = "Semyonp93";//change accordingly
        final String password = "**********";//change accordingly
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);


        Properties props = (Properties) System.getProperties().clone();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.port", "587");
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.ssl.enable", false);
        props.put("mail.smtp.starttls.enable", true);

        // Get the Session object.
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
                });

        String[] opponentEmails = {"CemenP93@gmail.com", "Email_2@gmail.com", "Email_3@gmail.com"};
        Message message;
        try {
            for (int i = 0; i < opponentEmails.length; i++) {
                message = new MimeMessage(session);
                message.setFrom(new InternetAddress(myEmail));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(opponentEmails[i]));
                message.setSubject("Hello");
                message.setText("Hi, this is my bulk email.");
                Transport transport = session.getTransport("smtp");
                Transport.send(message);
            }
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}