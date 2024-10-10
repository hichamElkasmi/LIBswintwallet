package com.ssw.bnk.swintwallet.jdbc.in;

import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_Address;
import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_Criteria;
import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_Customer;
import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_Data;
import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_Filter;
import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_Header;
import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_HistVoucher;
import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_Identity;
import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_Initiator;
import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_Merchant;
import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_MoneyTransfer;
import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_Request;
import static com.ssw.bnk.swintwallet.jdbc.in.SS_Df.*;
import com.ssw.bnk.swintwallet.jdbc.in.SS_Prc.TRANSACTION_TYPE;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.regex.Pattern; 


/**
 *
 * @author MOKHLISS
 */
public class SS_Chk {
    
    private String codeErreur;
    private String msgErreur;
    
    private final short LEN_NAME_40 = 40;
    private final short LEN_CODE_20 = 20;
    private final short LEN_AMOUNT_20 = 20;
    private final short LEN_PHONE_15 = 15;
    private final short LEN_ADDDATA_15 = 15;
    private final short LEN_SDATE_10 = 10;
    private final short LEN_LDATE_19 = 19;
    private final short LEN_MAC_200 = 200;
    private final short LEN_ID_40 = 40;
    private final short LEN_CTRY_15 = 15;
    private final short LEN_CUR_3 = 3;
    private final short LEN_MAIL_128 = 128;
    private final short LEN_STR_50 = 50;
    private final short LEN_ACC_24 = 24;
    private final short LEN_LST_1 = 1;
    private final short LEN_NBR_2 = 2;
    private final short LEN_TYP_2 = 2;
    private final short LEN_ADR_80 = 80;
    public  static final short LEN_MXTRX = 99;
    private final short LEN_MNTRX = 1;

    
    // EKH ADD OPERA CHECK 
    private final short LEN_OPERA = 10;
    private final short LEN_20 = 20;
    private final short LEN_3 = 3;
    // END EKH
    
    public String code(){
        return codeErreur;
    }
    
    public String msg(){
        return msgErreur;
    }
    public boolean checkDataAuth(SSEnt_Header sSEnt_Header){
        if(sSEnt_Header == null){
            codeErreur = SS_Df.ccode(ENT_HEADER_NULL);
            msgErreur = SS_Df.cmsg(ENT_HEADER_NULL);
            return false;
        }
        else{
        return checkStringEmpty(sSEnt_Header.getMac(), HEAD_MAC_EMPTY) &&
                checkStringLenght(sSEnt_Header.getMac(),LEN_MAC_200,HEAD_MAC_LENGHT) &&
                 checkStringEmpty(sSEnt_Header.getIdmsg(), HEAD_ID_EMPTY) && 
                  checkStringLenght(sSEnt_Header.getIdmsg(), LEN_ID_40, HEAD_ID_LENGHT);
        }
    }
    
    public boolean checkDataUpdated(SSEnt_Data sSEnt_Data){
        if(sSEnt_Data == null){
            codeErreur = SS_Df.ccode(ENT_DATA_NULL);
            msgErreur = SS_Df.cmsg(ENT_DATA_NULL);
            return false;
        }
        else{
        return checkDataEnFirstName(sSEnt_Data.getEnfirstname()) &&
                checkDataEnLastName(sSEnt_Data.getEnlastname()) &&
                 checkDataArFirstName(sSEnt_Data.getArfirstname()) && 
                  checkDataArLastName(sSEnt_Data.getArlastname()) &&
                   checkDataGendre(sSEnt_Data.getGender()) &&
                    checkDataTitel(sSEnt_Data.getTitle()) &&
                     checkDateField(sSEnt_Data.getDateob(), ENRL_DBIRTH, DATE_FORMAT_SMALL, true) &&
                      checkStringLenght(sSEnt_Data.getPlaceob(),LEN_CTRY_15,ENRL_POB_LENGHT) &&
                       checkDataMobile(sSEnt_Data.getMobile()) &&
                        checkDataEMail(sSEnt_Data.getEmail()) && 
                         checkStringLenght(sSEnt_Data.getNationality(),LEN_CTRY_15,ENRL_NAT_LENGHT) &&
                          checkDataAddress(sSEnt_Data.getAddress());
        }

    }
        
    public boolean checkDataData(SSEnt_Data sSEnt_Data){
        if(sSEnt_Data == null){
            codeErreur = SS_Df.ccode(ENT_DATA_NULL);
            msgErreur = SS_Df.cmsg(ENT_DATA_NULL);
            return false;
        }
        else{
            if(checkDataUpdated(sSEnt_Data)){

                return checkDataBank(sSEnt_Data.getBankcode()) &&
                         checkDataBranch(sSEnt_Data.getBranchcode()) &&
                          checkDataNationalID(sSEnt_Data.getNationalid()) && 
                            checkDataAccProgram(sSEnt_Data.getAccountprogramcode()) && 
                             checkDataAccount(sSEnt_Data.getAccountnumber()) &&
                              checkDataCustomer(sSEnt_Data.getCustomercode() ,false) &&
                               checkStringLenght(sSEnt_Data.getPassportnumber(),LEN_CODE_20,ENRL_PSRTID_LENGHT) &&
                                checkDateField(sSEnt_Data.getPassportexpiration(), ENRL_DPSPRT, DATE_FORMAT_SMALL, false);
            }
            else
                return false;
        }
    }
        
    public boolean checkDataCustomer(SSEnt_Customer sSEnt_Customer){
        
        return checkDataData(sSEnt_Customer);

    }
    
    public boolean checkDataMerchant(SSEnt_Merchant sSEnt_Merchant){
         if(checkDataData(sSEnt_Merchant))
         {
           return checkStringLenght(sSEnt_Merchant.getMerchantid(),LEN_CODE_20,ENRL_MERCHANTID_LENGHT) && 
                   checkStringEmpty(sSEnt_Merchant.getMerchantname(), ENRL_MERCHANTNAME_EMPTY) && 
                    checkStringLenght(sSEnt_Merchant.getMerchantname(),LEN_NAME_40,ENRL_MERCHANTNAME_LENGHT) && 
                     checkDataMerProgram(sSEnt_Merchant.getMerprogramcode()) && 
                      checkDataMerActivity(sSEnt_Merchant.getMeractivitycode());
         }
         return false;
    }
    
    public boolean checkDataInitiator(SSEnt_Initiator sSEnt_Initiator, boolean checkWallet){
        if(sSEnt_Initiator == null){
            codeErreur = SS_Df.ccode(ENT_INITIATOR_NULL);
            msgErreur = SS_Df.cmsg(ENT_INITIATOR_NULL);
            return false;
        }
        else{
           return true;  
        }
            
           // if(checkStringEmpty(sSEnt_Initiator.getType(), INIT_TYPE_EMPTY) &&
             // checkStringLenght(sSEnt_Initiator.getType(),LEN_LST_1,INIT_TYPE_VALUE) && 
               // checkStringList(sSEnt_Initiator.getType(),new String[] {"M","C"},INIT_TYPE_VALUE) &&
                //checkStringEmpty(sSEnt_Initiator.getIdentifier(), INIT_IDENT_EMPTY) &&
                  //checkStringLenght(sSEnt_Initiator.getIdentifier(),LEN_CODE_20,INIT_IDENT_LENGHT) &&
                    //checkNumericString(sSEnt_Initiator.getIdentifier(),INIT_IDENT_VALUE)){

          //     if("C".equals(sSEnt_Initiator.getType()) && checkWallet){
            //       return checkStringEmpty(sSEnt_Initiator.getCardcode(), INIT_WALLET_CODE_EMPTY) &&
              //             checkStringLenght(sSEnt_Initiator.getCardcode(),LEN_CODE_20,INIT_WALLET_CODE_EMPTY) &&
                //             checkNumericString(sSEnt_Initiator.getCardcode(),INIT_WALLET_CODE_VALUE);
              // }
               //else{
                 // return true;
                //}
          // }
           // else
              // return false;
       // }
    }
    
    // EKH ADD NEW CHECK FOR NEW SERVICE
    public boolean checkDataMoneyTransfer(SSEnt_MoneyTransfer sSEnt_MoneyTransfer, boolean checkWallet){
        if(sSEnt_MoneyTransfer == null){
            codeErreur = SS_Df.ccode(ENT_MONEYTRANSFER_NULL);
            msgErreur = SS_Df.cmsg(ENT_MONEYTRANSFER_NULL);
            return false;
        }
        else{
            if(
              checkStringEmpty(sSEnt_MoneyTransfer.getTransactiondate(), MONEY_DATE_EMPTY) &&
              checkDateField(sSEnt_MoneyTransfer.getTransactiondate(), TRANS_DATE, DATE_FORMAT_SMALL, false) &&
            
              checkStringEmpty(sSEnt_MoneyTransfer.getTerminaltype(), MONEY_TERM_TYP_EMPTY) &&
              checkStringList(sSEnt_MoneyTransfer.getTerminaltype(),new String[] {"TC","TM"},MONEY_TERM_VALUES) &&
              
              checkStringEmpty(sSEnt_MoneyTransfer.getTerminalcode(), MONEY_TERM_COD_EMPTY) &&
              checkStringLenght(sSEnt_MoneyTransfer.getTerminalcode(),LEN_20,MONEY_TREM_LEN) &&
                    
              checkStringEmpty(sSEnt_MoneyTransfer.getEntitycodefrom(), MONEY_ENTI_COD_FROM_EMPTY) &&
              checkStringLenght(sSEnt_MoneyTransfer.getEntitycodefrom(),LEN_20,INIT_IDENT_LENGHT) &&                    
                    
              checkStringEmpty(sSEnt_MoneyTransfer.getEntitytypefrom(), ENTIT_TYPE_FROM_EMPTY) &&
              checkStringList(sSEnt_MoneyTransfer.getEntitytypefrom(),new String[] {"WALLET","CARD", "BANK ACCOUNT"},MONEY_ENT_TYP_FROM_VAL) &&
              
                    
              checkStringEmpty(sSEnt_MoneyTransfer.getEntitycodeto(), MONEY_ENTI_COD_TO_EMPTY) &&
              checkStringLenght(sSEnt_MoneyTransfer.getEntitycodeto(),LEN_20,INIT_IDENT_LENGHT) &&                    
                    
              checkStringEmpty(sSEnt_MoneyTransfer.getEntitytypeto(), ENTIT_TYPE_TO_EMPTY) &&
              checkStringList(sSEnt_MoneyTransfer.getEntitytypeto(),new String[] {"WALLET","CARD", "BANK ACCOUNT"},MONEY_ENT_TYP_TO_VAL) &&
                    
              checkStringEmpty(sSEnt_MoneyTransfer.getAmount(), MONEY_AMOUNT_EMPTY) &&
              checkNumericString(sSEnt_MoneyTransfer.getAmount(),MONEY_NOT_NUMIRIC) &&
               
              checkStringEmpty(sSEnt_MoneyTransfer.getCurrencycode(), MONEY_CURR_EMPTY) &&
              checkStringLenght(sSEnt_MoneyTransfer.getCurrencycode(),LEN_3,MONEY_CURR_LEN)                     
              )
            {
                return true;
            }
            else
                return false;
        }
    }
    
       
    public boolean checkDataIdentity(SSEnt_Identity sSEnt_Identity){
        if(sSEnt_Identity == null){
            codeErreur = SS_Df.ccode(ENT_IDENTITY_NULL);
            msgErreur = SS_Df.cmsg(ENT_IDENTITY_NULL);
            return false;
        }
        else {
        return checkStringEmpty(sSEnt_Identity.getCustomercode(), INIT_IDENT_EMPTY) &&
                checkStringLenght(sSEnt_Identity.getCustomercode(),LEN_CODE_20,INIT_IDENT_LENGHT) &&
                 checkNumericString(sSEnt_Identity.getCustomercode(),INIT_IDENT_VALUE) &&
                  checkStringEmpty(sSEnt_Identity.getWalletcode(), INIT_WALLET_CODE_EMPTY) &&
                   checkStringLenght(sSEnt_Identity.getWalletcode(),LEN_CODE_20,INIT_WALLET_CODE_EMPTY) &&
                    checkNumericString(sSEnt_Identity.getWalletcode(),INIT_WALLET_CODE_VALUE);
        }

    }
    
    public boolean checkDataCriteria(SSEnt_Criteria sSEnt_Criteria){
        if(sSEnt_Criteria == null){
            codeErreur = SS_Df.ccode(ENT_CRITERIA_NULL);
            msgErreur = SS_Df.cmsg(ENT_CRITERIA_NULL);
            return false;
        }
        else{
        return checkDataBank(sSEnt_Criteria.getBankcode()) && 
                checkDataNationalID(sSEnt_Criteria.getNationalid());
        }
    }
    
    public boolean checkDataFilter(SSEnt_Filter sSEnt_Filter){
        
        if(sSEnt_Filter == null){
            codeErreur = SS_Df.ccode(ENT_FILTER_NULL);
            msgErreur = SS_Df.cmsg(ENT_FILTER_NULL);
            return false;
        }
        else{
            if(isFilled(sSEnt_Filter.getIdtrans())){
                return checkStringLenght(sSEnt_Filter.getIdtrans(),LEN_ID_40,FILTER_IDTRANS_LENGHT);
            } else{  
            if(isFilled(sSEnt_Filter.getNtrans())){

                if(!(checkNumericString(sSEnt_Filter.getNtrans(), FILTER_NTRANS_VALUE) &&
                      checkStringLenght(sSEnt_Filter.getNtrans(),LEN_NBR_2,FILTER_NTRANS_LENGHT)))
                    return false;  
                if(Long.valueOf(sSEnt_Filter.getNtrans())<LEN_MNTRX || 
                        Long.valueOf(sSEnt_Filter.getNtrans())>LEN_MXTRX){
                    codeErreur = SS_Df.ccode(FILTER_NTRANS_LENGHT);
                    msgErreur = SS_Df.cmsg(FILTER_NTRANS_LENGHT);
                     return false;
                }
            }  

            return checkDateField(sSEnt_Filter.getStartdate(), FILTER_DSTART_FIELD, DATE_FORMAT_SMALL, false) &&
                    checkDateField(sSEnt_Filter.getStartdate(), FILTER_DEND_FIELD, DATE_FORMAT_SMALL, false);
            }
        }
    }
    
        // END EKH ADD FILTER CHECK
        public boolean checkDataFilterHistVoucher(SSEnt_HistVoucher sSEnt_Filter){
        
        if(sSEnt_Filter == null){
            codeErreur = SS_Df.ccode(ENT_FILTER_NULL);
            msgErreur = SS_Df.cmsg(ENT_FILTER_NULL);
            return false;
        }
        else{
            if(isFilled(sSEnt_Filter.getIdtrans())){
                return checkStringLenght(sSEnt_Filter.getIdtrans(),LEN_ID_40,FILTER_IDTRANS_LENGHT);
            } else{  
            if(isFilled(sSEnt_Filter.getNtrans())){

                if(!(checkNumericString(sSEnt_Filter.getNtrans(), FILTER_NTRANS_VALUE) &&
                      checkStringLenght(sSEnt_Filter.getNtrans(),LEN_NBR_2,FILTER_NTRANS_LENGHT)))
                    return false;  
                if(Long.valueOf(sSEnt_Filter.getNtrans())<LEN_MNTRX || 
                        Long.valueOf(sSEnt_Filter.getNtrans())>LEN_MXTRX){
                    codeErreur = SS_Df.ccode(FILTER_NTRANS_LENGHT);
                    msgErreur = SS_Df.cmsg(FILTER_NTRANS_LENGHT);
                     return false;
                }
            }  

            return checkDateField(sSEnt_Filter.getStartdate(), FILTER_DSTART_FIELD, DATE_FORMAT_SMALL, false) &&
                    checkDateField(sSEnt_Filter.getStartdate(), FILTER_DEND_FIELD, DATE_FORMAT_SMALL, false);
            }
        }
    }
    // END EKH ADD FILTER CHECK
    public boolean checkDataRequest(SSEnt_Request sSEnt_Request, TRANSACTION_TYPE transaction_type){
        
        if(sSEnt_Request == null){
            codeErreur = SS_Df.ccode(ENT_REQUEST_NULL);
            msgErreur = SS_Df.cmsg(ENT_REQUEST_NULL);
            return false;
        }
        else{
            if(transaction_type != TRANSACTION_TYPE.ETOPUP)
            {
                if(!(checkStringEmpty(sSEnt_Request.getAddid(), REQ_ADDID_EMPTY) &&
                      checkStringLenght(sSEnt_Request.getAddid(),LEN_CODE_20,REQ_ADDID_LENGHT) &&
                       checkNumericString(sSEnt_Request.getAddid(), REQ_ADDID_VALUE)))
                    return false; 
            }

            if(transaction_type == TRANSACTION_TYPE.VOUCHER)
            {
                if(!(checkStringEmpty(sSEnt_Request.getAdddata(), REQ_ADDDATA_EMPTY) &&
                      checkStringLenght(sSEnt_Request.getAdddata(),LEN_ADDDATA_15,REQ_ADDDATA_LENGHT) &&
                       checkNumericString(sSEnt_Request.getAdddata(), REQ_ADDID_VALUE)))
                    return false; 
            }
            
            if(transaction_type == TRANSACTION_TYPE.BILLPAYMENT)
            {
                if(!(checkStringEmpty(sSEnt_Request.getAdddata(), REQ_ADDDATA_EMPTY) &&
                      checkStringLenght(sSEnt_Request.getAdddata(),LEN_ADDDATA_15,REQ_ADDDATA_LENGHT)))
                    return false; 

            }
 
            if(transaction_type != TRANSACTION_TYPE.VOUCHER && transaction_type != TRANSACTION_TYPE.VOUCHERSS )
            {
                System.out.println("Service is " + transaction_type);
                if(!(checkStringEmpty(sSEnt_Request.getAmount(), REQ_AMOUNT_EMPTY) &&
                     checkStringLenght(sSEnt_Request.getAmount(),LEN_AMOUNT_20,REQ_AMOUNT_LENGHT) &&
                      checkNumericString(sSEnt_Request.getAmount(), REQ_AMOUNT_VALUE)))
                    return false; 
            }           
            return checkDateField(sSEnt_Request.getDate(), REQ_DATE, DATE_FORMAT_LONG, false) &&
                       checkStringEmpty(sSEnt_Request.getCurrency(), REQ_CURRENCY_EMPTY) &&
                        checkStringLenght(sSEnt_Request.getCurrency(),LEN_CUR_3,REQ_CURRENCY_LENGHT) &&
                         checkStringLenght(sSEnt_Request.getLabel(),LEN_STR_50,REQ_LABEL_LENGHT);
        }
    }
    
    public boolean checkDataEnFirstName(String enFirstName) {
       
        return checkStringEmpty(enFirstName, ENRL_EN_FSTNAME_EMPTY) && 
                checkStringLenght(enFirstName,LEN_NAME_40,ENRL_EN_FSTNAME_LENGHT);
    }
    
    public boolean checkDataEnLastName(String enLastName) {
        
        return checkStringEmpty(enLastName, ENRL_EN_LSTNAME_EMPTY) && 
                checkStringLenght(enLastName,LEN_NAME_40,ENRL_EN_LSTNAME_LENGHT);
    }
    
    public boolean checkDataArFirstName(String arFirstName) {
        
        return checkStringLenght(arFirstName,LEN_NAME_40,ENRL_AR_FSTNAME_LENGHT);
    }
    
    public boolean checkDataArLastName(String arLastName) {
        
        return checkStringLenght(arLastName,LEN_NAME_40,ENRL_AR_LSTNAME_LENGHT);
    }
    
      
    public boolean checkDataBank(String bankCode) {
        
        return checkStringEmpty(bankCode, BANK_EMPTY) && 
                checkStringLenght(bankCode,LEN_CODE_20,BANK_LENGHT) && 
                  checkNumericString(bankCode, BANK_VALUE);
    }
    
    public boolean checkDataBranch(String branchCode) {
        
        return checkStringEmpty(branchCode, BRANCH_EMPTY) && 
                checkStringLenght(branchCode,LEN_CODE_20,BRANCH_LENGHT) &&
                    checkNumericString(branchCode,BRANCH_VALUE);
    }
    
    public boolean checkDataMobile(String mobileNbr) {
        
        return checkStringEmpty(mobileNbr, PHONE_EMPTY) &&
                checkStringLenght(mobileNbr, LEN_PHONE_15,PHONE_LENGHT) &&
                checkNumericPhoneString(mobileNbr, PHONE_VALUE);
    }
    
    public boolean checkDataNationalID(String nationalId) {
        
        return checkStringEmpty(nationalId, NATID_EMPTY) && 
                checkStringLenght(nationalId,LEN_CODE_20,NATID_LENGHT);
    }
    
    public boolean checkDataGendre(String gendre) {
        
        return checkStringLenght(gendre,LEN_LST_1,ENRL_GENDER_VALUE) &&
                checkStringList(gendre,new String[] {"M","F"},ENRL_GENDER_VALUE);
    }
    
        
    public boolean checkDataTitel(String titel) {
        
        return checkStringLenght(titel,LEN_LST_1,ENRL_TITEL_VALUE) &&
                checkStringList(titel,new String[] {"1","2","3"},ENRL_TITEL_VALUE);
    }
    
    public boolean checkDataAccount(String accountNbr) {
        
        return checkStringEmpty(accountNbr, ACCOUNT_EMPTY) &&
                checkStringLenght(accountNbr,LEN_ACC_24,ACCOUNT_LENGHT) &&
                 checkNumericString(accountNbr, ACCOUNT_VALUE);
    }
    
    public boolean checkDataAccProgram(String accPrgCode) {
        
        return checkStringEmpty(accPrgCode, ACC_PROGRAM_EMPTY) &&
                checkStringLenght(accPrgCode,LEN_CODE_20,ACC_PROGRAM_LENGHT) &&
                 checkNumericString(accPrgCode, ACC_PROGRAM_VALUE);
    }
      
    public boolean checkDataMerActivity(String merActCode) {
        
        return checkStringEmpty(merActCode, MER_ACTIVITY_EMPTY) &&
                checkStringLenght(merActCode,LEN_CODE_20,MER_ACTIVITY_LENGHT) &&
                 checkNumericString(merActCode, MER_ACTIVITY_VALUE);
    }
    
    public boolean checkDataMerProgram(String merPrgCode) {
        
        return checkStringEmpty(merPrgCode, MER_PROGRAM_EMPTY) &&
                checkStringLenght(merPrgCode,LEN_CODE_20,MER_PROGRAM_LENGHT) &&
                 checkNumericString(merPrgCode, MER_PROGRAM_VALUE);
    }
    
    public boolean checkDataEMail(String mailAdr) {
        
        if(isFilled(mailAdr))
        {
            return checkStringLenght(mailAdr,LEN_MAIL_128,ENRL_MAIL_LENGHT) &&
                    checkMailString(mailAdr,ENRL_MAIL_VALUE);
        }
         return true;
    }
    
    
    public boolean checkDataAddzipd(String addzipd) {
        
        if(isFilled(addzipd))
        {
            return checkStringLenght(addzipd,5,ENRL_ADRSPD_LENGHT) &&
                    checkNumericString(addzipd,ENRL_ADRSPD_VALUE);
        }
         return true;
    }
    
    
    public boolean checkDataCitycode(String citycode) {
        
        if(isFilled(citycode))
        {
            return checkStringLenght(citycode,10,ENRL_ADRCTY_LENGHT) &&
                    checkNumericString(citycode,ENRL_ADRCTY_VALUE);
        }
         return true;
    }
    
    
    public boolean checkDataPostalcode(String postalcode) {
        
        if(isFilled(postalcode))
        {
            return checkStringLenght(postalcode,20,ENRL_ADRPSTL_LENGHT) &&
                    checkNumericString(postalcode,ENRL_ADRPSTL_VALUE);
        }
         return true;
    }
    
    
    public boolean checkDataCountry(String country) {
        
        if(isFilled(country))
        {
            return checkStringLenght(country,10,ENRL_ADRCTR_LENGHT) &&
                    checkNumericString(country,ENRL_ADRCTR_VALUE);
        }
         return true;
    }
    
     
    public boolean checkStringLenght(String str, int len, int idMsg) {
        try{
            if(str.length()>len){
                codeErreur = SS_Df.ccode(idMsg);
                msgErreur = SS_Df.cmsg(idMsg);
                 return false;
            }
        }
        catch(Exception e){
            codeErreur = SS_Df.ccode(idMsg);
            msgErreur = SS_Df.cmsg(idMsg);
             return false;
        }
        return true;
    }
    
    public boolean checkStringFixLenght(String str, int len, int idMsg) {
        try{
            if(str.length()!=len){
                codeErreur = SS_Df.ccode(idMsg);
                msgErreur = SS_Df.cmsg(idMsg);
                 return false;
            }
        }
        catch(Exception e){
            codeErreur = SS_Df.ccode(idMsg);
            msgErreur = SS_Df.cmsg(idMsg);
             return false;
        }
        return true;
    }
    
    public boolean checkStringEmpty(String str, int idMsg) {
    
        if(!isFilled(str)){
            codeErreur = SS_Df.ccode(idMsg);
            msgErreur = SS_Df.cmsg(idMsg);
             return false;
        }
        return true;
    }
    
    public boolean checkStringList(String str, String[] strList, int idMsg) {
        boolean isIn =false;
        
        for (String strList1 : strList) {
            if (str.equalsIgnoreCase(strList1)) {
                isIn = true;
                break;
            }
        }

        if(!isIn){
            codeErreur = SS_Df.ccode(idMsg);
            msgErreur = SS_Df.cmsg(idMsg);
             return false;
        }
        return true;
    }
    
    private boolean checkNumericString(String str, int idMsg) {
        boolean isNumeric =true;
        
        try{
            if (str == null)
                isNumeric =false;
            else{
                for (char c : str.toCharArray())
                    if (c < '0' || c > '9')
                        isNumeric =false;
            }
            if(!isNumeric){
                codeErreur = SS_Df.ccode(idMsg);
                msgErreur = SS_Df.cmsg(idMsg);
                 return false;
            }
        }
        catch(Exception e){
            codeErreur = SS_Df.ccode(idMsg);
            msgErreur = SS_Df.cmsg(idMsg);
             return false;
        }

        return true;
    }
    
    private boolean checkNumericPhoneString(String str, int idMsg) {
        boolean isNumeric =true;
        
        try{
            if (str == null)
                isNumeric =false;
            else{
                for (char c : str.toCharArray())
                    if (c < '0' || c > '9')
                        isNumeric =false;
            }
            if(!isNumeric){
                codeErreur = SS_Df.ccode(idMsg);
                msgErreur = SS_Df.cmsg(idMsg);
                 return false;
            }
            else{
                String data = str.substring(0, 3);
                if(!data.equalsIgnoreCase("218")){
                    codeErreur = SS_Df.ccode(idMsg);
                    msgErreur = SS_Df.cmsg(idMsg);
                     return false;
                }
            }
        }
        catch(Exception e){
            codeErreur = SS_Df.ccode(idMsg);
            msgErreur = SS_Df.cmsg(idMsg);
             return false;
        }

        return true;
    }
    
    private boolean checkMailString(String email, int idMsg) {
        boolean isMail;
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                                "[a-zA-Z0-9_+&*-]+)*@" + 
                                "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                                "A-Z]{2,7}$"; 
        try{                      
            Pattern pat = Pattern.compile(emailRegex); 

            if (email == null) 
                isMail = false; 
            else
                isMail = pat.matcher(email).matches(); 

            if(!isMail){
                codeErreur = SS_Df.ccode(idMsg);
                msgErreur = SS_Df.cmsg(idMsg);
                 return false;
            }
        }
        catch(Exception e){
            codeErreur = SS_Df.ccode(idMsg);
            msgErreur = SS_Df.cmsg(idMsg);
             return false;
        }
        return true;
    }
    
    
    public boolean checkDataCustomer(String sCusCode, boolean chkEmpty) {
       
        if(isFilled(sCusCode)){
            if(!(checkStringLenght(sCusCode,LEN_CODE_20,CUSTOMER_LENGHT) && 
                  checkNumericString(sCusCode, CUSTOMER_VALUE)))
                return false;
        }else{
             if(chkEmpty){
                codeErreur = SS_Df.ccode(CUSTOMER_EMPTY);
                msgErreur =  SS_Df.cmsg(CUSTOMER_EMPTY);
                return false;
            }
        }     
        return true;
    }
    
    public boolean checkDateField(String sDate, int sIdMsg, int sIdFormat, boolean chkEmpty) {
       
        String dateFormat;
        String dateField;
        int iLen;
        
        if(sIdFormat == DATE_FORMAT_SMALL)
            iLen = LEN_SDATE_10;
        else
            iLen = LEN_LDATE_19;
        
        dateFormat = SS_Df.cmsg(sIdFormat);
        dateField  = SS_Df.cmsg(sIdMsg);
        
        if(isFilled(sDate)){
            
            if(sDate.length()!=iLen){
                codeErreur = SS_Df.ccode(DATE_LENGHT);
                msgErreur = dateField+SS_Df.cmsg(DATE_LENGHT)+dateFormat;
                 return false;
            }
            
            if(!checkValidFormat(dateFormat, sDate)){
                codeErreur = SS_Df.ccode(DATE_VALUE);
                msgErreur = dateField+SS_Df.cmsg(DATE_VALUE)+dateFormat;
                 return false;
            }
        }
        else{
            if(chkEmpty){
                codeErreur = SS_Df.ccode(DATE_EMPTY);
                msgErreur = dateField+SS_Df.cmsg(DATE_EMPTY)+dateFormat;
                return false;
            }
        }
        return true;
    }

    public boolean  checkDataAddress(SSEnt_Address[] custadress) {
        if(custadress==null || custadress.length == 0){
            codeErreur = SS_Df.ccode(ENRL_ADR_COUNT);
            msgErreur = SS_Df.cmsg(ENRL_ADR_COUNT);
            return false;
        }
            
        for (SSEnt_Address addEntity : custadress) {
               if(!( checkStringEmpty(addEntity.getAddrtype(),ENRL_ADRTYP_EMPTY) &&
                checkStringLenght(addEntity.getAddrtype(),LEN_TYP_2,ENRL_ADRTYP_LENGHT) &&
                checkStringList(addEntity.getAddrtype(),new String[] {"01","02","03","04","05","05"},ENRL_ADRTYP_VALUE) &&
                checkStringEmpty(addEntity.getAddcorr(),ENRL_ADRCRS_EMPTY) &&
                checkStringLenght(addEntity.getAddcorr(),LEN_TYP_2,ENRL_ADRCRS_LENGHT) &&
                checkStringList(addEntity.getAddcorr(),new String[] {"01","02","03","04","05","05"},ENRL_ADRCRS_VALUE) &&    
                checkStringEmpty(addEntity.getAddr1(),ENRL_ADR1_EMPTY) &&
                checkStringLenght(addEntity.getAddr1(),LEN_ADR_80,ENRL_ADR1_LENGHT)&&         
                checkStringLenght(addEntity.getAddr2(),LEN_ADR_80,ENRL_ADR2_LENGHT) &&
                checkStringLenght(addEntity.getAddstreet(),40,ENRL_ADRSTR_LENGHT) &&
                checkDataAddzipd(addEntity.getAddzipd()) &&
                checkDataCitycode(addEntity.getCitycode()) &&
                checkDataPostalcode(addEntity.getPostalcode()) &&
                checkDataCountry(addEntity.getCountry())))
                   return false;
            
        }
        
        return true;
    }
   
    public boolean  isFilled(String str) {
        
        return (str!=null) && (!str.isEmpty());
    }
    
    private boolean checkValidFormat(String format, String value) {
        LocalDateTime ldt;
        Locale locale = Locale.ENGLISH;
        DateTimeFormatter fomatter = DateTimeFormatter.ofPattern(format, locale);

        try {
            ldt = LocalDateTime.parse(value, fomatter);
            String result = ldt.format(fomatter);
            return result.equals(value);
        } catch (DateTimeParseException e) {
            try {
                LocalDate ld = LocalDate.parse(value, fomatter);
                String result = ld.format(fomatter);
                return result.equals(value);
            } catch (DateTimeParseException exp) {
                try {
                    LocalTime lt = LocalTime.parse(value, fomatter);
                    String result = lt.format(fomatter);
                    return result.equals(value);
                } catch (DateTimeParseException e2) {
                }
            }
        }

        return false;
    }
}