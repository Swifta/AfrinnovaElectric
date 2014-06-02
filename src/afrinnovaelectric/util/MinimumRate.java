/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package afrinnovaelectric.util;

import com.afrinnova.api.schoolfees.service.model.AccountLookup;
import java.sql.SQLException;

/**
 *
 * @author princeyekaso
 */
public class MinimumRate {

    public boolean isBelowMinimumRate(double amount) throws SQLException {
        Double minimumAmount = AccountLookup.getInstance().getCurrentMinimumRate();
        boolean status = (amount < minimumAmount) ? true : false;
        return status;
    }
}
