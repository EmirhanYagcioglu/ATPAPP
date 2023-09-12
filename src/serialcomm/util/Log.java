package serialcomm.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Log {
    static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    public static void d(String category, String data) {
        LocalDateTime now = LocalDateTime.now();
        System.out.printf(dtf.format(now) + "\t%s: %s\n", category, data);
    }
}
