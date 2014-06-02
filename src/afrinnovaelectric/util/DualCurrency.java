/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package afrinnovaelectric.util;

import com.afrinnova.api.schoolfees.service.model.AccountLookup;
import java.sql.SQLException;

public class DualCurrency {

    public Long convert(Long amount) throws SQLException {
        Double convertedAmount = 0.0;
        Double rate = AccountLookup.getInstance().getCurrentExchangeRate();
        convertedAmount = amount / rate;
        return Long.valueOf(convertedAmount.longValue());
    }
}
