package com.ssw.bnk.swintwallet.jdbc.responses;

import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_Auth;
import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_Header;
import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_Identity;
import com.ssw.bnk.swintwallet.jdbc.in.SS_Stt;
import lombok.Getter;
import lombok.Setter;


/**
 *
 * @author MOKHLISS
 */
public class SSRes_Enrolment extends SSRes_Base {
    
    @Getter @Setter 
    protected SSEnt_Identity identity;
         
     public SSRes_Enrolment() {
        super();
        identity = null;
    }
     
    public SSRes_Enrolment(SSEnt_Auth sSEnt_Auth) {
        super(sSEnt_Auth);
        identity = null;
    }
    
    public SSRes_Enrolment(SSEnt_Header header, SSEnt_Identity identity, SS_Stt status) {
        super(header,status);
        this.identity = identity;
    }

}
