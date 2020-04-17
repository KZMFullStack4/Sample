package Uploading.uploading;

//import com.ghasemkiani.util.icu.PersianDateFormat;
//import ir.tagavar.util.validator.SimpleValidator;
import com.ghasemkiani.util.icu.PersianDateFormat;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

//import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author aghaei
 *
 */
public class DateConverter11 {

    private static final Log logger = LogFactory.getLog(DateConverter11.class);
    public static final String DATE_FORMAT_NO_SLASH = "yyyyMMdd";
    public static final String DATE_TIME_FORMAT_NO_EXTRA_SIGN = "yyyyMMddHHmmss";
    public static final String DATE_TIME_FORMAT_NO_SLASH_NO_SECONDS = "yyyyMMdd hh:mm";
    public static final String DATE_FORMAT = "yyyy/MM/dd";
    public static final String TS_FORMAT = "yyyy/MM/dd HH:mm:ss.S";
    public static final String TIME_FORMAT = "HH:mm:ss";
    public static final String DATE_TIME_FORMAT = "yyyy/MM/dd HH:mm:ss";
    public static final String DATE_TIME_FORMAT_BRIEF = "yy/MM/dd HH:mm";
    public static final String DATE_TIME_FORMAT_NO_SECONDS = "yyyy/MM/dd HH:mm";
    public static final String DATE_DELIMITER = "/";

//    protected Object convertToString(Object value) {
//        if (value instanceof Date) {
//            DateFormat df = new SimpleDateFormat(DATE_FORMAT);
//            if (value instanceof Timestamp) {
//                df = new SimpleDateFormat(TS_FORMAT);
//            }
//            try {
//                return df.format(value);
//            } catch (Exception e) {
//                throw new ConversionException("Error converting Date to String", e);
//            }
//        }
//        return value.toString();
//    }
    public static String dateToShamsiString(Date date, String pattern) {
        PersianDateFormat pdc = new PersianDateFormat(pattern);
        return pdc.format(date);
    }

    public static long diffMilliseconds(Date date1, Date date2) {
        long diff = date2.getTime() - date1.getTime();
        return TimeUnit.MILLISECONDS.convert(diff, TimeUnit.MILLISECONDS);
    }

    /**
     *
     * @param timeValue formatted as HH:mm:ss
     * @return the amount of seconds that input denotes
     */
    public static int calculateSeconds(String timeValue) {
        return Integer.valueOf(timeValue.substring(0, 2)) * 60 * 60
                + Integer.valueOf(timeValue.substring(3, 5)) * 60
                + Integer.valueOf(timeValue.substring(6));
    }

    /**
     *
     * @param input only the time value matters
     * @return the amount of seconds that input denotes
     */
    public static int calculateSeconds(Date input) {
        DateFormat df = new SimpleDateFormat(TIME_FORMAT);
        String formattedTime = df.format(input);
        return calculateSeconds(formattedTime);
    }

    public static String oneDayIncrease(String miladiDate) throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        java.util.Date date = formatter.parse(miladiDate);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = date.getMonth()+1;
        System.out.println("******* Month in miladi : " + month);

        if (month >= 4 && month <= 9) {
            cal.add(Calendar.DATE, 1);
            date= cal.getTime();
            return formatter.format(date);
        } else {
            return formatter.format(date);
        }

    }

    public static Float daysCalculator(String fromDate, String tillDate, String dateFormat) {

        String date1 = fromDate;
        String date2 = tillDate;
        SimpleDateFormat myFormat;
        if (dateFormat == null) {
            myFormat = new SimpleDateFormat("yyyy/MM/dd");
        } else {
            myFormat = new SimpleDateFormat(dateFormat);
        }

        try {

            Date from = myFormat.parse(date1);
            Date till = myFormat.parse(date2);
            long differences = till.getTime() - from.getTime();
            float daysBetween = TimeUnit.DAYS.convert(differences, TimeUnit.MILLISECONDS);
            return daysBetween;
        } catch (Exception ex) {
            System.out.println("\n Date exception occured when executing daysCalculator in date convertor class : \n" + ex);
            return null;
        }
    }

}
