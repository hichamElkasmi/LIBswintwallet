package com.ssw.bnk.swintwallet.jdbc.in;

import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_Auth;
import com.ssw.bnk.swintwallet.jdbc.responses.*;
import com.ssw.bnk.swintwallet.jdbc.requests.*;
import com.ssw.bnk.swintwallet.jdbc.entities.*;
import com.ssw.bnk.swintwallet.jdbc.*;
import com.ssw.bnk.swintwallet.jdbc.in.SS_Chk;
import com.ssw.bnk.swintwallet.jdbc.in.SS_Stt;
import static com.ssw.bnk.swintwallet.jdbc.SW_INT.isLiveCtx;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.DatatypeConverter;
import java.util.Map;
import oracle.jdbc.OracleTypes;
import static com.ssw.bnk.swintwallet.jdbc.in.SS_Df.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.ResultSet;

/**
 *
 * @author MOKHLISS
 */
public class SS_Prc {
    
    private static final short CLR=0;
    private static final short SQL=1;
    private static final short REQ=2;
    private static final short RES=3;
    
    private static final SSEnt_Header DEFAULT_HEAD = new SSEnt_Header("999999999999","999999999999");
    
    private static final String ALGO = "SHA-256";
    private static final String KEY_CLIENT = "142DF54EF47ZE78B";
    private static final String KEY_HOST = "552A15CF5889EAFF";
    
    private static final String PACKAGE ="call wallet.WT_INTERFACE_PKG";
    private static final String PROCEDURE ="call wallet.GET_REF_DATA";
    
    private static final String CUSTOMER[] ={" <enrollCustomer> ", "{"+PACKAGE+".WALLETREGISTRATION(?,?)}","WALLETREGISTRATIONRQ","WALLETREGISTRATIONRS"};
    private static final String MERCHANT[] ={" <enrollMerchant> ", "{"+PACKAGE+".MERCHANTREGISTRATION(?,?,?)}","MERCHANTREGISTRATIONRQ","MERCHANTREGISTRATIONRS"};
    private static final String CITY[] ={" <getCities> ", "{"+PACKAGE+".GETCITIES(?,?)}","GETCITIESRQ","GETCITIESRS"};
    private static final String BANK[] ={" <getBanks> ", "{"+PACKAGE+".GETBANKS(?,?)}","GETBANKSRQ","GETBANKSRS"};
    private static final String BRANCH[] ={" <getbranchs> ", "{"+PACKAGE+".GETBRANCHS(?,?)}","GETBRANCHSRQ","GETBRANCHSRS"};
    private static final String APL[] ={" <getApl> ", "{"+PACKAGE+".GETACCOUNTPROGRAMS(?,?)}","GETACCOUNTPROGRAMSRQ","GETACCOUNTPROGRAMSRS"};
    private static final String MPL[] ={" <getMpl> ", "{"+PACKAGE+".GETMERCHANTPROGRAMS(?,?)}","GETMERCHANTPROGRAMSRQ","GETMERCHANTPROGRAMSRS"};
   // private static final String BALANCE[] ={" <getBalance> ", "{"+PACKAGE+".getcustomerentitybalance(?,?)}","getcustomerentitybalancerq","getcustomerentitybalancers"};
    private static final String BALANCE[] ={" <getBalance> ", "{"+PACKAGE+".GETCUSTOMERENTITYBALANCE(?,?)}","GETCUSTOMERENTITYBALANCERQ","GETCUSTOMERENTITYBALANCERS"};
    private static final String TRANSACTION[] ={" <getTransactions> ", "{"+PACKAGE+".SEARCHTRANSACTIONS(?,?)}","SEARCHTRANSRQ","SEARCHTRANSRS"};
    private static final String PAYMENT[] ={"", "{"+PACKAGE+".ACCEPTPAYMENT(?,?)}","ACCEPTPAYMENTRQ","ACCEPTPAYMENTRS"};
    // EKH ADD NEW SERVICES
    private static final String VOUCHER_SS[] ={"", "{"+PACKAGE+".PURCHASEVOUCHER(?,?)}","PURCHASEVOUCHERRQ","PURCHASEVOUCHERRS"};
    private static final String HISTORYVOUCHER[] ={" <gethistoryVoucher> ", "{"+PACKAGE+".HISTORYVOUCHERTRAN(?,?)}","GETHISTORYVOUCHERRQ","GETHISTORYVOUCHERRS"};
    private static final String MONEYTRANSFER[] ={" <moneytransfer> ", "{"+PACKAGE+".MONEYTRANSFER(?,?)}","MONEYTRANSFERRQ","MONEYTRANSFERRS"};
    // END EKH
    
    //
    private static final String REPOSITORY[] ={"", "{"+PROCEDURE+"(?,?)}","VARCHAR2","VARCHAR2"};
    //
    private static final String TOPUP[] ={"", "{"+PACKAGE+".CREDITTRANSACTION(?,?)}","CRTRANSRQ","CRTRANSRS"};
    private static final String CUSTOMERINFO[] ={" <getCustomerInfo> ", "{"+PACKAGE+".CHECKCUSTOMERINFO(?,?)}","CHECKCUSTOMERINFORQ","CHECKCUSTOMERINFORS"};
    private static final String MERCHANTINFO[] ={" <getMerchantInfo> ", "{"+PACKAGE+".CHECKMERCHANTINFO(?,?)}","CHECKMERCHANTINFO1RQ","CHECKMERCHANTINFO1RS"};
    private static final String WALLETINFO[] ={" <getWalletInfo> ", "{"+PACKAGE+".GETCUSTOMERENTITYLIST(?,?)}","GETCUSTOMERENTITYLISTRQ","GETCUSTOMERENTITYLISTRS"};
    private static final String WALLETUPDATE[] ={" <updateCustomerInfo> ", "{"+PACKAGE+".WALLETINFOUPDATE(?,?)}","WALLETINFOUPDATERQ","WALLETINFOUPDATERS"};
    private static final String PINGSERVER[] ={" <pingServer> ", "{"+PACKAGE+".PINGSERVER(?,?)}","PINGSRVRQ","PINGSRVRS"};
    private static final String MNO[] ={" <getMno> ", "{"+PACKAGE+".GETMNOSLIST(?,?)}","GETMNOSLISTRQ","GETMNOSLISTRS"};

    public static enum TRANSACTION_TYPE{
        PAYMENT("500000"), 
        BILLPAYMENT("510000"), 
        VOUCHER("520000"), 
        PAYTAX("530000"),
        ETOPUP("540000"),
        TOPUP3RD("550000"),
        VOUCHERSS("600000");
        
        private final String value;

        TRANSACTION_TYPE(final String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return this.getValue();
        }
    }
    
    public SS_Prc() {
            
    }
    
    public SSEnt_Auth processAuthentification(SSEnt_Header psSEnt_Header)
    {
        String originalString, evvString;
        byte[] encodedhash;
        MessageDigest digest;
        SS_Chk sS_Chk = new SS_Chk();
        SS_Stt sS_Status = new SS_Stt(scode(ST_APPROVED), smsg(ST_APPROVED),
                                            ecode(ER_CLEAN), emsg(ER_CLEAN));
        
        if (!sS_Chk.checkDataAuth(psSEnt_Header))
        {
            sS_Status = new SS_Stt(scode(ST_REJECTED), smsg(ST_REJECTED),
                    sS_Chk.code(), sS_Chk.msg());
            
            return(new SSEnt_Auth(getHostHeader(DEFAULT_HEAD.getIdmsg()), sS_Status));

        }
        
        try {         
            digest = MessageDigest.getInstance(ALGO);
            originalString = psSEnt_Header.getIdmsg() + KEY_CLIENT;
            encodedhash = digest.digest(originalString.getBytes(StandardCharsets.UTF_8));
            evvString = DatatypeConverter.printHexBinary(encodedhash).substring(0, 16);
            
            if(!evvString.equalsIgnoreCase(psSEnt_Header.getMac()))
                {
                    sS_Status = new SS_Stt(scode(ST_REJECTED), smsg(ST_REJECTED),
                                                    ecode(ER_AUTH), emsg(ER_AUTH));
                }        
        } catch (Exception e) {
            sS_Status = new SS_Stt(scode(ST_REJECTED), smsg(ST_REJECTED),
                                            ecode(ER_AUTH), emsg(ER_AUTH));
        }
   
        return(new SSEnt_Auth(getHostHeader(psSEnt_Header.getIdmsg()), sS_Status));
    }

    private SSEnt_Header getHostHeader(String idMsg)
    {
        String originalString, evvString;
        byte[] encodedhash;
        MessageDigest digest;
        SSEnt_Header sSEnt_Header;
        
        try {
            
            digest = MessageDigest.getInstance(ALGO);
            originalString = idMsg + KEY_HOST;

            encodedhash = digest.digest(originalString.getBytes(StandardCharsets.UTF_8));
            evvString = DatatypeConverter.printHexBinary(encodedhash).substring(0, 16);
            sSEnt_Header = new SSEnt_Header(idMsg,evvString);
            
        } catch (Exception e) {
            sSEnt_Header = DEFAULT_HEAD;
        }
        
        return sSEnt_Header;
    }

    public SSRes_Enrolment enrollCustomer(SSReq_EnrolmentCustomer sSReq_EnrolmentCustomer) {
        
        CallableStatement callableStatement = null;
        Connection        connection        = null;
        SSRes_EnrolmentCustomer sSRes_Enrolment = new SSRes_EnrolmentCustomer();
        SS_Stt         sS_Status;
        SS_Chk sS_Chk = new SS_Chk();
        
        if (!sS_Chk.checkDataCustomer(sSReq_EnrolmentCustomer.getCustomer()))
        {
            sS_Status = new SS_Stt(scode(ST_REJECTED), smsg(ST_REJECTED),
                                            sS_Chk.code(), sS_Chk.msg());
             sSRes_Enrolment.setStatus(sS_Status);
            return sSRes_Enrolment;
        }
        
        try {
            if(isLiveCtx()){
                connection= SW_INT.getDBConnection();
                callableStatement = connection.prepareCall(CUSTOMER[SQL]);
                sSReq_EnrolmentCustomer.setSqlType(CUSTOMER[REQ],connection);
                sSRes_Enrolment.setSqlType(CUSTOMER[RES]);
                callableStatement.setObject(1, sSReq_EnrolmentCustomer);
                callableStatement.setObject(2, sSRes_Enrolment);
                callableStatement.registerOutParameter( 2, OracleTypes.STRUCT, CUSTOMER[RES]);
                callableStatement.executeUpdate();

                Map<String,Class<?>> typeMap = connection.getTypeMap();
                typeMap.put( CUSTOMER[RES], SSRes_EnrolmentCustomer.class );
                                
                sSRes_Enrolment = (SSRes_EnrolmentCustomer) callableStatement.getObject(2);
  
            }else{
                sSRes_Enrolment.setIdentity(new SSEnt_Identity("45866", "87974545"));
                sS_Status         = new SS_Stt(scode(ST_APPROVED), smsg(ST_APPROVED),
                                                            ecode(ER_CLEAN), emsg(ER_CLEAN));
                sSRes_Enrolment.setStatus(sS_Status);
            }
            
        }catch (Exception e) {     
            sS_Status = new SS_Stt(scode(ST_ERROR), smsg(ST_ERROR),
                                            ecode(ER_PROCESS),emsg(ER_PROCESS) + e.getMessage());
             sSRes_Enrolment.setStatus(sS_Status);
            return sSRes_Enrolment;
        } finally {
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SS_Prc.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection!=null) try {connection.close();}catch (Exception ignore) {}
        }
        sSRes_Enrolment.updateStatus(CUSTOMER[CLR]);
        return sSRes_Enrolment;
    }
  
    public SSRes_Enrolment enrollMerchant(SSReq_EnrolmentMerchant sSReq_EnrolmentMerchant) {
        
        CallableStatement callableStatement = null;
        Connection        connection        = null;
        SSRes_EnrolmentMerchant sSRes_Enrolment = new SSRes_EnrolmentMerchant();
        SS_Stt         sS_Status;
        SS_Chk sS_Chk = new SS_Chk();      
        
                
        if (!sS_Chk.checkDataMerchant(sSReq_EnrolmentMerchant.getMerchant()))
        {
            sS_Status = new SS_Stt(scode(ST_REJECTED), smsg(ST_REJECTED),
                                            sS_Chk.code(), sS_Chk.msg());
             sSRes_Enrolment.setStatus(sS_Status);
            return sSRes_Enrolment;
        }
        
        try {
            if(isLiveCtx()){
                connection= SW_INT.getDBConnection();
                callableStatement = connection.prepareCall(MERCHANT[SQL]);
                sSReq_EnrolmentMerchant.setSqlType(MERCHANT[REQ],connection);
                sSRes_Enrolment.setSqlType(MERCHANT[RES]);
                callableStatement.setObject(1, sSReq_EnrolmentMerchant);
                callableStatement.setObject(2, sSRes_Enrolment);
                callableStatement.setString(3, sSReq_EnrolmentMerchant.getMerchant().getMerchantid());
                callableStatement.registerOutParameter( 2, OracleTypes.STRUCT, MERCHANT[RES]);
                callableStatement.executeUpdate();

                Map<String,Class<?>> typeMap = connection.getTypeMap();
                typeMap.put( MERCHANT[RES], SSRes_EnrolmentMerchant.class );
                
                 sSRes_Enrolment = (SSRes_EnrolmentMerchant) callableStatement.getObject(2);  
            }else{
                sSRes_Enrolment.setIdentity(new SSEnt_Identity("88889"));
                sS_Status         = new SS_Stt(scode(ST_APPROVED), smsg(ST_APPROVED),
                                                            ecode(ER_CLEAN), emsg(ER_CLEAN));
                sSRes_Enrolment.setStatus(sS_Status);
            }
            
        }catch (Exception e) {     
            sS_Status = new SS_Stt(scode(ST_ERROR), smsg(ST_ERROR),
                                            ecode(ER_PROCESS),emsg(ER_PROCESS) + e.getMessage());
             sSRes_Enrolment.setStatus(sS_Status);
            return sSRes_Enrolment;
        } finally {
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SS_Prc.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection!=null) try {connection.close();}catch (Exception ignore) {}
        }
        sSRes_Enrolment.updateStatus(MERCHANT[CLR]);
        return sSRes_Enrolment;
    }

    public SSRes_Base updateCustomerInfo(SSReq_UpdateCustomer sSReq_UpdateCustomer) {
        
        CallableStatement callableStatement = null;
        Connection        connection        = null;
        SSRes_Base sSRes_Base = new SSRes_Base();
        SS_Stt         sS_Status;
        SS_Chk sS_Chk = new SS_Chk();     
        
        if ((!sS_Chk.checkDataUpdated(sSReq_UpdateCustomer.getCustomer())) ||
              (!sS_Chk.checkDataIdentity(sSReq_UpdateCustomer.getIdentity())) )
        {
            sS_Status = new SS_Stt(scode(ST_REJECTED), smsg(ST_REJECTED),
                                            sS_Chk.code(), sS_Chk.msg());
             sSRes_Base.setStatus(sS_Status);
            return sSRes_Base;
        }
        
        try {
            if(isLiveCtx()){
                connection= SW_INT.getDBConnection();
                callableStatement = connection.prepareCall(WALLETUPDATE[SQL]);
                sSReq_UpdateCustomer.setSqlType(WALLETUPDATE[REQ],connection);
                     System.out.println("OKAY");           
                sSRes_Base.setSqlType(WALLETUPDATE[RES]);
                callableStatement.setObject(1, sSReq_UpdateCustomer);
                System.out.println("PFF");
                callableStatement.setObject(2, sSRes_Base);
                callableStatement.registerOutParameter( 2, OracleTypes.STRUCT, WALLETUPDATE[RES]);
                callableStatement.executeUpdate();

                Map<String,Class<?>> typeMap = connection.getTypeMap();
                typeMap.put(WALLETUPDATE[RES], SSRes_Base.class );
                
                
                 sSRes_Base = (SSRes_Base) callableStatement.getObject(2);

            }else{
                sS_Status         = new SS_Stt(scode(ST_APPROVED), smsg(ST_APPROVED),
                                                            ecode(ER_CLEAN), emsg(ER_CLEAN));
                sSRes_Base.setStatus(sS_Status);
            }
            
        }catch (Exception e) {     
            sS_Status = new SS_Stt(scode(ST_ERROR), smsg(ST_ERROR),
                                            ecode(ER_PROCESS),emsg(ER_PROCESS) + e.getMessage());
             sSRes_Base.setStatus(sS_Status);
            return sSRes_Base;
        } finally {
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SS_Prc.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection!=null) try {connection.close();}catch (Exception ignore) {}
        }
        sSRes_Base.updateStatus(WALLETUPDATE[CLR]);
        return sSRes_Base;
    }
    
    public SSRes_CustomerInfo getCustomerInfo(SSReq_Info sSReq_CustomerInfo) {
        
        CallableStatement callableStatement = null;
        Connection        connection        = null;
        SSRes_CustomerInfo sSRes_CustomerInfo= new SSRes_CustomerInfo();
        SS_Stt         sS_Status;
        SS_Chk sS_Chk = new SS_Chk();        
        
        if (!sS_Chk.checkDataCriteria(sSReq_CustomerInfo.getCriteria()))
        {
            sS_Status = new SS_Stt(scode(ST_REJECTED), smsg(ST_REJECTED),
                                            sS_Chk.code(), sS_Chk.msg());
            sSRes_CustomerInfo.setStatus(sS_Status);
            return sSRes_CustomerInfo;
        }
        
        try {
            if(isLiveCtx()){
                connection= SW_INT.getDBConnection();

                callableStatement = connection.prepareCall(CUSTOMERINFO[SQL]);
                sSReq_CustomerInfo.setSqlType(CUSTOMERINFO[REQ]);
                sSRes_CustomerInfo.setSqlType(CUSTOMERINFO[RES]);
                callableStatement.setObject(1, sSReq_CustomerInfo);
                callableStatement.setObject(2, sSRes_CustomerInfo);
                callableStatement.registerOutParameter( 2, OracleTypes.STRUCT, CUSTOMERINFO[RES]);
                callableStatement.executeUpdate();

                Map<String,Class<?>> typeMap = connection.getTypeMap();
                typeMap.put( CUSTOMERINFO[RES], SSRes_CustomerInfo.class );

                sSRes_CustomerInfo = (SSRes_CustomerInfo) callableStatement.getObject(2);
            }else{

                sSRes_CustomerInfo.setCustomer(getCustomerSample());
                sS_Status  = new SS_Stt(scode(ST_APPROVED), smsg(ST_APPROVED),
                                            ecode(ER_CLEAN), emsg(ER_CLEAN));
                sSRes_CustomerInfo.setStatus(sS_Status);
            }
        }catch (Exception e) {     
            sS_Status = new SS_Stt(scode(ST_ERROR), smsg(ST_ERROR),
                                            ecode(ER_PROCESS),emsg(ER_PROCESS) + e.getMessage());
             sSRes_CustomerInfo.setStatus(sS_Status);
            return sSRes_CustomerInfo;
        } finally {
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SS_Prc.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection!=null) try {connection.close();}catch (Exception ignore) {}
        }
        sSRes_CustomerInfo.updateStatus(CUSTOMERINFO[CLR]);
        return sSRes_CustomerInfo;
    }
    
    public SSRes_MerchantInfo getMerchantInfo(SSReq_Info sSReq_MerchantInfo) {
        
        CallableStatement callableStatement     = null;
        Connection        connection            = null;
        SSRes_MerchantInfo sSRes_MerchantInfo   = new SSRes_MerchantInfo();
        SS_Stt         sS_Status;
        SS_Chk sS_Chk = new SS_Chk();        
        
        if (!sS_Chk.checkDataCriteria(sSReq_MerchantInfo.getCriteria()))
        {
            sS_Status = new SS_Stt(scode(ST_REJECTED), smsg(ST_REJECTED),
                                            sS_Chk.code(), sS_Chk.msg());
            sSRes_MerchantInfo.setStatus(sS_Status);
            return sSRes_MerchantInfo;
        }
        
        try {
            if(isLiveCtx()){
                connection= SW_INT.getDBConnection();

                callableStatement = connection.prepareCall(MERCHANTINFO[SQL]);
                sSReq_MerchantInfo.setSqlType(MERCHANTINFO[REQ]);
                sSRes_MerchantInfo.setSqlType(MERCHANTINFO[RES]);
                callableStatement.setObject(1, sSReq_MerchantInfo);
                callableStatement.setObject(2, sSRes_MerchantInfo);
                callableStatement.registerOutParameter( 2, OracleTypes.STRUCT, MERCHANTINFO[RES]);
                callableStatement.executeUpdate();

                Map<String,Class<?>> typeMap = connection.getTypeMap();
                typeMap.put( MERCHANTINFO[RES], SSRes_MerchantInfo.class );

                sSRes_MerchantInfo = (SSRes_MerchantInfo) callableStatement.getObject(2);

            }else{
                sSRes_MerchantInfo.setMerchant(getMerchantSample());
                sS_Status  = new SS_Stt(scode(ST_APPROVED), smsg(ST_APPROVED),
                                            ecode(ER_CLEAN), emsg(ER_CLEAN));
                sSRes_MerchantInfo.setStatus(sS_Status);
            }
                
        }catch (Exception e) {     
            sS_Status = new SS_Stt(scode(ST_ERROR), smsg(ST_ERROR),
                                            ecode(ER_PROCESS),emsg(ER_PROCESS) + e.getMessage());
             sSRes_MerchantInfo.setStatus(sS_Status);
            return sSRes_MerchantInfo;
        } finally {
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SS_Prc.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection!=null) try {connection.close();}catch (Exception ignore) {}
        }
        sSRes_MerchantInfo.updateStatus(MERCHANTINFO[CLR]);
        return sSRes_MerchantInfo;
    }
    
    public SSRes_Wallets getWalletInfo(SSReq_Wallet sSReq_Wallet) {
        
        CallableStatement callableStatement = null;
        Connection        connection        = null;
        SSRes_Wallets sSRes_Wallets         = new SSRes_Wallets();
        SS_Stt         sS_Status;
        SS_Chk sS_Chk = new SS_Chk();        
        
        if (!sS_Chk.checkDataInitiator(sSReq_Wallet.getInitiator(),false))
        {
            sS_Status = new SS_Stt(scode(ST_REJECTED), smsg(ST_REJECTED),
                                            sS_Chk.code(), sS_Chk.msg());
            sSRes_Wallets.setStatus(sS_Status);
            return sSRes_Wallets;
        }
        
        try {
            if(isLiveCtx()){
                connection= SW_INT.getDBConnection();

                callableStatement = connection.prepareCall(WALLETINFO[SQL]);
                sSReq_Wallet.setSqlType(WALLETINFO[REQ]);
                sSRes_Wallets.setSqlType(WALLETINFO[RES]);
                callableStatement.setObject(1, sSReq_Wallet);
                callableStatement.setObject(2, sSRes_Wallets);
                callableStatement.registerOutParameter( 2, OracleTypes.STRUCT, WALLETINFO[RES]);
                callableStatement.executeUpdate();

                Map<String,Class<?>> typeMap = connection.getTypeMap();
                typeMap.put( WALLETINFO[RES], SSRes_Wallets.class );

                sSRes_Wallets = (SSRes_Wallets) callableStatement.getObject(2);
                System.out.println("com.ssw.bnk.swint.jdbc.in.SS_Prc.getWalletInfo()");
            }else{

                sSRes_Wallets.setWallet(getWalletSample());
                sS_Status  = new SS_Stt(scode(ST_APPROVED), smsg(ST_APPROVED),
                                            ecode(ER_CLEAN), emsg(ER_CLEAN));
                sSRes_Wallets.setStatus(sS_Status);
            }
        }catch (Exception e) {     
            sS_Status = new SS_Stt(scode(ST_ERROR), smsg(ST_ERROR),
                                            ecode(ER_PROCESS),emsg(ER_PROCESS) + e.getMessage());
             sSRes_Wallets.setStatus(sS_Status);
            return sSRes_Wallets;
        } finally {
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SS_Prc.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection!=null) try {connection.close();}catch (Exception ignore) {}
        }
        sSRes_Wallets.updateStatus(WALLETINFO[CLR]);
        return sSRes_Wallets;
    }

    public SSRes_Balance getBalance(SSReq_Balance sSReq_Balance) {
        
        CallableStatement callableStatement = null;
        Connection        connection        = null;
        SSRes_Balance sSRes_Balance = new SSRes_Balance();
        SS_Chk sS_Chk = new SS_Chk();
        SS_Stt         sS_Status;      
        
        if (!sS_Chk.checkDataInitiator(sSReq_Balance.getInitiator(),true))
        {
            sS_Status = new SS_Stt(scode(ST_REJECTED), smsg(ST_REJECTED),
                                            sS_Chk.code(), sS_Chk.msg());
            sSRes_Balance.setStatus(sS_Status);
            return sSRes_Balance;
        }
                
        try {
            if(isLiveCtx()){
                connection= SW_INT.getDBConnection();

                callableStatement = connection.prepareCall(BALANCE[SQL]);
                sSReq_Balance.setSqlType(BALANCE[REQ]);
                sSRes_Balance.setSqlType(BALANCE[RES]);
                callableStatement.setObject(1, sSReq_Balance);
                callableStatement.setObject(2, sSRes_Balance);
                callableStatement.registerOutParameter( 2, OracleTypes.STRUCT, BALANCE[RES]);
                callableStatement.executeUpdate();

                Map<String,Class<?>> typeMap = connection.getTypeMap();
                typeMap.put( BALANCE[RES], SSRes_Balance.class );

                sSRes_Balance = (SSRes_Balance) callableStatement.getObject(2);

            }else{
                sSRes_Balance.setBalance(new SSEnt_Balance("3413.14", "LYD"));
                sS_Status         = new SS_Stt(scode(ST_APPROVED), smsg(ST_APPROVED),
                                                            ecode(ER_CLEAN), emsg(ER_CLEAN));
                sSRes_Balance.setStatus(sS_Status);
            }
        }catch (Exception e) {     
            sS_Status = new SS_Stt(scode(ST_ERROR), smsg(ST_ERROR),
                                            ecode(ER_PROCESS),emsg(ER_PROCESS) + e.getMessage());
             sSRes_Balance.setStatus(sS_Status);
            return sSRes_Balance;
        } finally {
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SS_Prc.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection!=null) try {connection.close();}catch (Exception ignore) {}
        }
        sSRes_Balance.updateStatus(BALANCE[CLR]);
        return sSRes_Balance;
    }
    
    public SSRes_Transaction transaction(SSReq_Transaction sSReq_Transaction, TRANSACTION_TYPE transaction_type) {
        
        CallableStatement callableStatement = null;
        Connection        connection        = null;
        SSRes_Transaction sSRes_Transaction = new SSRes_Transaction();
        SS_Stt         sS_Status;
        SS_Chk sS_Chk = new SS_Chk();   
        
        
        if (!sS_Chk.checkDataInitiator(sSReq_Transaction.getInitiator(),true)  || 
                !sS_Chk.checkDataRequest(sSReq_Transaction.getRequest(),transaction_type))
        {
            sS_Status = new SS_Stt(scode(ST_REJECTED), smsg(ST_REJECTED),
                                            sS_Chk.code(), sS_Chk.msg());
            sSRes_Transaction.setStatus(sS_Status);
            return sSRes_Transaction;
        }

        try {
            if(isLiveCtx()){
                connection= SW_INT.getDBConnection();
                sSReq_Transaction.getRequest().setTrxtype(transaction_type.getValue());
                String sqlRes = null;
                switch (transaction_type){
                     case ETOPUP:
                         sqlRes = TOPUP[RES];
                         SSReq_TopUp sSReq_TopUp = new SSReq_TopUp(sSReq_Transaction);
                         callableStatement = connection.prepareCall(TOPUP[SQL]);
                         sSReq_TopUp.setSqlType(TOPUP[REQ]);
                         callableStatement.setObject(1, sSReq_TopUp);
                         break;
                     case BILLPAYMENT:
                         String label = sSReq_Transaction.getRequest().getLabel();
                         String billId = sSReq_Transaction.getRequest().getAdddata();
                         String newLabel;
                         try{
                             newLabel = label.substring(0, 50-billId.length());
                         }
                         catch(Exception e){
                             newLabel = label;
                         }
                         sSReq_Transaction.getRequest().setLabel(newLabel+" N°:"+billId);
                     default:
                         if(transaction_type == TRANSACTION_TYPE.VOUCHER){
                             sSReq_Transaction.getRequest().setAmount(sSReq_Transaction.getRequest().getAdddata());
                         }
                         sqlRes = PAYMENT[RES];
                         SSReq_Payment sSReq_Payment = new SSReq_Payment(sSReq_Transaction);
                         callableStatement = connection.prepareCall(PAYMENT[SQL]);
                         sSReq_Payment.setSqlType(PAYMENT[REQ]);
                         callableStatement.setObject(1, sSReq_Payment);
                         break;
                 }
                
                sSRes_Transaction.setSqlType(sqlRes);
                callableStatement.setObject(2, sSRes_Transaction);
                callableStatement.registerOutParameter( 2, OracleTypes.STRUCT, sqlRes);
                callableStatement.executeUpdate();

                Map<String,Class<?>> typeMap = connection.getTypeMap();
                typeMap.put( sqlRes, SSRes_Transaction.class );

                sSRes_Transaction = (SSRes_Transaction) callableStatement.getObject(2);                   
        

            }else{

                sSRes_Transaction.setAuthorization(new SSEnt_Authorization("PAEC7D", "919912634677","18/07/19 12:18:32"));
                sS_Status         = new SS_Stt(scode(ST_APPROVED), smsg(ST_APPROVED),
                                                            ecode(ER_CLEAN), emsg(ER_CLEAN));
                sSRes_Transaction.setStatus(sS_Status);
                        
            }
        }catch (Exception e) {     
            sS_Status = new SS_Stt(scode(ST_ERROR), smsg(ST_ERROR),
                                            ecode(ER_PROCESS),emsg(ER_PROCESS) + e.getMessage());
             sSRes_Transaction.setStatus(sS_Status);
            return sSRes_Transaction;
        } finally {
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SS_Prc.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection!=null) try {connection.close();}catch (Exception ignore) {}
        }

        sSRes_Transaction.updateStatus(" <"+transaction_type.name()+"> ");
        return sSRes_Transaction;        
        

    }
   
    
     // EKH ADD THIS FUNCTION FOR PURSHASE VOUCHER SERVICE
    public SSRes_PurshaseVoucher PurchaseVoucher(SSReq_Transaction sSReq_Transaction, TRANSACTION_TYPE transaction_type) {
        
        CallableStatement callableStatement = null;
        Connection        connection        = null;
        SSRes_PurshaseVoucher sSRes_PurshaseVoucher = new SSRes_PurshaseVoucher();
        SS_Stt         sS_Status;
        SS_Chk sS_Chk = new SS_Chk();   
        
        
        if (!sS_Chk.checkDataInitiator(sSReq_Transaction.getInitiator(),true)  || 
                !sS_Chk.checkDataRequest(sSReq_Transaction.getRequest(),transaction_type))
        {
            sS_Status = new SS_Stt(scode(ST_REJECTED), smsg(ST_REJECTED),
                                            sS_Chk.code(), sS_Chk.msg());
            sSRes_PurshaseVoucher.setStatus(sS_Status);
            return sSRes_PurshaseVoucher;
        }

        try {
            if(isLiveCtx()){
                connection= SW_INT.getDBConnection();
                sSReq_Transaction.getRequest().setTrxtype(transaction_type.getValue());
                String sqlRes = null;
                switch (transaction_type){
                     case ETOPUP:
                         sqlRes = TOPUP[RES];
                         SSReq_TopUp sSReq_TopUp = new SSReq_TopUp(sSReq_Transaction);
                         callableStatement = connection.prepareCall(TOPUP[SQL]);
                         sSReq_TopUp.setSqlType(TOPUP[REQ]);
                         callableStatement.setObject(1, sSReq_TopUp);
                         break;
                     case BILLPAYMENT:
                         String label = sSReq_Transaction.getRequest().getLabel();
                         String billId = sSReq_Transaction.getRequest().getAdddata();
                         String newLabel;
                         try{
                             newLabel = label.substring(0, 50-billId.length());
                         }
                         catch(Exception e){
                             newLabel = label;
                         }
                         sSReq_Transaction.getRequest().setLabel(newLabel+" N°:"+billId);
                         break;
                         // EKH ADD VOUCHER FOR SELECT SYSTEM */
                     case VOUCHERSS:
                         sSReq_Transaction.getRequest().setAdddata(sSReq_Transaction.getRequest().getAdddata());
                         sqlRes = VOUCHER_SS[RES];
                         SSReq_Payment sSReq_VoucherSS = new SSReq_Payment(sSReq_Transaction);
                         callableStatement = connection.prepareCall(VOUCHER_SS[SQL]);
                         sSReq_VoucherSS.setSqlType(VOUCHER_SS[REQ]);
                         callableStatement.setObject(1, sSReq_VoucherSS);
                         break;
                         // EKH END
                     default:
                         if(transaction_type == TRANSACTION_TYPE.VOUCHER){
                             sSReq_Transaction.getRequest().setAmount(sSReq_Transaction.getRequest().getAdddata());
                         }
                         sqlRes = PAYMENT[RES];
                         SSReq_Payment sSReq_Payment = new SSReq_Payment(sSReq_Transaction);
                         callableStatement = connection.prepareCall(PAYMENT[SQL]);
                         sSReq_Payment.setSqlType(PAYMENT[REQ]);
                         callableStatement.setObject(1, sSReq_Payment);
                         break;
                 }
                
                sSRes_PurshaseVoucher.setSqlType(sqlRes);
                callableStatement.setObject(2, sSRes_PurshaseVoucher);
                callableStatement.registerOutParameter( 2, OracleTypes.STRUCT, sqlRes);
                callableStatement.executeUpdate();

                Map<String,Class<?>> typeMap = connection.getTypeMap();
                typeMap.put( sqlRes, SSRes_PurshaseVoucher.class );

                sSRes_PurshaseVoucher = (SSRes_PurshaseVoucher) callableStatement.getObject(2);          

            }else{
                sSRes_PurshaseVoucher.setAuthorization(new SSEnt_Authorization("PAEC7D", "919912634677","18/07/19 12:18:32"));
                sSRes_PurshaseVoucher.setVoucherDT(new SSEnt_VoucherData("11154","54654EEF654","01-03-2020",""));
                sS_Status         = new SS_Stt(scode(ST_APPROVED), smsg(ST_APPROVED),
                                                            ecode(ER_CLEAN), emsg(ER_CLEAN));
                sSRes_PurshaseVoucher.setStatus(sS_Status);
                        
            }
        }catch (Exception e) {     
            sS_Status = new SS_Stt(scode(ST_ERROR), smsg(ST_ERROR),
                                            ecode(ER_PROCESS),emsg(ER_PROCESS) + e.getMessage());
             sSRes_PurshaseVoucher.setStatus(sS_Status);
            return sSRes_PurshaseVoucher;
        } finally {
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SS_Prc.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection!=null) try {connection.close();}catch (Exception ignore) {}
        }
        
        sSRes_PurshaseVoucher.updateStatus(" <"+transaction_type.name()+"> ");
        return sSRes_PurshaseVoucher;
    }
    
    public SSRes_HistoryVoucher getHistoryVoucher(SSReq_HistoryVoucher sSReq_HistoryVoucher) {
        
        CallableStatement callableStatement = null;
        Connection        connection        = null;
        SSRes_HistoryVoucher sSRes_HistoryVoucher = new SSRes_HistoryVoucher();
        SS_Stt         sS_Status;
        SS_Chk sS_Chk = new SS_Chk();
        
        if (!sS_Chk.checkDataInitiator(sSReq_HistoryVoucher.getInitiator(),true) || !sS_Chk.checkDataFilterHistVoucher(sSReq_HistoryVoucher.getFilter()))
        {
            sS_Status = new SS_Stt(scode(ST_REJECTED), smsg(ST_REJECTED),
                                            sS_Chk.code(), sS_Chk.msg());
            sSRes_HistoryVoucher.setStatus(sS_Status);
            return sSRes_HistoryVoucher;
        }
            try {
            if(isLiveCtx()){
                connection= SW_INT.getDBConnection();

                callableStatement = connection.prepareCall(HISTORYVOUCHER[SQL]);
                sSReq_HistoryVoucher.setSqlType(HISTORYVOUCHER[REQ]);
                sSRes_HistoryVoucher.setSqlType(HISTORYVOUCHER[RES]);
                callableStatement.setObject(1, sSReq_HistoryVoucher);
                callableStatement.setObject(2, sSRes_HistoryVoucher);
                callableStatement.registerOutParameter( 2, OracleTypes.STRUCT, HISTORYVOUCHER[RES]);
                callableStatement.executeUpdate();

                Map<String,Class<?>> typeMap = connection.getTypeMap();
                typeMap.put( HISTORYVOUCHER[RES], SSRes_HistoryVoucher.class );

                sSRes_HistoryVoucher = (SSRes_HistoryVoucher) callableStatement.getObject(2);
            }else{
                SSEnt_VoucherTranStruct[] sSEnt_VoucherTranStruct = new SSEnt_VoucherTranStruct[2];
                sSEnt_VoucherTranStruct[0]=new SSEnt_VoucherTranStruct("20", "01/06/19", "39", "45454545645");
                sSEnt_VoucherTranStruct[1]=new SSEnt_VoucherTranStruct("10", "01/06/19", "29", "58658564567");
                sSRes_HistoryVoucher.setTransactions(sSEnt_VoucherTranStruct);
                sS_Status         = new SS_Stt(scode(ST_APPROVED), smsg(ST_APPROVED), ecode(ER_CLEAN), emsg(ER_CLEAN));
                sSRes_HistoryVoucher.setStatus(sS_Status);
            }
        }catch (Exception e) {     
            sS_Status = new SS_Stt(scode(ST_ERROR), smsg(ST_ERROR),
                                            ecode(ER_PROCESS),emsg(ER_PROCESS) + e.getMessage());
             sSRes_HistoryVoucher.setStatus(sS_Status);
            return sSRes_HistoryVoucher;
        } finally {
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SS_Prc.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection!=null) try {connection.close();}catch (Exception ignore) {}
        }
        sSRes_HistoryVoucher.updateStatus(HISTORYVOUCHER[CLR]);
        return sSRes_HistoryVoucher;
    }
    
    public SSRes_Moneytransfer moneytransfer(SSReq_Moneytransfer sSReq_Moneytransfer) {
        
        CallableStatement callableStatement = null;
        Connection        connection        = null;
        SSRes_Moneytransfer sSRes_Moneytransfer = new SSRes_Moneytransfer();
        SS_Stt         sS_Status;
        SS_Chk sS_Chk = new SS_Chk();
        
        if (!sS_Chk.checkDataMoneyTransfer(sSReq_Moneytransfer.getMoneytransfer(),true))
        {
            sS_Status = new SS_Stt(scode(ST_REJECTED), smsg(ST_REJECTED),
                                            sS_Chk.code(), sS_Chk.msg());
            sSRes_Moneytransfer.setStatus(sS_Status);
            return sSRes_Moneytransfer;
        }
            try {
            if(isLiveCtx()){
                connection= SW_INT.getDBConnection();
                callableStatement = connection.prepareCall(MONEYTRANSFER[SQL]);
                sSReq_Moneytransfer.setSqlType(MONEYTRANSFER[REQ]);
                sSRes_Moneytransfer.setSqlType(MONEYTRANSFER[RES]);
                callableStatement.setObject(1, sSReq_Moneytransfer);
                callableStatement.setObject(2, sSRes_Moneytransfer);
                callableStatement.registerOutParameter( 2, OracleTypes.STRUCT, MONEYTRANSFER[RES]);
                callableStatement.executeUpdate();

                Map<String,Class<?>> typeMap = connection.getTypeMap();
                typeMap.put( MONEYTRANSFER[RES], SSRes_Moneytransfer.class );

                sSRes_Moneytransfer = (SSRes_Moneytransfer) callableStatement.getObject(2);
            }else{
                sS_Status         = new SS_Stt(scode(ST_APPROVED), smsg(ST_APPROVED), ecode(ER_CLEAN), emsg(ER_CLEAN));
                sSRes_Moneytransfer.setStatus(sS_Status);
            }
        }catch (Exception e) {     
            sS_Status = new SS_Stt(scode(ST_ERROR), smsg(ST_ERROR),
                                            ecode(ER_PROCESS),emsg(ER_PROCESS) + e.getMessage());
             sSRes_Moneytransfer.setStatus(sS_Status);
            return sSRes_Moneytransfer;
        } finally {
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SS_Prc.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection!=null) try {connection.close();}catch (Exception ignore) {}
        }
        sSRes_Moneytransfer.updateStatus(MONEYTRANSFER[CLR]);
        return sSRes_Moneytransfer;
    }
    
    // EKH END
    
    
    

    public SSRes_TransactionsList getTransactions(SSReq_TransactionsList sSReq_TransactionsList) {
        
        CallableStatement callableStatement = null;
        Connection        connection        = null;
        SSRes_TransactionsList sSRes_TransactionsList = new SSRes_TransactionsList();
        SS_Stt         sS_Status;
        SS_Chk sS_Chk = new SS_Chk();
      
        
        if (!sS_Chk.checkDataInitiator(sSReq_TransactionsList.getInitiator(),true) || 
                !sS_Chk.checkDataFilter(sSReq_TransactionsList.getFilter()))
        {
            sS_Status = new SS_Stt(scode(ST_REJECTED), smsg(ST_REJECTED),
                                            sS_Chk.code(), sS_Chk.msg());
            sSRes_TransactionsList.setStatus(sS_Status);
            return sSRes_TransactionsList;
        }
                
        try {
            if(isLiveCtx()){
     
                connection= SW_INT.getDBConnection();

                callableStatement = connection.prepareCall(TRANSACTION[SQL]);
                sSReq_TransactionsList.setSqlType(TRANSACTION[REQ]);
                sSRes_TransactionsList.setSqlType(TRANSACTION[RES]);
                callableStatement.setObject(1, sSReq_TransactionsList);
                callableStatement.setObject(2, sSRes_TransactionsList);
                callableStatement.registerOutParameter( 2, OracleTypes.STRUCT, TRANSACTION[RES]);
                callableStatement.executeUpdate();

                Map<String,Class<?>> typeMap = connection.getTypeMap();
                typeMap.put( TRANSACTION[RES], SSRes_TransactionsList.class );

                sSRes_TransactionsList = (SSRes_TransactionsList) callableStatement.getObject(2);
            }else{
                SSEnt_Trasansaction[] sSEnt_Trasansactions = new SSEnt_Trasansaction[2];
                sSEnt_Trasansactions[0]=new SSEnt_Trasansaction("189787956421", "01/06/19", "WALLET TRANSFER", "20000", "D", "WALLET");
                sSEnt_Trasansactions[1]=new SSEnt_Trasansaction("874582178654", "01/06/19", "with ATM", "5000", "D", "CARD");
                sSRes_TransactionsList.setTransactions(sSEnt_Trasansactions);
                sS_Status         = new SS_Stt(scode(ST_APPROVED), smsg(ST_APPROVED),
                                                ecode(ER_CLEAN), emsg(ER_CLEAN));
                sSRes_TransactionsList.setStatus(sS_Status);
            }
        }catch (Exception e) {     
            sS_Status = new SS_Stt(scode(ST_ERROR), smsg(ST_ERROR),
                                            ecode(ER_PROCESS),emsg(ER_PROCESS) + e.getMessage());
             sSRes_TransactionsList.setStatus(sS_Status);
            return sSRes_TransactionsList;
        } finally {
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SS_Prc.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection!=null) try {connection.close();}catch (Exception ignore) {}
        }
        sSRes_TransactionsList.updateStatus(TRANSACTION[CLR]);
        return sSRes_TransactionsList;
    }
    
    public SSRes_Branchs getBranchs(SSReq_HeadBank sSReq_HeadBank) {
        
        CallableStatement callableStatement = null;
        Connection        connection        = null;
        SSRes_Branchs     sSRes_Branchs     = new SSRes_Branchs();
        SS_Stt         sS_Status;
        SS_Chk sS_Chk = new SS_Chk();
        
        if (!sS_Chk.checkDataBank(sSReq_HeadBank.getBankcode()))
        {
            sS_Status = new SS_Stt(scode(ST_REJECTED), smsg(ST_REJECTED),
                                            sS_Chk.code(), sS_Chk.msg());
            sSRes_Branchs.setStatus(sS_Status);
            return sSRes_Branchs;
        }
        
        try {
            if(isLiveCtx()){
                connection= SW_INT.getDBConnection();

                callableStatement = connection.prepareCall(BRANCH[SQL]);
                sSReq_HeadBank.setSqlType(BRANCH[REQ]);
                sSRes_Branchs.setSqlType(BRANCH[RES]);
                callableStatement.setObject(1, sSReq_HeadBank);
                callableStatement.setObject(2, sSRes_Branchs);
                callableStatement.registerOutParameter( 2, OracleTypes.STRUCT, BRANCH[RES]);
                callableStatement.executeUpdate();

                Map<String,Class<?>> typeMap = connection.getTypeMap();
                typeMap.put( BRANCH[RES], SSRes_Branchs.class );

                sSRes_Branchs = (SSRes_Branchs) callableStatement.getObject(2);
            }else{
                SSEnt_Branchs[] sSEnt_Branchses = new SSEnt_Branchs[2];
                sSEnt_Branchses[0] =  new SSEnt_Branchs("12461", "social branch");
                sSEnt_Branchses[1] =  new SSEnt_Branchs("11301", "MSRT Main Branch");
                sSRes_Branchs.setBranchs(sSEnt_Branchses);
                sS_Status         = new SS_Stt(scode(ST_APPROVED), smsg(ST_APPROVED),
                                                ecode(ER_CLEAN), emsg(ER_CLEAN));
                sSRes_Branchs.setStatus(sS_Status);
            }
        }catch (Exception e) {     
            sS_Status = new SS_Stt(scode(ST_ERROR), smsg(ST_ERROR),
                                            ecode(ER_PROCESS),emsg(ER_PROCESS) + e.getMessage());
             sSRes_Branchs.setStatus(sS_Status);
            return sSRes_Branchs;
        } finally {
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SS_Prc.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection!=null) try {connection.close();}catch (Exception ignore) {}
        }
        sSRes_Branchs.updateStatus(BRANCH[CLR]);
        return sSRes_Branchs;
    }
        
    public SSRes_Mno getMno(SSReq_Base sSReq_Base) {
        
        CallableStatement callableStatement = null;
        Connection        connection        = null;
        SSRes_Mno sSRes_Mno             = new SSRes_Mno();
        SS_Stt         sS_Status;
        
        try {
            if(isLiveCtx()){
                connection= SW_INT.getDBConnection();

                callableStatement = connection.prepareCall(MNO[SQL]);
                sSReq_Base.setSqlType(MNO[REQ]);
                sSRes_Mno.setSqlType(MNO[RES]);
                callableStatement.setObject(1, sSReq_Base);
                callableStatement.setObject(2, sSRes_Mno);
                callableStatement.registerOutParameter( 2, OracleTypes.STRUCT, MNO[RES]);
                callableStatement.executeUpdate();

                Map<String,Class<?>> typeMap = connection.getTypeMap();
                typeMap.put( MNO[RES], SSRes_Mno.class );

                sSRes_Mno = (SSRes_Mno) callableStatement.getObject(2);
            }else{
                SSEnt_Denomination[] sSEnt_Denominations = new SSEnt_Denomination[1];
                SSEnt_Denomination[] sSEnt_Denominations1 = new SSEnt_Denomination[1];
                SSEnt_Denomination[] sSEnt_Denominations2 = new SSEnt_Denomination[1];
                sSEnt_Denominations[0] =  new SSEnt_Denomination("4401", "3");
                sSEnt_Denominations1[0] =  new SSEnt_Denomination("11641", "100");
                sSEnt_Denominations2[0] =  new SSEnt_Denomination("11642", "10");
                SSEnt_Service[] sSEnt_Services = new SSEnt_Service[1];
                SSEnt_Service[] sSEnt_Services1 = new SSEnt_Service[1];
                SSEnt_Service[] sSEnt_Services2 = new SSEnt_Service[1];
                sSEnt_Services[0] =  new SSEnt_Service("2481", "Mobile TopUp", sSEnt_Denominations);
                sSEnt_Services1[0] =  new SSEnt_Service("2482", "Mobile TopUp", sSEnt_Denominations1);
                sSEnt_Services2[0] =  new SSEnt_Service("2483", "Mobile TopUp", sSEnt_Denominations2);
                SSEnt_Mno[] sSEnt_Mno = new SSEnt_Mno[3];
                sSEnt_Mno[0] =  new SSEnt_Mno("3021", "Libyana","32",sSEnt_Services);
                sSEnt_Mno[1] =  new SSEnt_Mno("005", "AlMadar","31",sSEnt_Services1);
                sSEnt_Mno[2] =  new SSEnt_Mno("003", "LTT","33",sSEnt_Services2);
                sSRes_Mno.setMno(sSEnt_Mno);
                sS_Status         = new SS_Stt(scode(ST_APPROVED), smsg(ST_APPROVED),
                                                ecode(ER_CLEAN), emsg(ER_CLEAN));
                sSRes_Mno.setStatus(sS_Status);
            }
        }catch (Exception e) {     
            sS_Status = new SS_Stt(scode(ST_ERROR), smsg(ST_ERROR),
                                            ecode(ER_PROCESS),emsg(ER_PROCESS) + e.getMessage());
             sSRes_Mno.setStatus(sS_Status);
            return sSRes_Mno;
        } finally {
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SS_Prc.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection!=null) try {connection.close();}catch (Exception ignore) {}
        }
        sSRes_Mno.updateStatus(MNO[CLR]);
        return sSRes_Mno;
    }
    
    public SSRes_Banks getBanks(SSReq_Base sSReq_Base) {
        
        CallableStatement callableStatement = null;
        Connection        connection        = null;
        SSRes_Banks sSRes_Banks             = new SSRes_Banks();
        SS_Stt         sS_Status;
        
        try {
            if(isLiveCtx()){
                connection= SW_INT.getDBConnection();

                callableStatement = connection.prepareCall(BANK[SQL]);
                sSReq_Base.setSqlType(BANK[REQ]);
                sSRes_Banks.setSqlType(BANK[RES]);
                callableStatement.setObject(1, sSReq_Base);
                callableStatement.setObject(2, sSRes_Banks);
                callableStatement.registerOutParameter( 2, OracleTypes.STRUCT, BANK[RES]);
                callableStatement.executeUpdate();

                Map<String,Class<?>> typeMap = connection.getTypeMap();
                typeMap.put( BANK[RES], SSRes_Banks.class );

                sSRes_Banks = (SSRes_Banks) callableStatement.getObject(2);
            }else{
                SSEnt_Banks[] sSEnt_Banks = new SSEnt_Banks[3];
                sSEnt_Banks[0] =  new SSEnt_Banks("8671", "MASARAT");
                sSEnt_Banks[1] =  new SSEnt_Banks("7601", "AL WAHDA");
                sSEnt_Banks[2] =  new SSEnt_Banks("7621", "SAHARA BANK");
                sSRes_Banks.setBanks(sSEnt_Banks);
                sS_Status         = new SS_Stt(scode(ST_APPROVED), smsg(ST_APPROVED),
                                                ecode(ER_CLEAN), emsg(ER_CLEAN));
                sSRes_Banks.setStatus(sS_Status);
            }
        }catch (Exception e) {     
            sS_Status = new SS_Stt(scode(ST_ERROR), smsg(ST_ERROR),
                                            ecode(ER_PROCESS),emsg(ER_PROCESS) + e.getMessage());
             sSRes_Banks.setStatus(sS_Status);
            return sSRes_Banks;
        } finally {
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SS_Prc.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection!=null) try {connection.close();}catch (Exception ignore) {}
        }
        sSRes_Banks.updateStatus(BANK[CLR]);
        return sSRes_Banks;
    }
        
    public SSRes_MplList getMpl(SSReq_HeadBank sSReq_HeadBank)  {
        
        CallableStatement callableStatement = null;
        Connection        connection        = null;
        SSRes_MplList sSRes_MplList = new SSRes_MplList();
        SS_Stt         sS_Status;
        SS_Chk sS_Chk = new SS_Chk();        
        
        if (!sS_Chk.checkDataBank(sSReq_HeadBank.getBankcode()))
        {
            sS_Status = new SS_Stt(scode(ST_REJECTED), smsg(ST_REJECTED),
                                            sS_Chk.code(), sS_Chk.msg());
            sSRes_MplList.setStatus(sS_Status);
            return sSRes_MplList;
        }
        
        try {
            if(isLiveCtx()){
                connection= SW_INT.getDBConnection();

                callableStatement = connection.prepareCall(MPL[SQL]);
                sSReq_HeadBank.setSqlType(MPL[REQ]);
                sSRes_MplList.setSqlType(MPL[RES]);
                callableStatement.setObject(1, sSReq_HeadBank);
                callableStatement.setObject(2, sSRes_MplList);
                callableStatement.registerOutParameter( 2, OracleTypes.STRUCT, MPL[RES]);
                callableStatement.executeUpdate();

                Map<String,Class<?>> typeMap = connection.getTypeMap();
                typeMap.put( MPL[RES], SSRes_MplList.class );

                sSRes_MplList = (SSRes_MplList) callableStatement.getObject(2);
            }else{
                SSEnt_MPL[] sSEnt_MPLs = new SSEnt_MPL[3];
                SSEnt_Activities[] sSEnt_Activitieses = new SSEnt_Activities[1];
                SSEnt_PaymentMode[] sSEnt_PaymentMode = new SSEnt_PaymentMode[2];
                sSEnt_Activitieses[0]= new SSEnt_Activities("35","5722","HOUSEHOLD APPLIANCE STORE");
                sSEnt_PaymentMode[0]= new SSEnt_PaymentMode("1","CASH","50","Y");
                sSEnt_PaymentMode[1]= new SSEnt_PaymentMode("2","WIRE TRANSFER","50","N");
                sSEnt_MPLs[0] =  new SSEnt_MPL("16381", "Settlement 50%",sSEnt_Activitieses,sSEnt_PaymentMode);

                sSEnt_PaymentMode = new SSEnt_PaymentMode[1];
                sSEnt_PaymentMode[0]= new SSEnt_PaymentMode("4","CORPORATE CARD TOP-UP","100","Y");
                sSEnt_MPLs[1] =  new SSEnt_MPL("15981", "Grocery",sSEnt_Activitieses,sSEnt_PaymentMode);

                sSEnt_Activitieses[0]= new SSEnt_Activities("32","7542","CAR WASHES");
                sSEnt_MPLs[2] =  new SSEnt_MPL("10921", "VIRTUAL MERCHANT",sSEnt_Activitieses,null);


                sSRes_MplList.setMpl(sSEnt_MPLs);
                sS_Status         = new SS_Stt(scode(ST_APPROVED), smsg(ST_APPROVED),
                                                ecode(ER_CLEAN), emsg(ER_CLEAN));
                sSRes_MplList.setStatus(sS_Status);
            }
        }catch (Exception e) {     
            sS_Status = new SS_Stt(scode(ST_ERROR), smsg(ST_ERROR),
                                            ecode(ER_PROCESS),emsg(ER_PROCESS) + e.getMessage());
             sSRes_MplList.setStatus(sS_Status);
            return sSRes_MplList;
        } finally {
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SS_Prc.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection!=null) try {connection.close();}catch (Exception ignore) {}
        }
        sSRes_MplList.updateStatus(MPL[CLR]);
        return sSRes_MplList;
    }

        
    public SSRes_AplList getApl(SSReq_HeadBank sSReq_HeadBank) {
        
        CallableStatement callableStatement = null;
        Connection      connection        = null;
        SSRes_AplList   sSRes_AplList = new SSRes_AplList();
        SS_Stt          sS_Status;
        SS_Chk          sS_Chk = new SS_Chk();
        
        if (!sS_Chk.checkDataBank(sSReq_HeadBank.getBankcode()))
        {
            sS_Status = new SS_Stt(scode(ST_REJECTED), smsg(ST_REJECTED),
                                            sS_Chk.code(), sS_Chk.msg());
            sSRes_AplList.setStatus(sS_Status);
            return sSRes_AplList;
        }
                
        try {
            if(isLiveCtx()){
                connection= SW_INT.getDBConnection();

                callableStatement = connection.prepareCall(APL[SQL]);
                sSReq_HeadBank.setSqlType(APL[REQ]);
                sSRes_AplList.setSqlType(APL[RES]);
                callableStatement.setObject(1, sSReq_HeadBank);
                callableStatement.setObject(2, sSRes_AplList);
                callableStatement.registerOutParameter( 2, OracleTypes.STRUCT, APL[RES]);
                callableStatement.executeUpdate();

                Map<String,Class<?>> typeMap = connection.getTypeMap();
                typeMap.put( APL[RES], SSRes_AplList.class );

                sSRes_AplList = (SSRes_AplList) callableStatement.getObject(2);
            }else{
                SSEnt_APL[] sSEnt_APLs = new SSEnt_APL[1];
                SSEnt_Limits[] sSEnt_Limitses = new SSEnt_Limits[6];
                sSEnt_Limitses[0] =  new SSEnt_Limits("Mountly Account Transfer", "10000","String");
                sSEnt_Limitses[1] =  new SSEnt_Limits("Mountly Account Transfer (Swip", "10000","String");
                sSEnt_Limitses[2] =  new SSEnt_Limits("Fees Bulk Evoucher", "3.3%","String");
                sSEnt_Limitses[3] =  new SSEnt_Limits("Fees Account Transfer (Swipe)", "1%","String");
                sSEnt_Limitses[4] =  new SSEnt_Limits("Fees Evoucher", "3.3%","String");
                sSEnt_Limitses[5] =  new SSEnt_Limits("Fees Account Transfer", "1%","String");
                sSEnt_APLs[0] =  new SSEnt_APL("1020360", "wallet cashier pre-paid card",sSEnt_Limitses);
                sSRes_AplList.setApl(sSEnt_APLs);
                sS_Status         = new SS_Stt(scode(ST_APPROVED), smsg(ST_APPROVED),
                                                ecode(ER_CLEAN), emsg(ER_CLEAN));
                sSRes_AplList.setStatus(sS_Status);
            }
        }catch (Exception e) {     
            sS_Status = new SS_Stt(scode(ST_ERROR), smsg(ST_ERROR),
                                            ecode(ER_PROCESS),emsg(ER_PROCESS) + e.getMessage());
             sSRes_AplList.setStatus(sS_Status);
            return sSRes_AplList;
        } finally {
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SS_Prc.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection!=null) try {connection.close();}catch (Exception ignore) {}
        }
        sSRes_AplList.updateStatus(APL[CLR]);
        return sSRes_AplList;
    }

    public SSRes_Cities getCities(SSReq_Base sSReq_Base) {
        
        CallableStatement callableStatement = null;
        Connection        connection        = null;
        SSRes_Cities      sSRes_Cities      = new SSRes_Cities();
        SS_Stt         sS_Status;
        
        try {
            if(isLiveCtx()){
                connection= SW_INT.getDBConnection();

                callableStatement = connection.prepareCall(CITY[SQL]);
                sSReq_Base.setSqlType(CITY[REQ]);
                sSRes_Cities.setSqlType(CITY[RES]);
                callableStatement.setObject(1, sSReq_Base);
                callableStatement.setObject(2, sSRes_Cities);
                callableStatement.registerOutParameter( 2, OracleTypes.STRUCT, CITY[RES]);
                callableStatement.executeUpdate();

                Map<String,Class<?>> typeMap = connection.getTypeMap();
                typeMap.put( CITY[RES], SSRes_Cities.class );

                sSRes_Cities = (SSRes_Cities) callableStatement.getObject(2);
            }else{
                SSEnt_Cities[] sSEnt_Citieses = new SSEnt_Cities[3];
                sSEnt_Citieses[0] =  new SSEnt_Cities("3601", "Tripoli");
                sSEnt_Citieses[1] =  new SSEnt_Cities("5541", "Benghazi");
                sSEnt_Citieses[2] =  new SSEnt_Cities("5561", "Misrata");
                sSRes_Cities.setCities(sSEnt_Citieses);
                sS_Status         = new SS_Stt(scode(ST_APPROVED), smsg(ST_APPROVED),
                                                ecode(ER_CLEAN), emsg(ER_CLEAN));
                sSRes_Cities.setStatus(sS_Status);
            }
            
        }catch (Exception e) {     
            sS_Status = new SS_Stt(scode(ST_ERROR), smsg(ST_ERROR),
                                            ecode(ER_PROCESS),emsg(ER_PROCESS) + e.getMessage());
             sSRes_Cities.setStatus(sS_Status);
            return sSRes_Cities;
        } finally {
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SS_Prc.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection!=null) try {connection.close();}catch (Exception ignore) {}
        }
        sSRes_Cities.updateStatus(CITY[CLR]);
        return sSRes_Cities;
    }
    
   public SSRes_Base pingServer(SSReq_Base sSReq_Base) {
       
        CallableStatement callableStatement = null;
        Connection        connection        = null;
        SSRes_Base        sSRes_Base        = new SSRes_Base();
        SS_Stt            sS_Status;
        
        try {
            if(isLiveCtx()){
                connection= SW_INT.getDBConnection();
                callableStatement = connection.prepareCall(PINGSERVER[SQL]);
                sSReq_Base.setSqlType(PINGSERVER[REQ]);
                sSRes_Base.setSqlType(PINGSERVER[RES]);
                callableStatement.setObject(1, sSReq_Base);
                callableStatement.setObject(2, sSRes_Base);
                callableStatement.registerOutParameter( 2, OracleTypes.STRUCT, PINGSERVER[RES]);
                callableStatement.executeUpdate();

                Map<String,Class<?>> typeMap = connection.getTypeMap();
                typeMap.put(PINGSERVER[RES], SSRes_Base.class );
                
                
                 sSRes_Base = (SSRes_Base) callableStatement.getObject(2);

            }else{
                sS_Status         = new SS_Stt(scode(ST_APPROVED), smsg(ST_APPROVED),
                                                            ecode(ER_CLEAN), emsg(ER_CLEAN));
                sSRes_Base.setStatus(sS_Status);
            }
            
        }catch (Exception e) {     
            sS_Status = new SS_Stt(scode(ST_ERROR), smsg(ST_ERROR),
                                            ecode(ER_PROCESS),emsg(ER_PROCESS) + e.getMessage());
             sSRes_Base.setStatus(sS_Status);
            return sSRes_Base;
        } finally {
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SS_Prc.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection!=null) try {connection.close();}catch (Exception ignore) {}
        }
        sSRes_Base.updateStatus(PINGSERVER[CLR]);
        return sSRes_Base;
    }

      public SSRes_Base getmnolist(SSReq_Base sSReq_Base) {
       
        CallableStatement callableStatement = null;
        Connection        connection        = null;
        SSRes_Base        sSRes_Base        = new SSRes_Base();
        SS_Stt            sS_Status;
        
        try {
            if(isLiveCtx()){
                connection= SW_INT.getDBConnection();
                callableStatement = connection.prepareCall(PINGSERVER[SQL]);
                sSReq_Base.setSqlType(PINGSERVER[REQ]);
                sSRes_Base.setSqlType(PINGSERVER[RES]);
                callableStatement.setObject(1, sSReq_Base);
                callableStatement.setObject(2, sSRes_Base);
                callableStatement.registerOutParameter( 2, OracleTypes.STRUCT, PINGSERVER[RES]);
                callableStatement.executeUpdate();

                Map<String,Class<?>> typeMap = connection.getTypeMap();
                typeMap.put(PINGSERVER[RES], SSRes_Base.class );
                
                
                 sSRes_Base = (SSRes_Base) callableStatement.getObject(2);

            }else{
                sS_Status         = new SS_Stt(scode(ST_APPROVED), smsg(ST_APPROVED),
                                                            ecode(ER_CLEAN), emsg(ER_CLEAN));
                sSRes_Base.setStatus(sS_Status);
            }
            
        }catch (Exception e) {     
            sS_Status = new SS_Stt(scode(ST_ERROR), smsg(ST_ERROR),
                                            ecode(ER_PROCESS),emsg(ER_PROCESS) + e.getMessage());
             sSRes_Base.setStatus(sS_Status);
            return sSRes_Base;
        } finally {
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SS_Prc.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection!=null) try {connection.close();}catch (Exception ignore) {}
        }
        sSRes_Base.updateStatus(PINGSERVER[CLR]);
        return sSRes_Base;
    }
     
  
    private SSEnt_Customer getCustomerSample() {
        SSEnt_Customer customer = new SSEnt_Customer();
        customer.setEnfirstname("AHMED");
        customer.setEnlastname("SALAH");
        customer.setArfirstname("");
        customer.setArlastname("");
        customer.setGender("M");
        customer.setTitle("1");
        customer.setDateob("1988-12-04");
        customer.setPlaceob("Tripoli");
        customer.setMobile("00218125689745");
        customer.setEmail("s.ahmed@gmail.com");
        customer.setBankcode("8671");
        customer.setBranchcode("99999");
        customer.setNationalid("125467");
        customer.setNationality("Libyan");
        //customer.setAccountnumber("879875457987987465");
        customer.setCustomercode("45866");
        customer.setPassportnumber("BS54658");
        customer.setPassportexpiration("2023-01-01");
        SSEnt_Address[] address = new SSEnt_Address[1];
        address[0] = new SSEnt_Address("03", "01", "NB alnour  25", "", "st abd nasser", "12566", "125", "", "434");
        customer.setAddress(address);
        return customer;
    }
    private SSEnt_Merchant getMerchantSample() {
        SSEnt_Merchant merchant = new SSEnt_Merchant();
            merchant.setMerchantid("546857");
            merchant.setMerchantname("S.A SHOP");
            merchant.setEnfirstname("MOHAMED");
            merchant.setEnlastname("NAIM");
            merchant.setGender("M");
            merchant.setTitle("1");
            merchant.setDateob("12/05/1970");
            merchant.setPlaceob("Damsh");
            merchant.setMobile("+218545458888");
            merchant.setEmail("mnaim@yahoo.com");
            SSEnt_Address[] address = new SSEnt_Address[1];
            address[0] = new SSEnt_Address("03", "01", "NB alnour  25", "", "st abd nasser", "12566", "125", "", "434");
            merchant.setAddress(address);
            merchant.setBankcode("1125");
            merchant.setBranchcode("11323");
            merchant.setNationalid("BI11526");
            merchant.setNationality("lybia");
            merchant.setAccountnumber("87987987999");
            merchant.setMerprogramcode("888");
            merchant.setMeractivitycode("142");
            merchant.setIdentity(null);
            SSEnt_PaymentMode[] sSEnt_PaymentMode = new SSEnt_PaymentMode[2];
            sSEnt_PaymentMode[0]= new SSEnt_PaymentMode("01","cash","30","y");
            sSEnt_PaymentMode[1]= new SSEnt_PaymentMode("02","transfer","70","y");
            merchant.setMerpaymentmode(sSEnt_PaymentMode);
            merchant.setPassportnumber("788");
            merchant.setPassportexpiration("12/08/2019");
            return merchant;
    }
    private SSEnt_Wallet[] getWalletSample() {
        SSEnt_Wallet[] wallet = new SSEnt_Wallet[1];
            wallet[0] = new SSEnt_Wallet();
            wallet[0].setWaccountnumber("787887878787555");
            wallet[0].setWcode("2505904881");
            wallet[0].setWprogramcode("005");
            wallet[0].setWstatus("Active");
            return wallet;
    }
    
    
    
        // EKH GATWAR FOR DESIGN AND TESTING
        public String repository(String repoReq) {
        
        CallableStatement callableStatement = null;
        Connection        connection        = null;
        Clob repoRes = null;
        SS_Stt         sS_Status;
        SS_Chk sS_Chk = new SS_Chk();        
        
        /*
        if (!sS_Chk.checkDataCriteria(sSReq_CustomerInfo.getCriteria()))
        {
            sS_Status = new SS_Stt(scode(ST_REJECTED), smsg(ST_REJECTED),
                                            sS_Chk.code(), sS_Chk.msg());
            sSRes_CustomerInfo.setStatus(sS_Status);
            return sSRes_CustomerInfo;
        }
        */
        
        try {
            if(isLiveCtx()){
                connection= SW_INT.getDBConnection();
                String program = "{call get_ref_data(?, ?)}";

                callableStatement = connection.prepareCall(program);
                callableStatement.setString(1, repoReq);  
                callableStatement.setClob(2, repoRes);  
                //ResultSet rs = callableStatement.executeQuery();
                callableStatement.registerOutParameter (2, java.sql.Types.CLOB);
                callableStatement.executeUpdate();
                repoRes = callableStatement.getClob(2);
                
                }else{
                return clobToString(repoRes);
                /*sSRes_CustomerInfo.setCustomer(getCustomerSample());
                sS_Status  = new SS_Stt(scode(ST_APPROVED), smsg(ST_APPROVED),
                                            ecode(ER_CLEAN), emsg(ER_CLEAN));
                sSRes_CustomerInfo.setStatus(sS_Status);*/
            }
        }catch (Exception e) {     
            sS_Status = new SS_Stt(scode(ST_ERROR), smsg(ST_ERROR),
                                            ecode(ER_PROCESS),emsg(ER_PROCESS) + e.getMessage());
             //sSRes_CustomerInfo.setStatus(sS_Status);
            return  clobToString(repoRes);
        } finally {
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SS_Prc.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection!=null) try {connection.close();}catch (Exception ignore) {}
        }
        //sSRes_CustomerInfo.updateStatus(CUSTOMERINFO[CLR]);
        return  clobToString(repoRes);
    }
        
         public String clobToString(Clob data) {
     StringBuilder sb = new StringBuilder();
        try {
            Reader reader = data.getCharacterStream();
            BufferedReader br = new BufferedReader(reader);

            String line;
            while(null != (line = br.readLine())) {
                sb.append(line);
            }
            br.close();
        } catch (SQLException e) {
            // handle this exception
        } catch (IOException e) {
            // handle this exception
        }
        return sb.toString();
}
    //END EKH
            
}
