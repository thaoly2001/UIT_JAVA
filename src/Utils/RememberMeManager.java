package Utils;
import java.util.prefs.Preferences;

public class RememberMeManager {
    private Preferences prefs;

    public RememberMeManager() {
        prefs = Preferences.userRoot().node(this.getClass().getName());
    }

    public void saveLogin(String username, String password, boolean remember) {
        if (remember) {
            prefs.put("username", username);
            prefs.put("password", password);
        } else {
            prefs.remove("username");
            prefs.remove("password");
        }
    }

    public String getUsername() {
        return prefs.get("username", "");
    }

    public String getPassword() {
        return prefs.get("password", "");
    }
}
