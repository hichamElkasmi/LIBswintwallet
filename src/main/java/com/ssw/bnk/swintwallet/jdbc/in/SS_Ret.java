package com.ssw.bnk.swintwallet.jdbc.in;

import com.ssw.bnk.swintwallet.jdbc.entities.*;
import com.ssw.bnk.swintwallet.jdbc.responses.*;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author MOKHLISS
 */
@Getter @Setter 
public class SS_Ret {
    private SSEnt_Header header;
    private SS_Stt status;
    private SSEnt_Balance balance;
    private SSEnt_Identity identity;
    private SSEnt_Authorization authorization;
    private SSEnt_Authorization_VSS authorization_VSS;
    private SSEnt_Trasansaction[] transactions;
    private SSEnt_Banks[] banks;
    private SSEnt_Mno[] mno;
    private SSEnt_Wallet[] wallet;
    private SSEnt_Cities[] cities;
    private SSEnt_Branchs[] branchs;
    private SSEnt_MPL[] mpl;
    private SSEnt_APL[] apl;
    private SSEnt_Customer customer;
    private SSEnt_Merchant merchant;  
    // EKH FOE NEW SERVICES
    private SSEnt_VoucherData voucherData;   
    private SSEnt_VoucherTranStruct [] histvoucher;
    // END EKH FOR NEW SERVICES

    public SS_Ret(SSRes_Base sSRes_Base) {
        this.header = sSRes_Base.getHeader();
        this.status = sSRes_Base.getStatus();
    }
        
    public SS_Ret(SSRes_MerchantInfo sSRes_MerchantInfo) {
        this((SSRes_Base)sSRes_MerchantInfo);
        this.merchant = sSRes_MerchantInfo.getMerchant();
    }
    
    public SS_Ret(SSRes_CustomerInfo sSRes_CustomerInfo) {
        this((SSRes_Base)sSRes_CustomerInfo);
        this.customer = sSRes_CustomerInfo.getCustomer();
    }
    
    public SS_Ret(SSRes_AplList sSRes_AplList) {
        this((SSRes_Base)sSRes_AplList);
        this.apl = sSRes_AplList.getApl();
    }
    
    public SS_Ret(SSRes_Wallets sSRes_Wallets) {
        this((SSRes_Base)sSRes_Wallets);
        this.wallet = sSRes_Wallets.getWallet();
    }
    
    public SS_Ret(SSRes_MplList sSRes_MplList) {
        this((SSRes_Base)sSRes_MplList);
        this.mpl = sSRes_MplList.getMpl();
    }
    
    public SS_Ret(SSRes_Branchs sSRes_Branchs) {
        this((SSRes_Base)sSRes_Branchs);
        this.branchs = sSRes_Branchs.getBranchs();
    }
        
    public SS_Ret(SSRes_Balance sSRes_Balance) {
        this((SSRes_Base)sSRes_Balance);
        this.balance = sSRes_Balance.getBalance();
    }
 
    public SS_Ret(SSRes_Cities sSRes_Cities) {
        this((SSRes_Base)sSRes_Cities);
        this.cities = sSRes_Cities.getCities();
    }
        
    public SS_Ret(SSRes_Banks sSRes_Banks) {
        this((SSRes_Base)sSRes_Banks);
        this.banks = sSRes_Banks.getBanks();
    }

    public SS_Ret(SSRes_Mno sSRes_Mno) {
        this((SSRes_Base)sSRes_Mno);
        this.mno = sSRes_Mno.getMno();
    }
        
    public SS_Ret(SSRes_Enrolment sSRes_Enrolment) {
        this((SSRes_Base)sSRes_Enrolment);
        this.identity = sSRes_Enrolment.getIdentity();
    }

    public SS_Ret(SSRes_Transaction sSRes_Transaction) {
        this((SSRes_Base)sSRes_Transaction);
        this.authorization = sSRes_Transaction.getAuthorization();
    }

    public SS_Ret(SSRes_TransactionsList sSRes_TransactionsList) {
        this((SSRes_Base)sSRes_TransactionsList);
        this.transactions = sSRes_TransactionsList.getTransactions();
    }

    // EKH ADD NEW SERVICES 
    public SS_Ret(SSRes_PurshaseVoucher sSRes_PurshaseVoucher) {
        this((SSRes_Transaction)sSRes_PurshaseVoucher);
        this.voucherData = sSRes_PurshaseVoucher.getVoucherDT();
    }
    
    public SS_Ret(SSRes_HistoryVoucher sSRes_HistoryVoucher) {
        this((SSRes_Base)sSRes_HistoryVoucher);
        this.histvoucher = sSRes_HistoryVoucher.getTransactions();
    }
    
    public SS_Ret(SSRes_Moneytransfer sSRes_Moneytransfer) {
        this((SSRes_Base)sSRes_Moneytransfer);
        //this.histvoucher = sSRes_Moneytransfer.getTransactions();
    }
    // END EKH 
}
