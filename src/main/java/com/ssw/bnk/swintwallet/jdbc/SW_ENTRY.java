package com.ssw.bnk.swintwallet.jdbc;

import com.ssw.bnk.swintwallet.jdbc.in.SS_Prc;
import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_Auth;
import com.ssw.bnk.swintwallet.jdbc.responses.SSRes_Base;
import com.ssw.bnk.swintwallet.jdbc.in.*;
import com.ssw.bnk.swintwallet.jdbc.requests.*;
import com.ssw.bnk.swintwallet.jdbc.responses.*;
import com.owlike.genson.Genson;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.ssw.bnk.swintwallet.jdbc.in.SS_Prc.TRANSACTION_TYPE;
import java.sql.Clob;

/**
 *
 * @author MOKHLISS
 */
public class SW_ENTRY {
   
    
    public String enrollCustomer(SSReq_EnrolmentCustomer sSReq_EnrolmentCustomer){
       SSEnt_Auth    sSEnt_Auth;
       SSRes_Enrolment sSRes_Enrolment;
       
       sSEnt_Auth = (new SS_Prc()).processAuthentification(sSReq_EnrolmentCustomer.getHeader());
       if(sSEnt_Auth.checkStatus()){
            sSRes_Enrolment = (new SS_Prc()).enrollCustomer(sSReq_EnrolmentCustomer);
            sSRes_Enrolment.setHeader(sSEnt_Auth.getHeader());
       }
        else
            sSRes_Enrolment = new SSRes_Enrolment(sSEnt_Auth);

        SS_Ret sSReturn = new SS_Ret(sSRes_Enrolment);
        return formatJsonOutput(sSReturn);
    } 
    
    public String enrollMerchant(SSReq_EnrolmentMerchant sSReq_EnrolmentMerchant){
       SSEnt_Auth    sSEnt_Auth;
       SSRes_Enrolment sSRes_Enrolment;
       
       sSEnt_Auth = (new SS_Prc()).processAuthentification(sSReq_EnrolmentMerchant.getHeader());
       if(sSEnt_Auth.checkStatus()){
            sSRes_Enrolment = (new SS_Prc()).enrollMerchant(sSReq_EnrolmentMerchant);
            sSRes_Enrolment.setHeader(sSEnt_Auth.getHeader());
       }
       else
            sSRes_Enrolment = new SSRes_Enrolment(sSEnt_Auth);

        SS_Ret sSReturn = new SS_Ret(sSRes_Enrolment);
        return formatJsonOutput(sSReturn);
    } 

    
    public String updateCustomerInfo(SSReq_UpdateCustomer sSReq_UpdateCustomer){
       SSEnt_Auth    sSEnt_Auth;
       SSRes_Base sSRes_Base;
       
       sSEnt_Auth = (new SS_Prc()).processAuthentification(sSReq_UpdateCustomer.getHeader());
       if(sSEnt_Auth.checkStatus()){
            sSRes_Base = (new SS_Prc()).updateCustomerInfo(sSReq_UpdateCustomer);
            sSRes_Base.setHeader(sSEnt_Auth.getHeader());
       }
        else
            sSRes_Base = new SSRes_Base(sSEnt_Auth);

        SS_Ret sSReturn = new SS_Ret(sSRes_Base);
        return formatJsonOutput(sSReturn);
    } 
    
    public String getBalance(SSReq_Balance sReq_Balance){
       SSEnt_Auth    sSEnt_Auth;
       SSRes_Balance sSRes_Balance;
       
       sSEnt_Auth = (new SS_Prc()).processAuthentification(sReq_Balance.getHeader());
       if(sSEnt_Auth.checkStatus())
        {
           sSRes_Balance = (new SS_Prc()).getBalance(sReq_Balance);
           sSRes_Balance.setHeader(sSEnt_Auth.getHeader());
        }
        else
            sSRes_Balance = new SSRes_Balance(sSEnt_Auth);
       
        SS_Ret sSReturn = new SS_Ret(sSRes_Balance);
        return formatJsonOutput(sSReturn);
    } 

    public String getTransactions(SSReq_TransactionsList sSReq_TransactionsList) {
       SSEnt_Auth    sSEnt_Auth;
       SSRes_TransactionsList sSRes_TransactionsList;
       
       sSEnt_Auth = (new SS_Prc()).processAuthentification(sSReq_TransactionsList.getHeader());
       if(sSEnt_Auth.checkStatus())
        {
            sSRes_TransactionsList = (new SS_Prc()).getTransactions(sSReq_TransactionsList);
            sSRes_TransactionsList.setHeader(sSEnt_Auth.getHeader());
        }
        else
            sSRes_TransactionsList = new SSRes_TransactionsList(sSEnt_Auth);

        SS_Ret sSReturn = new SS_Ret(sSRes_TransactionsList);
        return formatJsonOutput(sSReturn);
    } 
   
    public String transaction(SSReq_Transaction sSReq_Transaction, TRANSACTION_TYPE transaction_type) {
       SSEnt_Auth    sSEnt_Auth;
       SSRes_Transaction sSRes_Transaction;
       
       sSEnt_Auth = (new SS_Prc()).processAuthentification(sSReq_Transaction.getHeader());
        if(sSEnt_Auth.checkStatus()){
        sSRes_Transaction = (new SS_Prc()).transaction(sSReq_Transaction, transaction_type);
            sSRes_Transaction.setHeader(sSEnt_Auth.getHeader());
        }
        else
            sSRes_Transaction = new SSRes_Transaction(sSEnt_Auth);

        SS_Ret sSReturn = new SS_Ret(sSRes_Transaction);
        return formatJsonOutput(sSReturn);
    } 
    
    
    // EKH ADD NEW FUNCTION FOR PURSHASE VOUCHER
    public String PurchaseVoucher(SSReq_Transaction sSReq_Transaction, TRANSACTION_TYPE transaction_type) {
       SSEnt_Auth    sSEnt_Auth;
       SSRes_PurshaseVoucher sSRes_PurshaseVoucher;
       
       sSEnt_Auth = (new SS_Prc()).processAuthentification(sSReq_Transaction.getHeader());
        if(sSEnt_Auth.checkStatus()){
        sSRes_PurshaseVoucher = (new SS_Prc()).PurchaseVoucher(sSReq_Transaction, transaction_type);
            sSRes_PurshaseVoucher.setHeader(sSEnt_Auth.getHeader());
        }
        else
            sSRes_PurshaseVoucher = new SSRes_PurshaseVoucher(sSEnt_Auth);

        SS_Ret sSReturn = new SS_Ret(sSRes_PurshaseVoucher);
        return formatJsonOutput(sSReturn);
    } 
    
    public String HistoryVoucher(SSReq_HistoryVoucher sSReq_HistoryVoucher) {
       SSEnt_Auth    sSEnt_Auth;
       SSRes_HistoryVoucher sSRes_HistoryVoucher;
       
       sSEnt_Auth = (new SS_Prc()).processAuthentification(sSReq_HistoryVoucher.getHeader());
       if(sSEnt_Auth.checkStatus())
        {
            sSRes_HistoryVoucher = (new SS_Prc()).getHistoryVoucher(sSReq_HistoryVoucher);
            sSRes_HistoryVoucher.setHeader(sSEnt_Auth.getHeader());
        }
        else
            sSRes_HistoryVoucher = new SSRes_HistoryVoucher(sSEnt_Auth);

        SS_Ret sSReturn = new SS_Ret(sSRes_HistoryVoucher);
        return formatJsonOutput(sSReturn);
    }
 
    public String moneytransfer(SSReq_Moneytransfer sSReq_moneytransfer) {
       SSEnt_Auth    sSEnt_Auth;
        SSRes_Moneytransfer sSRes_Moneytransfer;
       
       sSEnt_Auth = (new SS_Prc()).processAuthentification(sSReq_moneytransfer.getHeader());
       if(sSEnt_Auth.checkStatus())
        {
            sSRes_Moneytransfer = (new SS_Prc()).moneytransfer(sSReq_moneytransfer);
            sSRes_Moneytransfer.setHeader(sSEnt_Auth.getHeader());
        }
        else
            sSRes_Moneytransfer = new SSRes_Moneytransfer(sSEnt_Auth);

        SS_Ret sSReturn = new SS_Ret(sSRes_Moneytransfer);
        return formatJsonOutput(sSReturn);
    }
    
    // EKH END
        
    public String getMerchantInfo(SSReq_Info sSReq_MerchantInfo) {
       SSEnt_Auth    sSEnt_Auth;
       SSRes_MerchantInfo sSRes_MerchantInfo;
       
       sSEnt_Auth = (new SS_Prc()).processAuthentification(sSReq_MerchantInfo.getHeader());
        if(sSEnt_Auth.checkStatus()){
            sSRes_MerchantInfo = (new SS_Prc()).getMerchantInfo(sSReq_MerchantInfo);
            sSRes_MerchantInfo.setHeader(sSEnt_Auth.getHeader());
        }
        else
            sSRes_MerchantInfo = new SSRes_MerchantInfo(sSEnt_Auth);

        SS_Ret sSReturn = new SS_Ret(sSRes_MerchantInfo);
        return formatJsonOutput(sSReturn);
    } 
    
    public String getCustomerInfo(SSReq_Info sSReq_CustomerInfo) {
       SSEnt_Auth    sSEnt_Auth;
       SSRes_CustomerInfo sSRes_CustomerInfo;
       
       sSEnt_Auth = (new SS_Prc()).processAuthentification(sSReq_CustomerInfo.getHeader());
        if(sSEnt_Auth.checkStatus()){
            sSRes_CustomerInfo = (new SS_Prc()).getCustomerInfo(sSReq_CustomerInfo);
            sSRes_CustomerInfo.setHeader(sSEnt_Auth.getHeader());
        }
        else
            sSRes_CustomerInfo = new SSRes_CustomerInfo(sSEnt_Auth);

        SS_Ret sSReturn = new SS_Ret(sSRes_CustomerInfo);
        return formatJsonOutput(sSReturn);
    } 
    
   
    public String getWalletInfo(SSReq_Wallet sSReq_Wallet) {
       SSEnt_Auth    sSEnt_Auth;
       SSRes_Wallets sSRes_Wallets;
       
       sSEnt_Auth = (new SS_Prc()).processAuthentification(sSReq_Wallet.getHeader());
        if(sSEnt_Auth.checkStatus()){
            sSRes_Wallets = (new SS_Prc()).getWalletInfo(sSReq_Wallet);
            sSRes_Wallets.setHeader(sSEnt_Auth.getHeader());
        }
        else
            sSRes_Wallets = new SSRes_Wallets(sSEnt_Auth);

        SS_Ret sSReturn = new SS_Ret(sSRes_Wallets);
        return formatJsonOutput(sSReturn);
    } 
    
    public String getCities(SSReq_Base sSReq_Base) {
       SSEnt_Auth    sSEnt_Auth;
       SSRes_Cities  sSRes_Cities;
       
       sSEnt_Auth = (new SS_Prc()).processAuthentification(sSReq_Base.getHeader());
       if(sSEnt_Auth.checkStatus())
       {
            sSRes_Cities = (new SS_Prc()).getCities(sSReq_Base);
            sSRes_Cities.setHeader(sSEnt_Auth.getHeader());
       }
        else
            sSRes_Cities = new SSRes_Cities(sSEnt_Auth);

        SS_Ret sSReturn = new SS_Ret(sSRes_Cities);
        return formatJsonOutput(sSReturn);
    } 
    
    public String getMno(SSReq_Base sSReq_Base) {
       SSEnt_Auth    sSEnt_Auth;
       SSRes_Mno  sSRes_Mno;
       
       sSEnt_Auth = (new SS_Prc()).processAuthentification(sSReq_Base.getHeader());
       if(sSEnt_Auth.checkStatus())
        {
            sSRes_Mno = (new SS_Prc()).getMno(sSReq_Base);
            sSRes_Mno.setHeader(sSEnt_Auth.getHeader());
        }
        else
            sSRes_Mno = new SSRes_Mno(sSEnt_Auth);

        SS_Ret sSReturn = new SS_Ret(sSRes_Mno);
        return formatJsonOutput(sSReturn);
    } 
     
    public String getBanks(SSReq_Base sSReq_Base) {
       SSEnt_Auth    sSEnt_Auth;
       SSRes_Banks  sSRes_Banks;
       
       sSEnt_Auth = (new SS_Prc()).processAuthentification(sSReq_Base.getHeader());
       if(sSEnt_Auth.checkStatus())
        {
            sSRes_Banks = (new SS_Prc()).getBanks(sSReq_Base);
            sSRes_Banks.setHeader(sSEnt_Auth.getHeader());
        }
        else
            sSRes_Banks = new SSRes_Banks(sSEnt_Auth);

        SS_Ret sSReturn = new SS_Ret(sSRes_Banks);
        return formatJsonOutput(sSReturn);
    } 
     
    public String getMpl(SSReq_HeadBank sSReq_HeadBank) {
       SSEnt_Auth    sSEnt_Auth;
       SSRes_MplList  sSRes_MplList;
       
       sSEnt_Auth = (new SS_Prc()).processAuthentification(sSReq_HeadBank.getHeader());
       if(sSEnt_Auth.checkStatus()){
            sSRes_MplList = (new SS_Prc()).getMpl(sSReq_HeadBank);
            sSRes_MplList.setHeader(sSEnt_Auth.getHeader());
        }
        else
            sSRes_MplList = new SSRes_MplList(sSEnt_Auth);

        SS_Ret sSReturn = new SS_Ret(sSRes_MplList);
        return formatJsonOutput(sSReturn);
    } 
       
    public String getApl(SSReq_HeadBank sSReq_HeadBank) {
       SSEnt_Auth    sSEnt_Auth;
       SSRes_AplList sSRes_AplList;
       
       sSEnt_Auth = (new SS_Prc()).processAuthentification(sSReq_HeadBank.getHeader());
       if(sSEnt_Auth.checkStatus()){
            sSRes_AplList = (new SS_Prc()).getApl(sSReq_HeadBank);
            sSRes_AplList.setHeader(sSEnt_Auth.getHeader());
        }
        else
            sSRes_AplList = new SSRes_AplList(sSEnt_Auth);

        SS_Ret sSReturn = new SS_Ret(sSRes_AplList);
        return formatJsonOutput(sSReturn);
    } 
       
    public String getBranchs(SSReq_HeadBank sSReq_HeadBank) {
       SSEnt_Auth    sSEnt_Auth;
       SSRes_Branchs sSRes_Branchs;
       
       sSEnt_Auth = (new SS_Prc()).processAuthentification(sSReq_HeadBank.getHeader());
       if(sSEnt_Auth.checkStatus())
       {
            sSRes_Branchs = (new SS_Prc()).getBranchs(sSReq_HeadBank);
            sSRes_Branchs.setHeader(sSEnt_Auth.getHeader());
       }
        else
            sSRes_Branchs = new SSRes_Branchs(sSEnt_Auth);

        SS_Ret sSReturn = new SS_Ret(sSRes_Branchs);
        return formatJsonOutput(sSReturn);
    } 
 
    public String pingServer(SSReq_Base sSReq_Base){
       SSEnt_Auth    sSEnt_Auth;
       SSRes_Base sSRes_Base;
       
       sSEnt_Auth = (new SS_Prc()).processAuthentification(sSReq_Base.getHeader());
       if(sSEnt_Auth.checkStatus()){
            sSRes_Base = (new SS_Prc()).pingServer(sSReq_Base);
            sSRes_Base.setHeader(sSEnt_Auth.getHeader());
       }
        else
            sSRes_Base = new SSRes_Base(sSEnt_Auth);

        SS_Ret sSReturn = new SS_Ret(sSRes_Base);
        return formatJsonOutput(sSReturn);
    } 
    
    public String formatJsonOutput(SS_Ret sSReturn){
        String jsonString;
        Genson genson;
        try {
            genson = SW_INT.builder.create();
            jsonString = genson.serialize(sSReturn);
        } catch (Exception ex) {
            Logger.getLogger(SW_ENTRY.class.getName()).log(Level.SEVERE, null, ex);
            jsonString=null;
        }
        return jsonString;
    }
    
    
    
    public String repository (String repoString) {
            return (new SS_Prc()).repository(repoString);
    }
}   
