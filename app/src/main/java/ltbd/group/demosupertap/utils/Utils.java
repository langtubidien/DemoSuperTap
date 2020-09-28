package ltbd.group.demosupertap.utils;

import android.content.Context;
import android.icu.util.Calendar;

/**
 * Created by ltbd on 9/28/20.
 */
public class Utils {

    public static String getHelloString() {
        Calendar rightNow = Calendar.getInstance();
        int currentHourIn24Format = rightNow.get(Calendar.HOUR_OF_DAY); // return the hour in 24 hrs format (ranging from 0-23)

        if (currentHourIn24Format > 0 && currentHourIn24Format < 24) {
            return "Good morning";
        } else {
            return "Good after noon";
        }
    }
}
