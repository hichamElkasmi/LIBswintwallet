/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssw.bnk.swintwallet.jdbc.in;


/**
 *
 * @author rmokh
 */
public class SS_Df {
 
    public static final int ID_CHECK                = 10000;
    public static final int HEAD_MAC_EMPTY          = 0;
    public static final int HEAD_MAC_LENGHT         = HEAD_MAC_EMPTY+1;
    public static final int HEAD_ID_EMPTY           = HEAD_MAC_LENGHT+1;
    public static final int HEAD_ID_LENGHT          = HEAD_ID_EMPTY+1;
    public static final int INIT_IDENT_EMPTY        = HEAD_ID_LENGHT+1;
    public static final int INIT_IDENT_LENGHT       = INIT_IDENT_EMPTY+1;
    public static final int INIT_IDENT_VALUE        = INIT_IDENT_LENGHT+1;
    public static final int INIT_TYPE_EMPTY         = INIT_IDENT_VALUE+1;
    public static final int INIT_TYPE_VALUE         = INIT_TYPE_EMPTY+1;
    public static final int INIT_WALLET_CODE_EMPTY  = INIT_TYPE_VALUE+1;
    public static final int INIT_WALLET_CODE_LENGHT = INIT_WALLET_CODE_EMPTY+1;
    public static final int INIT_WALLET_CODE_VALUE  = INIT_WALLET_CODE_LENGHT+1;
    public static final int FILTER_NTRANS_LENGHT    = INIT_WALLET_CODE_VALUE+1;
    public static final int FILTER_NTRANS_VALUE     = FILTER_NTRANS_LENGHT+1;
    public static final int FILTER_IDTRANS_LENGHT   = FILTER_NTRANS_VALUE+1;
    public static final int FILTER_DSTART_FIELD     = FILTER_IDTRANS_LENGHT+1;
    public static final int FILTER_DEND_FIELD       = FILTER_DSTART_FIELD+1;
    public static final int BANK_EMPTY              = FILTER_DEND_FIELD+1;
    public static final int BANK_LENGHT             = BANK_EMPTY+1;
    public static final int BANK_VALUE              = BANK_LENGHT+1;
    public static final int REQ_DATE                = BANK_VALUE+1;
    public static final int REQ_AMOUNT_EMPTY        = REQ_DATE+1;
    public static final int REQ_AMOUNT_LENGHT       = REQ_AMOUNT_EMPTY+1;
    public static final int REQ_AMOUNT_VALUE        = REQ_AMOUNT_LENGHT+1;
    public static final int REQ_CURRENCY_EMPTY      = REQ_AMOUNT_VALUE+1;
    public static final int REQ_CURRENCY_LENGHT     = REQ_CURRENCY_EMPTY+1;
    public static final int REQ_ADDID_EMPTY         = REQ_CURRENCY_LENGHT+1;
    public static final int REQ_ADDID_LENGHT        = REQ_ADDID_EMPTY+1;
    public static final int REQ_ADDID_VALUE         = REQ_ADDID_LENGHT+1;  
    public static final int REQ_ADDDATA_EMPTY       = REQ_ADDID_VALUE+1;
    public static final int REQ_ADDDATA_LENGHT      = REQ_ADDDATA_EMPTY+1;
    public static final int REQ_ADDDATA_VALUE       = REQ_ADDDATA_LENGHT+1;
    public static final int REQ_LABEL_LENGHT        = REQ_ADDDATA_VALUE+1;
    public static final int NATID_EMPTY             = REQ_LABEL_LENGHT+1;
    public static final int NATID_LENGHT            = NATID_EMPTY+1;
    public static final int PHONE_EMPTY             = NATID_LENGHT+1;
    public static final int PHONE_LENGHT            = PHONE_EMPTY+1;
    public static final int PHONE_VALUE             = PHONE_LENGHT+1;
    public static final int BRANCH_EMPTY            = PHONE_VALUE+1;
    public static final int BRANCH_LENGHT           = BRANCH_EMPTY+1;
    public static final int BRANCH_VALUE            = BRANCH_LENGHT+1;
    public static final int CUSTOMER_LENGHT         = BRANCH_VALUE+1;
    public static final int CUSTOMER_VALUE          = CUSTOMER_LENGHT+1;
    public static final int CUSTOMER_EMPTY          = CUSTOMER_VALUE+1;
    public static final int ACCOUNT_EMPTY           = CUSTOMER_EMPTY+1;
    public static final int ACCOUNT_LENGHT          = ACCOUNT_EMPTY+1;
    public static final int ACCOUNT_VALUE           = ACCOUNT_LENGHT+1;
    public static final int ACC_PROGRAM_EMPTY       = ACCOUNT_VALUE+1;
    public static final int ACC_PROGRAM_LENGHT      = ACC_PROGRAM_EMPTY+1;
    public static final int ACC_PROGRAM_VALUE       = ACC_PROGRAM_LENGHT+1;
    public static final int DATE_EMPTY              = ACC_PROGRAM_VALUE+1;
    public static final int DATE_LENGHT             = DATE_EMPTY+1;
    public static final int DATE_VALUE              = DATE_LENGHT+1;
    public static final int DATE_FORMAT_SMALL       = DATE_VALUE+1;
    public static final int DATE_FORMAT_LONG        = DATE_FORMAT_SMALL+1;
    public static final int ENRL_EN_FSTNAME_EMPTY   = DATE_FORMAT_LONG+1;
    public static final int ENRL_EN_FSTNAME_LENGHT  = ENRL_EN_FSTNAME_EMPTY+1;
    public static final int ENRL_EN_LSTNAME_EMPTY   = ENRL_EN_FSTNAME_LENGHT+1;
    public static final int ENRL_EN_LSTNAME_LENGHT  = ENRL_EN_LSTNAME_EMPTY+1;
    public static final int ENRL_AR_FSTNAME_EMPTY   = ENRL_EN_LSTNAME_LENGHT+1;
    public static final int ENRL_AR_FSTNAME_LENGHT  = ENRL_AR_FSTNAME_EMPTY+1;
    public static final int ENRL_AR_LSTNAME_EMPTY   = ENRL_AR_FSTNAME_LENGHT+1;
    public static final int ENRL_AR_LSTNAME_LENGHT  = ENRL_AR_LSTNAME_EMPTY+1;
    public static final int ENRL_GENDER_VALUE       = ENRL_AR_LSTNAME_LENGHT+1;
    public static final int ENRL_TITEL_VALUE        = ENRL_GENDER_VALUE+1;
    public static final int ENRL_DBIRTH             = ENRL_TITEL_VALUE+1;
    public static final int ENRL_POB_LENGHT         = ENRL_DBIRTH+1;
    public static final int ENRL_NAT_LENGHT         = ENRL_POB_LENGHT+1;
    public static final int ENRL_MAIL_LENGHT        = ENRL_NAT_LENGHT+1;
    public static final int ENRL_MAIL_VALUE         = ENRL_MAIL_LENGHT+1;
    public static final int ENRL_DPSPRT             = ENRL_MAIL_VALUE+1;
    public static final int ENRL_PSRTID_LENGHT      = ENRL_DPSPRT+1;
    public static final int ENRL_ADRTYP_EMPTY       = ENRL_PSRTID_LENGHT+1;
    public static final int ENRL_ADRTYP_LENGHT      = ENRL_ADRTYP_EMPTY+1;
    public static final int ENRL_ADRTYP_VALUE       = ENRL_ADRTYP_LENGHT+1;
    public static final int ENRL_ADRCRS_EMPTY       = ENRL_ADRTYP_VALUE+1;
    public static final int ENRL_ADRCRS_LENGHT      = ENRL_ADRCRS_EMPTY+1;
    public static final int ENRL_ADRCRS_VALUE       = ENRL_ADRCRS_LENGHT+1;
    public static final int ENRL_ADR1_EMPTY         = ENRL_ADRCRS_VALUE+1;
    public static final int ENRL_ADR1_LENGHT        = ENRL_ADR1_EMPTY+1;
    public static final int ENRL_ADR2_LENGHT        = ENRL_ADR1_LENGHT+1;
    public static final int ENRL_ADRSTR_LENGHT      = ENRL_ADR2_LENGHT+1;
    public static final int ENRL_ADRSPD_LENGHT      = ENRL_ADRSTR_LENGHT+1;
    public static final int ENRL_ADRCTY_LENGHT      = ENRL_ADRSPD_LENGHT+1;
    public static final int ENRL_ADRPSTL_LENGHT     = ENRL_ADRCTY_LENGHT+1;
    public static final int ENRL_ADRCTR_LENGHT      = ENRL_ADRPSTL_LENGHT+1;
    public static final int ENRL_ADRCTR_VALUE       = ENRL_ADRCTR_LENGHT+1;
    public static final int ENRL_ADRPSTL_VALUE      = ENRL_ADRCTR_VALUE+1;
    public static final int ENRL_ADRCTY_VALUE       = ENRL_ADRPSTL_VALUE+1;
    public static final int ENRL_ADRSPD_VALUE       = ENRL_ADRCTY_VALUE+1;
    public static final int ENRL_ADR_COUNT          = ENRL_ADRSPD_VALUE+1;
    public static final int ENRL_MERCHANTID_LENGHT  = ENRL_ADR_COUNT+1;
    public static final int ENRL_MERCHANTNAME_EMPTY = ENRL_MERCHANTID_LENGHT+1;
    public static final int ENRL_MERCHANTNAME_LENGHT= ENRL_MERCHANTNAME_EMPTY+1;
    public static final int MER_ACTIVITY_EMPTY      = ENRL_MERCHANTNAME_LENGHT+1;
    public static final int MER_ACTIVITY_LENGHT     = MER_ACTIVITY_EMPTY+1;
    public static final int MER_ACTIVITY_VALUE      = MER_ACTIVITY_LENGHT+1;
    public static final int MER_PROGRAM_EMPTY       = MER_ACTIVITY_VALUE+1;
    public static final int MER_PROGRAM_LENGHT      = MER_PROGRAM_EMPTY+1;
    public static final int MER_PROGRAM_VALUE       = MER_PROGRAM_LENGHT+1;
    public static final int ENT_HEADER_NULL         = MER_PROGRAM_VALUE+1;
    public static final int ENT_DATA_NULL           = ENT_HEADER_NULL+1;
    public static final int ENT_INITIATOR_NULL      = ENT_DATA_NULL+1;
    public static final int ENT_IDENTITY_NULL       = ENT_INITIATOR_NULL+1;
    public static final int ENT_CRITERIA_NULL       = ENT_IDENTITY_NULL+1;
    public static final int ENT_FILTER_NULL         = ENT_CRITERIA_NULL+1;
    public static final int ENT_REQUEST_NULL        = ENT_FILTER_NULL+1; 
    
    // EKH NEW CODES
    public static final int ENT_MONEYTRANSFER_NULL        = ENT_REQUEST_NULL+1; 
    public static final int MONEY_TERM_COD_EMPTY        = ENT_MONEYTRANSFER_NULL+1; 
    public static final int MONEY_TERM_TYP_EMPTY        = MONEY_TERM_COD_EMPTY+1; 
    public static final int ENTIT_TYPE_FROM_EMPTY        = MONEY_TERM_TYP_EMPTY+1; 
    public static final int ENTIT_TYPE_TO_EMPTY        = ENTIT_TYPE_FROM_EMPTY+1; 
    public static final int TRANS_DATE        = ENTIT_TYPE_TO_EMPTY+1; 
    public static final int MONEY_TREM_LEN        = TRANS_DATE+1; 
    public static final int MONEY_TERM_VALUES        = MONEY_TREM_LEN+1; 
    public static final int MONEY_ENT_TYP_FROM_VAL        = MONEY_TERM_VALUES+1; 
    public static final int MONEY_ENT_TYP_TO_VAL        = MONEY_ENT_TYP_FROM_VAL+1; 
    public static final int MONEY_CURR_EMPTY        = MONEY_ENT_TYP_TO_VAL+1; 
    public static final int MONEY_CURR_LEN        = MONEY_CURR_EMPTY+1; 
    public static final int MONEY_AMOUNT_EMPTY        = MONEY_CURR_LEN+1; 
    public static final int MONEY_DATE_EMPTY        = MONEY_AMOUNT_EMPTY+1; 
    public static final int MONEY_ENTI_COD_FROM_EMPTY        = MONEY_DATE_EMPTY+1; 
    public static final int MONEY_ENTI_COD_TO_EMPTY        = MONEY_ENTI_COD_FROM_EMPTY+1; 
    public static final int MONEY_NOT_NUMIRIC        = MONEY_ENTI_COD_TO_EMPTY+1; 
    
    // END EKH NEW CODES
                    
    public static final String OK_SS = "00000";
    public static final int ID_STATUS   = 0;
    public static final int ST_APPROVED = 0;
    public static final int ST_WAIT     = ST_APPROVED+1;
    public static final int ST_REJECTED = ST_WAIT+1;
    public static final int ST_ERROR    = ST_REJECTED+1;
    public static final int ST_NODATA   = ST_ERROR+1;
    
    public static final int ID_ERROR   = 20000;
    public static final int ER_CLEAN   = -1;
    public static final int ER_AUTH    = 0;
    public static final int ER_INPUT   = ER_AUTH+1;
    public static final int ER_PROCESS = ER_INPUT+1;
    public static final int ER_ERROR   = ER_PROCESS+1;
    public static final int ER_DATA    = ER_ERROR+1;
    public static final int ER_PARSE    = ER_DATA+1;
    public static final int ER_STREAM    = ER_PARSE+1;
    
    private static final String[] LIST_ERRORS={
        /*ER_AUTH*/"INCORRECT AUTHENTIFICATION REQ",
        /*ER_INPUT*/"INCORRECT INPUT",
        /*ER_PROCESS*/"PROCESSING ERROR: ",
        /*ER_ERROR*/"OTHER ERROR: ",
        /*ER_DATA*/"ERROR GET DATA: ",
        /*ER_PARSE*/"ERROR PARSING: ",
        /*ER_STREAM*/"ERROR STREAM: "
    };
    
    private static final String[] LIST_STATUS={
        /*ST_APPROVED*/"APPROVED",
        /*ST_WAIT*/"WAITING FOR APPROVAL",
        /*ST_REJECTED*/"NOT APPROVED",
        /*ST_ERROR*/"ERROR",
        /*ST_NODATA*/"NO DATA FOUND"
    };
     
    private static final String[] LIST_CHECKS={
        /*HEAD_MAC_EMPTY*/"MAC FIELD COULD NOT BE EMPTY",
        /*HEAD_MAC_LENGHT*/"MAC FIELD LENGHT IS INCORRECT, MAX 200",
        /*HEAD_ID_EMPTY*/"ID MSG REQUEST FIELD COULD NOT BE EMPTY",
        /*HEAD_ID_LENGHT*/"ID MSG REQUEST LENGHT IS INCORRECT, MAX 40",
        /*INIT_IDENT_EMPTY*/"INITIATOR IDENTIFIER FIELD COULD NOT BE EMPTY",
        /*INIT_IDENT_LENGHT*/"INITIATOR IDENTIFIER LENGHT IS INCORRECT, MAX 20",
        /*INIT_IDENT_VALUE*/"INITIATOR IDENTIFIER VALUE INCORRECT, SHOULD BE NUMERIC",
        /*INIT_TYPE_EMPTY*///"INITIATOR TYPE FIELD COULD NOT BE EMPTY",
        /*INIT_TYPE_VALUE*///"INITIATOR TYPE FIELD VALUE INCORRECT, SHOULD BE 'M' OR 'C'",
        /*INIT_WALLET_CODE_EMPTY*///"INITIATOR WALLET CODE FIELD COULD NOT BE EMPTY",
        /*INIT_WALLET_CODE_LENGHT*/"INITIATOR WALLET CODE LENGHT IS INCORRECT, MAX 20",
        /*INIT_WALLET_CODE_VALUE*/"INITIATOR WALLET CODE VALUE INCORRECT, SHOULD BE NUMERIC",
        /*FILTER_NTRANS_LENGHT*/"NTRANS FIELD LENGHT IS INCORRECT, MIN (1 TRX) & MAX (99 TRX)",
        /*FILTER_NTRANS_VALUE*/"NTRANS FIELD VALUE INCORRECT, SHOULD BE NUMERIC, MAX (99 TRX)",
        /*FILTER_IDTRANS_LENGHT*/"IDTRANS FIELD LENGHT IS INCORRECT, MIN (1 TRX) & MAX (40 TRX)",
        /*FILTER_DSTART_FIELD*/"START",
        /*FILTER_DEND_FIELD*/"END",
        /*BANK_EMPTY*/"BANK CODE FIELD COULD NOT BE EMPTY",
        /*BANK_LENGHT*/"BANK CODE LENGHT IS INCORRECT, MAX 20",
        /*BANK_VALUE*/"BANK CODE FIELD VALUE INCORRECT, SHOULD BE NUMERIC",
        /*REQ_DATE*/"REQUEST",    
        /*REQ_AMOUNT_EMPTY*/"AMOUNT FIELD COULD NOT BE EMPTY EKH",   
        /*REQ_AMOUNT_LENGHT*/"AMOUNT LENGHT IS INCORRECT, MAX 20",  
        /*REQ_AMOUNT_VALUE*/"AMOUNT VALUE INCORRECT, SHOULD BE NUMERIC",   
        /*REQ_CURRENCY_EMPTY*/"CURRENCY FIELD COULD NOT BE EMPTY", 
        /*REQ_CURRENCY_LENGHT*/"CURRENCY LENGHT IS INCORRECT, MAX 3",
        /*REQ_ADDID_EMPTY*/"ADD ID FIELD COULD NOT BE EMPTY",    
        /*REQ_ADDID_LENGHT*/"ADD ID LENGHT IS INCORRECT, MAX 20",   
        /*REQ_ADDID_VALUE*/"ADD ID VALUE INCORRECT, SHOULD BE NUMERIC",    
        /*REQ_ADDDATA_EMPTY*/"ADD DATA FIELD COULD NOT BE EMPTY",  
        /*REQ_ADDDATA_LENGHT*/"ADD DATA LENGHT IS INCORRECT, MAX 15",
        /*REQ_ADDDATA_VALUE*/"ADD DATA VALUE INCORRECT, SHOULD BE NUMERIC",
        /*REQ_LABEL_LENGHT*/"LABEL LENGHT IS INCORRECT, MAX 50",
        /*NATID_EMPTY*/"NATIONAL ID FIELD COULD NOT BE EMPTY",
        /*NATID_LENGHT*/"NATIONAL ID LENGHT IS INCORRECT, MAX 30",
        /*PHONE_EMPTY*/"MOBILE NUMBER FIELD COULD NOT BE EMPTY",
        /*PHONE_LENGHT*/"MOBILE NUMBER LENGHT IS INCORRECT, MAX 20",
        /*PHONE_VALUE*/"MOBILE NUMBER VALUE INCORRECT, SHOULD BE NUMERIC STARTED WITH 218 NOT (00) NOR (+)",
        /*BRANCH_EMPTY*/"BRANCH CODE FIELD COULD NOT BE EMPTY",
        /*BRANCH_LENGHT*/"BRANCH CODE LENGHT IS INCORRECT, MAX 20",
        /*BRANCH_VALUE*/"BRANCH CODE VALUE INCORRECT, SHOULD BE NUMERIC",
        /*CUSTOMER_LENGHT*/"CUSTOMER CODE LENGHT IS INCORRECT, MAX 20",
        /*CUSTOMER_VALUE*/"CUSTOMER CODE VALUE INCORRECT, SHOULD BE NUMERIC",
        /*CUSTOMER_EMPTY*/"CUSTOMER CODE FIELD COULD NOT BE EMPTY",
        /*ACCOUNT_EMPTY*/"ACCOUNT FIELD COULD NOT BE EMPTY",
        /*ACCOUNT_LENGHT*/"ACCOUNT LENGHT IS INCORRECT, MAX 24",
        /*ACCOUNT_VALUE*/"ACCOUNT VALUE INCORRECT, SHOULD BE NUMERIC",
        /*ACC_PROGRAM_EMPTY*/"ACCOUNT PROGRAM CODE FIELD COULD NOT BE EMPTY",
        /*ACC_PROGRAM_LENGHT*/"ACCOUNT PROGRAM CODE LENGHT IS INCORRECT, MAX 20",
        /*ACC_PROGRAM_VALUE*/"ACCOUNT PROGRAM CODE VALUE INCORRECT, SHOULD BE NUMERIC",
        /*DATE_EMPTY*/" DATE FIELD COULD NOT BE EMPTY, FORMAT ",
        /*DATE_LENGHT*/" DATE FIELD LENGHT IS INCORRECT, FORMAT ",
        /*DATE_VALUE*/" DATE FIELD VALUE IS INCORRECT, FORMAT ",
        /*DATE_FORMAT_SMALL*/"yyyy-MM-dd",
        /*DATE_FORMAT_LONG*/"yyyy-MM-dd HH:mm:ss",
        /*ENRL_EN_FSTNAME_EMPTY*/"ENGLISH FIRST NAME COULD NOT BE EMPTY",
        /*ENRL_EN_FSTNAME_LENGHT*/"ENGLISH FIRST NAME LENGHT IS INCORRECT, MAX 40",
        /*ENRL_EN_LSTNAME_EMPTY*/"ENGLISH LAST NAME COULD NOT BE EMPTY",
        /*ENRL_EN_LSTNAME_LENGHT*/"ENGLISH LAST NAME LENGHT IS INCORRECT, MAX 40",
        /*ENRL_AR_FSTNAME_EMPTY*/"ARABIC FIRST NAME COULD NOT BE EMPTY",
        /*ENRL_AR_FSTNAME_LENGHT*/"ARABIC FIRST NAME LENGHT IS INCORRECT, MAX 40",
        /*ENRL_AR_LSTNAME_EMPTY*/"ARABIC LAST NAME COULD NOT BE EMPTY",
        /*ENRL_AR_LSTNAME_LENGHT*/"ARABIC LAST NAME LENGHT IS INCORRECT, MAX 40",
        /*ENRL_GENDER_VALUE*/"GENDER VALUE INCORRECT, SHOULD BE 'M' OR 'F'",
        /*ENRL_TITEL_VALUE*/"TITEL VALUE INCORRECT, SHOULD BE NUMERIC WITH ONE OF VALUES '1'=Mr,'2'=Mrs OR '3'=Miss",
        /*ENRL_DBIRTH*/"BIRTH",
        /*ENRL_POB_LENGHT*/"PLACE OF BIRTH LENGHT IS INCORRECT, MAX 15",
        /*ENRL_NAT_LENGHT*/"NATIONALITY LENGHT IS INCORRECT, MAX 20",
        /*ENRL_MAIL_LENGHT*/"EMAIL ADR LENGHT IS INCORRECT, MAX 128",
        /*ENRL_MAIL_VALUE*/"EMAIL VALUE IS INCORRECT",
        /*ENRL_DPSPRT*/"PASSPORT EXP",
        /*ENRL_PSRTID_LENGHT*/"PASSPORT ID LENGHT IS INCORRECT, MAX 20",
        /*ENRL_ADRTYP_EMPTY*/"ADDRESS TYPE COULD NOT BE EMPTY",
        /*ENRL_ADRTYP_LENGHT*/"ADDRESS TYPE IS INCORRECT, SHOULD BE NUMERIC WITH POSSIBLE VALUES (01 , 02 , 03 , 04, 05 OR 06)",
        /*ENRL_ADRTYP_VALUE*/"ADDRESS TYPE IS INCORRECT, SHOULD BE NUMERIC WITH POSSIBLE VALUES (01 , 02 , 03 , 04, 05 OR 06)",
        /*ENRL_ADRCRS_EMPTY*/"ADDRESS CORRESPONDENCE COULD NOT BE EMPTY",
        /*ENRL_ADRCRS_LENGHT*/"ADDRESS CORRESPONDENCE IS INCORRECT, SHOULD BE NUMERIC WITH POSSIBLE VALUES (01 , 02 , 03 , 04, 05 OR 06)",
        /*ENRL_ADRCRS_VALUE*/"ADDRESS CORRESPONDENCE IS INCORRECT, SHOULD BE NUMERIC WITH POSSIBLE VALUES (01 , 02 , 03 , 04, 05 OR 06)",
        /*ENRL_ADR1_EMPTY*/"ADDRESS A COULD NOT BE EMPTY",
        /*ENRL_ADR1_LENGHT*/"ADDRESS A LENGHT IS INCORRECT, MAX 80",
        /*ENRL_ADR2_LENGHT*/"ADDRESS B LENGHT IS INCORRECT, MAX 80",
        /*ENRL_ADRSTR_LENGHT*/"ADDRESS STREET LENGHT IS INCORRECT, MAX 40",
        /*ENRL_ADRSPD_LENGHT*/"ZIP CODE ADDRESS LENGHT IS INCORRECT, MAX 5",
        /*ENRL_ADRCTY_LENGHT*/"CITY CODE ADDRESS LENGHT IS INCORRECT, MAX 10",
        /*ENRL_ADRPSTL_LENGHT*/"POSTAL CODE LENGHT IS INCORRECT, MAX 20",
        /*ENRL_ADRCTR_LENGHT*/"COUNTRY CODE LENGHT IS INCORRECT, MAX 10",
        /*ENRL_ADRCTR_VALUE*/"COUNTRY CODE VALUE INCORRECT, SHOULD BE NUMERIC",
        /*ENRL_ADRPSTL_VALUE*/"POSTAL CODE VALUE INCORRECT, SHOULD BE NUMERIC",
        /*ENRL_ADRCTY_VALUE*/"CITY CODE VALUE INCORRECT, SHOULD BE NUMERIC",
        /*ENRL_ADRSPD_VALUE*/"ZIP CODE ADDRESS VALUE INCORRECT, SHOULD BE NUMERIC",
        /*ENRL_ADR_COUNT*/"ADDRESS COUNT INCORRECT, AT LEAST ONE ADDRESS SHOULD BE FILLED",
        /*ENRL_MERCHANTID_LENGHT*/"MERCHANT ID LENGHT IS INCORRECT, MAX 20",
        /*ENRL_MERCHANTNAME_EMPTY*/"MERCHANT NAME COULD NOT BE EMPTY",
        /*ENRL_MERCHANTNAME_LENGHT*/"MERCHANT NAME LENGHT IS INCORRECT, MAX 40",
        /*MER_ACTIVITY_EMPTY*/"MERCHANT ACTIVITY CODE COULD NOT BE EMPTY",
        /*MER_ACTIVITY_LENGHT*/"MERCHANT ACTIVITY CODE LENGHT IS INCORRECT, MAX 20",
        /*MER_ACTIVITY_VALUE*/"MERCHANT ACTIVITY CODE VALUE INCORRECT, SHOULD BE NUMERIC",
        /*MER_PROGRAM_EMPTY*/"MERCHANT PROGRAM CODE COULD NOT BE EMPTY",
        /*MER_PROGRAM_LENGHT*/"MERCHANT PROGRAM CODE LENGHT IS INCORRECT, MAX 20",
        /*MER_PROGRAM_VALUE*/"MERCHANT PROGRAM CODE VALUE INCORRECT, SHOULD BE NUMERIC",
        /*ENT_HEADER_NULL*/"HEADER ENTITY NOT FOUND",
        /*ENT_DATA_NULL*/"DATA ENTITY NOT FOUND",
        /*ENT_INITIATOR_NULL*/"INITIATOR ENTITY NOT FOUND",
        /*ENT_IDENTITY_NULL*/"IDENTITY ENTITY NOT FOUND",
        /*ENT_CRITERIA_NULL*/"CRITERIA ENTITY NOT FOUND",
        /*ENT_FILTER_NULL*/"FILTER ENTITY NOT FOUND",
        /*ENT_REQUEST_NULL*/"REQUEST ENTITY NOT FOUND" ,
        
        // EKH 
        /*ENT_MONEYTRANSFER_NULL*/"MONEYTRANSFER ENTITY NOT FOUND",
        /*MONEY_TERM_COD_EMPTY*/"TERMINAL CODE IS EMPTY",
        /*MONEY_TERM_TYP_EMPTY*/"TERMINAL TYPE IS EMPTY",
        /*ENTIT_TYPE_FROM_EMPTY*/"SOURCE ENTITY IS EMPTY",
        /*ENTIT_TYPE_TO_EMPTY*/"DESTINATION ENTITY IS EMPTY",
        /*TRANS_DATE*/"TRANSACTION DATE",
        /*MONEY_TREM_LEN*/"TERMINAL CODE LENGTH IS NOT CORRECT MAX 20",
        /*MONEY_TERM_VALUES*/"TERMINAL VALUE MUST BE : TM or TC",
        /*MONEY_ENT_TYP_FROM_VAL*/"ENTITY TYPE FROM MUST BE : CARD or WALLET or BANK ACCOUNT",
        /*MONEY_ENT_TYP_TO_VAL*/"ENTITY TYPE TO MUST BE : CARD or WALLET or BANK ACCOUNT",
        /*MONEY_CURR_EMPTY*/"CURRENCY IS EMPTY",
        /*MONEY_CURR_LEN*/"CURRENCY LENGTH IS NOT CORRECT MAX 3",
        /*MONEY_AMOUNT_EMPTY*/"TRANSACTION AMOUNT IS EMPTY",
        /*MONEY_DATE_EMPTY*/"TRANSACTION DATE IS EMPTY",
        /*MONEY_ENTI_COD_FROM_EMPTY*/"SOURCE ENTITY CODE IS EMPTY",
        /*MONEY_ENTI_COD_TO_EMPTY*/"DESTINATION ENTITY CODE IS EMPTY",
        /*MONEY_NOT_NUMIRIC*/"AMOUNT IS NOT NUMIRIC",

        
    };
    
    public static String ccode(int idxError){
        return String.valueOf(idxError+ID_CHECK);
    }
    
    public static String cmsg(int idxError){
        return LIST_CHECKS[idxError];
    }
    
    public static String scode(int idxError){
        return String.valueOf(idxError+ID_STATUS);
    }
    
    public static String smsg(int idxError){
        return LIST_STATUS[idxError];
    }
    
    public static String ecode(int idxError){
        if (idxError<0) return "";
        return String.valueOf(idxError+ID_ERROR);
    }
    
    public static String emsg(int idxError){
        if (idxError<0) return "";
        return LIST_ERRORS[idxError];
    }
}
