/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package afrinnovaelectric.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;

/**
 *
 * @author princeyekaso
 */
public class NumberUtilities {

    public String format(Double amount) {
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        DecimalFormatSymbols decimalFormatSymbols = ((DecimalFormat) nf).getDecimalFormatSymbols();
        decimalFormatSymbols.setCurrencySymbol("");
        ((DecimalFormat) nf).setDecimalFormatSymbols(decimalFormatSymbols);
        String formattedString = nf.format(amount);
        return formattedString;
    }

    public String formatWithNigerianCurrency(Double amount) {
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        DecimalFormatSymbols decimalFormatSymbols = ((DecimalFormat) nf).getDecimalFormatSymbols();
        decimalFormatSymbols.setCurrencySymbol("\u20A6");
        ((DecimalFormat) nf).setDecimalFormatSymbols(decimalFormatSymbols);
        String formattedString = nf.format(amount);
         return formattedString;
    }
}
