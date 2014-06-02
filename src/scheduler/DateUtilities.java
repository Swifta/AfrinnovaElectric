/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduler;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.apache.log4j.Logger;

/**
 *
 * @author princeyekaso
 */
public class DateUtilities {

    private Logger logger = Logger.getLogger(DateUtilities.class);

    public Date formatStringDate(String dateString, String dateFormat, boolean lenient) {
        Date date = null;
        DateFormat df = new SimpleDateFormat(dateFormat);
        df.setLenient(lenient);
        if (dateString != null && !dateString.isEmpty()) {
            try {
                logger.info("-------------inside the method");
                date = df.parse(dateString);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return date;
    }

    public String formatDateToString(Date date, String dateFormat) {
        DateFormat df = new SimpleDateFormat(dateFormat);
        String formattedDate = "";
        if (date != null) {
            formattedDate = df.format(date);
        }
        return formattedDate;
    }

    public String incrementByADay(String dt, String dateFormat) {
        String formattedDate = "";
        DateFormat df = new SimpleDateFormat(dateFormat);
        if (dt != null && !dt.isEmpty()) {
            Calendar c = Calendar.getInstance();


            try {
                c.setTime(df.parse(dt));


            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();


            }
            c.add(Calendar.DATE, 1); // number of days to add

            formattedDate = df.format(c.getTime());
        }
        return formattedDate;

    }
}
