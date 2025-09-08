package Utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

public class DateUtils {

    private static final List<String> INPUT_PATTERNS = Arrays.asList(
            "dd/MM/yyyy",
            "dd-MM-yyyy",
            "dd.MM.yyyy",
            "dd--MM--yyyy"
    );

    private static final String OUTPUT_PATTERN = "dd/MM/yyyy";

    public static LocalDate normalizeDate(String dateStr) {
        for (String pattern : INPUT_PATTERNS) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
                return LocalDate.parse(dateStr, formatter);
            } catch (DateTimeParseException ignored) {
            }
        }
        throw new IllegalArgumentException("Ngày không hợp lệ: " + dateStr);
    }

    public static String toString(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern(OUTPUT_PATTERN));
    }

    public static String toString(LocalDate date, String pattern) {
        return date.format(DateTimeFormatter.ofPattern(pattern));
    }
}
