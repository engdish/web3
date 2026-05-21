package validation;

import javax.ejb.Stateless;
import java.util.Locale;
import java.util.ResourceBundle;



@Stateless
public class PointValidator {
    private static final ResourceBundle MESSAGES =
            ResourceBundle.getBundle("i18n.messages", Locale.getDefault());


    public void validate(Integer x, Double y, Integer r) {
        if (x == null || y == null || r == null) {
            throw new IllegalArgumentException(message("validation.coordinates.empty"));
        }
        if (x < -5 || x > 3) {
            throw new IllegalArgumentException(message("validation.x.invalid"));
        }
        if (y < -5.0 || y > 5.0) {
            throw new IllegalArgumentException(message("validation.y.invalid"));
        }
        if (r < 1 || r > 5) {
            throw new IllegalArgumentException(message("validation.r.invalid"));
        }
    }

    private String message(String key) {
        return MESSAGES.getString(key);
    }
}
