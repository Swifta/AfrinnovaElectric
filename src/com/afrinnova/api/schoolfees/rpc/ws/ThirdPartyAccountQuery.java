/**
 *
 */
package com.afrinnova.api.schoolfees.rpc.ws;

import com.afrinnova.api.schoolfees.authentication.ServerAuthentication;
import com.afrinnova.api.schoolfees.face.IThirdPartyAccountEnquiry;
import com.afrinnova.api.schoolfees.properties.AccountProperties;
import com.afrinnova.api.schoolfees.response.AccountEnquiryResponse;
import com.afrinnova.api.schoolfees.response.ResponseCode;
import com.afrinnova.api.schoolfees.service.exception.ServiceException;
import com.afrinnova.api.schoolfees.service.model.Account;
import com.afrinnova.api.schoolfees.service.model.Account01;
import com.afrinnova.api.schoolfees.service.model.AccountLookup;
import com.afrinnova.api.schoolfees.service.model.TransactionOb;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThirdPartyAccountQuery implements IThirdPartyAccountEnquiry {

    private static final Logger logger = Logger.getLogger("com.afrinnova.api.schoolfees.rpc.ws.ThirdPartyAccountQuery");

    @Override
    @SuppressWarnings("empty-statement")
    public AccountEnquiryResponse getAccountBalance(String accountRef,
            String messageId, String fundamoUserId, String fundamoPassword,
            String localeLanguage, String localeCountry, String appVersion) {



        try {
            /* Look up if the Fundamo credentials are correct */
            logger.info("Validating credentials to platform");
            if (!ServerAuthentication.isLoginDetailsValid(fundamoUserId, fundamoPassword)) {
                throw new ServiceException(ResponseCode.CODE_05_INVALID_FUNDAMO_DETAILS, "Invalid authentication");
            }

            /* Look up account */
            logger.log(Level.INFO, "Fetching school fees amount for student with Identification number: {0}", accountRef);
            AccountLookup look = AccountLookup.getInstance();

        /*    Account01 thirdPartyAccount = look.getAccountref(accountRef);

            if (thirdPartyAccount == null) {

                logger.log(Level.INFO, "Student with {0} does not exist", accountRef);


                throw new ServiceException(ResponseCode.CODE_02_INVALID_ACC_DETAILS, "Student with " + accountRef + " does not exist");
            }

*/
            //    TransactionOb transbal = look.getPayment(accountRef);

            Account thirdpartyamount = new Account();

            /*    if (transbal != null) {


             thirdpartyamount.setAccountRef(accountRef);
             thirdpartyamount.setAmountDue(0L);
             thirdpartyamount.setBalance(transbal.getAmount().longValue());
             thirdpartyamount.setMinimumDue(thirdPartyAccount.getMinimum_amount());

             } else {
             thirdpartyamount.setAccountRef(accountRef);
             thirdpartyamount.setAmountDue(0L);
             thirdpartyamount.setBalance(0L);
             thirdpartyamount.setMinimumDue(thirdPartyAccount.getMinimum_amount());

             }
             */


            /* Assemble the response with the information from the account */
            AccountEnquiryResponse response = new AccountEnquiryResponse();
            response.setThirdPartyAccountRef(accountRef);
            response.setFundamoTransactionId(messageId);
            response.setResponseCode(ResponseCode.CODE_01_OK);
            response.setResponseMessage("Success");
            response.setAmountDue(thirdpartyamount.getAmountDue() * 100);
            response.setBalance(thirdpartyamount.getBalance() * 100);
            response.setMinimumDue(thirdpartyamount.getMinimumDue() * 100);
            response.setTextMessage("Please pay");
            response.setAppVersion(AccountProperties.APP_VERSION);

            logger.info("Thirdparty Query processed successfully");

            return response;



        } catch (ServiceException se) {

            logger.log(Level.INFO, "service Exception caught {0}", se.getMessage());

            return new AccountEnquiryResponse(se.getResponseCode(), se.getResponseMessage(), AccountProperties.APP_VERSION);
        } catch (Exception e) {

            logger.log(Level.INFO, "Exception caught{0}", e.getMessage());
            e.printStackTrace();;

            return new AccountEnquiryResponse(ResponseCode.CODE_100_GENERAL_FAILURE, "null", AccountProperties.APP_VERSION);
        }

    }
//    public static void main(String args[]) throws AccountDAOException, IOException{
//        
//        AccountLookup loook = new AccountLookup();
//        
//        Account01 thirdPartyAccount = loook.getAccountref("rc0001");
//        
//        if (thirdPartyAccount == null){
//            
//            System.out.println("No value");
//        } else {
//        
//        thirdPartyAccount.print();
//        }
//    }
}
