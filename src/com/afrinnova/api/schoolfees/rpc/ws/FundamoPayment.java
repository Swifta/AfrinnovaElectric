/**
 *
 */
package com.afrinnova.api.schoolfees.rpc.ws;

import afrinnovaelectric.Constants;
import afrinnovaelectric.IpayMsg;
import afrinnovaelectric.StdToken;
import afrinnovaelectric.VendRes;
import afrinnovaelectric.VendRevRes;
import afrinnovaelectric.util.MinimumRate;
import afrinnovaelectric.util.NumberUtilities;
import afrinnovaelectric.util.SendSubscriberToken;
import com.afrinnova.api.schoolfees.authentication.ServerAuthentication;
import com.afrinnova.api.schoolfees.face.IFundamoPayment;
import com.afrinnova.api.schoolfees.properties.AccountProperties;
import com.afrinnova.api.schoolfees.response.GeneralResponse;
import com.afrinnova.api.schoolfees.response.ResponseCode;
import com.afrinnova.api.schoolfees.response.TransactionResponse;
import com.afrinnova.api.schoolfees.service.exception.ServiceException;
import com.afrinnova.api.schoolfees.service.model.AccountLookup;
import com.afrinnova.api.schoolfees.service.model.TransactionOb;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FundamoPayment implements IFundamoPayment {

    private static final Logger logger = Logger.getLogger("com.afrinnova.api.schoolfees.rpc.ws.FundamoPayment");
    public String Statuscode = "01", extCode = "";
    GeneralResponse response = new GeneralResponse();
    public int seqCount = 0;
    private Constants constant = new Constants();
    private NumberUtilities numberUtilities = new NumberUtilities();

    @Override
    public TransactionResponse receivePaymentConfirmation(String payerAccountIdentifier, String customerName, String accountRef, double amount, String paymentRef, String transactionType,
            String fundamoTransactionID, String thirdPartyTransactionID, String fundamoUserId, String fundamoPassword,
            String statusCode, String appVersion) {


        try {

            logger.info("Inside receivePaymentConfirmation ");
            StringBuilder objStringBuffer = new StringBuilder();
            objStringBuffer.append("\n receivePaymentConfirmation Methods Parameters : [ ");
            objStringBuffer.append(" accountIdentifier : ").append(payerAccountIdentifier);
            objStringBuffer.append(" , customerName : ").append(customerName);
            objStringBuffer.append(" , accountRef : ").append(accountRef);
            objStringBuffer.append(" , amount : ").append(amount);
            objStringBuffer.append(" , paymentRef : ").append(paymentRef);
            objStringBuffer.append(" , transactiontype : ").append(transactionType);
            objStringBuffer.append(" , fundamoTransactionID : ").append(fundamoTransactionID);
            objStringBuffer.append(" , thirdPartyTransactionID : ").append(thirdPartyTransactionID);
            objStringBuffer.append(" , fundamoUserId : ").append(fundamoUserId);
            objStringBuffer.append(" , fundamoPassword : ").append(fundamoPassword);
            objStringBuffer.append(" , statusCode : ").append(statusCode);
            objStringBuffer.append(" , appVersion : ").append(appVersion);
            objStringBuffer.append(" ]\n");

            if (statusCode == null || !Statuscode.equals(statusCode)) {

                response.setResponseMessage("Status code returned not 1");
                throw new ServiceException(ResponseCode.CODE_03_TRAN_NOT_ALLOWED, "Request StatusCode not successful");
            }
            MinimumRate minRate = new MinimumRate();
            if (minRate.isBelowMinimumRate(amount)) {
                response.setResponseMessage("Payment is below minimum.");
                throw new ServiceException(ResponseCode.CODE_04_PAYMENT_BELOW_MIN, "Payment is below minimum");
            }

            logger.info(objStringBuffer.toString());
            /* Look up if the Fundamo credentials are correct */
            if (!ServerAuthentication.isLoginDetailsValid(fundamoUserId, fundamoPassword)) {
                logger.info("Mobile Mobile call::::::Received Userid/Username not correct");
                throw new ServiceException(ResponseCode.CODE_05_INVALID_FUNDAMO_DETAILS, "Invalid authentication");
            }


            logger.log(Level.INFO, "Fetching customer details for Meter number ==" + accountRef);

            AccountLookup look = AccountLookup.getInstance();
            Boolean meterStatus = look.confirmCustomerDetails(accountRef);
            if (meterStatus == null) {
                logger.log(Level.INFO, "customer with meter number has a pending transaction", accountRef);

                throw new ServiceException(ResponseCode.CODE_103_DUPL_TRAN_ID, "Customer with " + accountRef + " has a pending vend request and cannot complete further requests");
            } else if (!meterStatus) {

                logger.log(Level.INFO, "customer with meter number cannot be retrieved", accountRef);

                throw new ServiceException(ResponseCode.CODE_02_INVALID_ACC_DETAILS, "Customer with " + accountRef + " does not exist");
            }
            seqCount++;
            /* Look up account */

            //AccountLookup look1 = new AccountLookup();
            TransactionOb transactionOb = new TransactionOb();
            transactionOb.setAcctref(accountRef);
            transactionOb.setAmount(Double.valueOf(amount).longValue());
            transactionOb.setCustomerName(customerName);
            transactionOb.setFundamoTransactionID(fundamoTransactionID);
            transactionOb.setPayerAccountIdentifier(payerAccountIdentifier);
            transactionOb.setPaymentRef(paymentRef);
            transactionOb.setStatuscode(statusCode);

            IpayMsg ipay = look.makePayments(transactionOb, seqCount);
            String statusMessage = "Successful\nToken(s) ";
            String responseCode = "";
            logger.info("returned ipay after payments =====================================\n");
            if (ipay == null) {
                statusMessage = "Unsuccessful and reversed!!!";
            } else {
                SendSubscriberToken sendSubToken = new SendSubscriberToken();
                if (ipay != null) {
                    if (ipay.getElecMsg() != null) {
                        logger.info("ElecMSG is not null......");
                        VendRes vendRes = ipay.getElecMsg().getVendRes();
                        if (vendRes != null) {
                            logger.info("VendRes is not null........");
                            responseCode = vendRes.getRes().getCode();
                            extCode = vendRes.getRes().getExtCode();
                            if (responseCode.equalsIgnoreCase(constant.STATUS_OK)) {
                                logger.info("Response code is OK..");
                                List<StdToken> stdTokens = vendRes.getStdToken();
                                if (stdTokens != null) {
                                    StdToken stdToken = stdTokens.iterator().next();
                                    if (stdToken != null) {
                                        logger.info("TOKEN is not NULL.......");
                                        statusMessage += ":" + stdToken.getValue();
                                        look.insertSmsLog(vendRes.getRef(), stdToken.getValue(), payerAccountIdentifier, sendSubToken.sendToken(numberUtilities.format(amount / 100), stdToken.getValue(), fundamoTransactionID, payerAccountIdentifier));
                                        look.updateTransactionHistory(vendRes.getRef(), constant.TXN_COMPLETE);
                                    }
                                } else {
                                    logger.info("Token is NULL==========");
                                }

                            } else {
                                logger.info("The Response code is " + responseCode);
                                statusMessage = retrieveResponseDescription(responseCode);
                                look.updateTransactionHistory(vendRes.getRef(), constant.TXN_FAILED);
                            }
                        } else {
                            logger.info("Checking if its a vend reversal........");
                            VendRevRes vendRevRes = ipay.getElecMsg().getVendRevRes();
                            if (vendRevRes != null) {
                                vendRes = vendRevRes.getVendRes();
                                responseCode = vendRevRes.getRes().getCode();
                                extCode = vendRevRes.getRes().getExtCode();
                                if (responseCode.equalsIgnoreCase(constant.STATUS_ESKOMO)) {
                                    statusMessage = "Reversal not supported.";
                                    List<StdToken> stdTokens = vendRes.getStdToken();
                                    if (stdTokens != null) {
                                        StdToken stdToken = stdTokens.iterator().next();
                                        if (stdToken != null) {
                                            statusMessage += ":" + stdToken.getValue();
                                            look.insertSmsLog(vendRevRes.getRef(), stdToken.getValue(), payerAccountIdentifier, sendSubToken.sendToken(numberUtilities.format(amount / 100), stdToken.getValue(), fundamoTransactionID, payerAccountIdentifier));
                                        }

                                    }

                                } else if (responseCode.equalsIgnoreCase(constant.STATUS_OK)) {
                                    statusMessage = "Unsuccessful and reversed!!!";
                                } else {
                                    statusMessage = retrieveResponseDescription(responseCode);
                                }
                            }
                        }
                    } else {
                        logger.info("ELECMSG is null....after confirmation");
                    }
                } else {
                    logger.info("IPAY is null.....after confirmation");
                }
            }


            logger.info("Mobile Mobile call::::::Payment Added Successfully");




            return new TransactionResponse(thirdPartyTransactionID, fundamoTransactionID, retrieveFundamoResponseCode(responseCode), statusMessage, AccountProperties.APP_VERSION, null);


        } catch (ServiceException se) {
            logger.log(Level.INFO, "ServiceClass Exception::::Transaction not processed due to {0}", se.getResponseMessage());
            return new TransactionResponse(thirdPartyTransactionID, fundamoTransactionID, se.getResponseCode(), se.getResponseMessage(), AccountProperties.APP_VERSION, null);
        } catch (Exception e) {
            logger.log(Level.INFO, "Exception:::::Transaction not processed due to {0}", e.getMessage());
            e.printStackTrace();
            return new TransactionResponse(null, fundamoTransactionID, ResponseCode.CODE_100_GENERAL_FAILURE, "null", AccountProperties.APP_VERSION, null);
        }

    }

    public String retrieveFundamoResponseCode(String responseCode) {

        responseCode = responseCode.trim();

        if (responseCode.equalsIgnoreCase("elec000")) {
            return ResponseCode.CODE_01_OK;
        } else if (responseCode.equalsIgnoreCase("elec001")) {
            if (extCode != null && extCode.equalsIgnoreCase("10071")) {
                return ResponseCode.CODE_04_PAYMENT_BELOW_MIN;
            } else {
                return ResponseCode.CODE_100_GENERAL_FAILURE;
            }
        } else {
            return ResponseCode.CODE_100_GENERAL_FAILURE;
        }
    }

    public String retrieveResponseDescription(String responseCode) {

        responseCode = responseCode.trim();

        if (responseCode.equalsIgnoreCase("elec001")) {
            return "General Error";
        } else if (responseCode.equalsIgnoreCase("elec002")) {
            return "Service not available";
        } else if (responseCode.equalsIgnoreCase("elec003")) {
            return "No record of previous transaction";
        } else if (responseCode.equalsIgnoreCase("elec004")) {
            return "Warning - reversals are not supported by the suppliers system";
        } else if (responseCode.equalsIgnoreCase("elec010")) {
            return "Unknown meter number";
        } else if (responseCode.equalsIgnoreCase("elec011")) {
            return "Meter is block";
        } else if (responseCode.equalsIgnoreCase("elec012")) {
            return "Too much debt";
        } else if (responseCode.equalsIgnoreCase("elec013")) {
            return "Invalid Amount";
        } else if (responseCode.equalsIgnoreCase("elec014")) {
            return "Invalid Number of Tokens";
        } else if (responseCode.equalsIgnoreCase("elec015")) {
            return "Amount too high";
        } else if (responseCode.equalsIgnoreCase("elec016")) {
            return "Amount too low";
        } else if (responseCode.equalsIgnoreCase("elec017")) {
            return "No free BSST token is due";
        } else if (responseCode.equalsIgnoreCase("elec018")) {
            return "Multiple tokens not supported";
        } else if (responseCode.equalsIgnoreCase("elec019")) {
            return "Already reversed";
        } else if (responseCode.equalsIgnoreCase("elec020")) {
            return "Transaction already completed";
        } else if (responseCode.equalsIgnoreCase("elec021")) {
            return "Duplicate meter number";
        } else if (responseCode.equalsIgnoreCase("elec022")) {
            return "Meter is blocked";
        } else if (responseCode.equalsIgnoreCase("elec023")) {
            return "Invalid PaymentType";
        } else if (responseCode.equalsIgnoreCase("elec029")) {
            return "Invalid replace reference";
        } else if (responseCode.equalsIgnoreCase("elec030")) {
            return "Invalid XmlVend Format";
        } else if (responseCode.equalsIgnoreCase("elec031")) {
            return "BSST token must be vended as part of sale";
        } else if (responseCode.equalsIgnoreCase("elec032")) {
            return "Update meter key";
        } else if (responseCode.equalsIgnoreCase("elec033")) {
            return "Track 2 meter mismatch";
        } else if (responseCode.equalsIgnoreCase("elec034")) {
            return "Test vend not supported";
        } else if (responseCode.equalsIgnoreCase("elec900")) {
            return "General system error";
        } else if (responseCode.equalsIgnoreCase("elec901")) {
            return "Unsupported message version numb";
        } else if (responseCode.equalsIgnoreCase("elec902")) {
            return "Invalid Reference";
        } else {
            return "Unknown error";
        }
    }

    /* (non-Javadoc)
     * @see com.fundamo.api.payment.ws.IFundamoPayment#retryPaymentConfirmation(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public TransactionResponse retryPaymentConfirmation(String string, String string1, String string2, String string3, String string4, String string5, String string6, String string7) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
