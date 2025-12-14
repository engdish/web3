package util;

import javax.ejb.Stateless;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Stateless
public class DateTimeProvider {
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss dd.MM.yyyy");

    public String now() {
        return LocalDateTime.now().format(FORMAT);
    }
}
