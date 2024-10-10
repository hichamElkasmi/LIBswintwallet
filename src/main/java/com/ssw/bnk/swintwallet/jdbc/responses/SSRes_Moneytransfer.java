package com.ssw.bnk.swintwallet.jdbc.responses;

import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_Auth;
import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_Header;
import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_Identity;
import com.ssw.bnk.swintwallet.jdbc.in.SS_Stt;


/*
 * @author Hicham ELKASMI
 */
public class SSRes_Moneytransfer extends SSRes_Base {
            
     public SSRes_Moneytransfer() {
        super();
    }
     
    public SSRes_Moneytransfer(SSEnt_Auth sSEnt_Auth) {
        super(sSEnt_Auth);
    }
    
    public SSRes_Moneytransfer(SSEnt_Header header, SSEnt_Identity identity, SS_Stt status) {
        super(header,status);
    }

}
