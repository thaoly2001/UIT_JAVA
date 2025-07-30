package Utils;


import MODEL.Users;

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
}
