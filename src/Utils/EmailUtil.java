package Utils;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.security.SecureRandom;

public class EmailUtil {

    private static final String SENDER_EMAIL = "thientx.personal@gmail.com";
    private static final String SENDER_PASSWORD = "mjpm qzmu sbyh aaas";
    private static final String SMTP_HOST = "smtp.gmail.com";
    private static final String SMTP_PORT = "587";

    public static void sendEmail(String recipientEmail, String subject, String content) throws Exception {
        Properties props = new Properties();
        props.put("mail.smtp.host", SMTP_HOST);
        props.put("mail.smtp.port", SMTP_PORT);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SENDER_EMAIL, SENDER_PASSWORD);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(SENDER_EMAIL));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
        message.setSubject(subject);
        message.setText(content);

        Transport.send(message);
    }

    public static void sendNewPasswordEmail(String recipientEmail) throws Exception {
        String newPassword = generateRandomPassword(16);
        String subject = "Mật khẩu mới của bạn";
        String content = "Chào bạn,\n\nMật khẩu mới của bạn là: " + newPassword + "\n\nVui lòng đổi mật khẩu sau khi đăng nhập.\n\nTrân trọng,\nĐội ngũ hỗ trợ";
        sendEmail(recipientEmail, subject, content);
    }

    public static String generateRandomPassword(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }
}