package Utils;


import MODEL.Users;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author ADMIN
 */
public class LoginUtils {

    private static Users u = new Users();

    public static void setUsers(Users us) {
        u = us;
    }

    public static String getUserName() {
        return u.getUsername();
    }

    public static Long getId() {
        return u.getId();
    }
    
     public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashed = md.digest(password.getBytes());
            
            // Convert to hex string
            StringBuilder sb = new StringBuilder();
            for (byte b : hashed) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
