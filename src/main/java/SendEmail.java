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
        // Recipient's email ID needs to be mentioned.
//        String to = "CemenP93@gmail.com";

        // Sender's email ID needs to be mentioned
        String myEmail = "Semyonp93@gmail.com";
        final String username = "Semyonp93";//change accordingly
        final String password = "QPwoeiruty123";//change accordingly
        String opponentEmail = "CemenP93@gmail.com";
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

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(opponentEmail));
            message.setSubject("Testing send email");
            message.setText("Hello, this is sample for to check send email");
            Transport transport = session.getTransport("smtp");
            Transport.send(message);
            System.out.println("Sent message successfully....");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}