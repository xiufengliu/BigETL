package dk.dtu.ccsd.common;

import java.util.Calendar;
import java.util.Random;
import java.util.TimeZone;

/**
 * Created by xiuli on 2/21/15.
 */
public class Utils {
    protected static Random random = new Random();

    public static long millsecondsSinceEpoch(int year, int month, int date, int hourOfDay) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.clear();
        calendar.set(year, month - 1, date, hourOfDay, 0); // year, month (0-based), date of month, hour of day (0-23), minute
        return calendar.getTimeInMillis();
    }

    public static long secondsSinceEpoch(int year, int month, int date, int hourOfDay) {
        return millsecondsSinceEpoch(year, month, date, hourOfDay);
    }

    public static long hoursSinceEpoch(int year, int month, int date, int hourOfDay) {
        return secondsSinceEpoch(year, month, date, hourOfDay) / 3600L;
    }

    public static double randomInRange(double min, double max) {
        double range = max - min;
        double scaled = random.nextDouble() * range;
        return scaled + min;
    }
}
