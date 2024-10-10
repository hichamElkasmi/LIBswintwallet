package com.ssw.bnk.swintwallet.jdbc.entities;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author MOKHLISS
 */
@Getter @Setter 
public class SSEnt_Identity {
    
    private String customercode;
    private String walletcode;
    private String mechantid;
    
    public SSEnt_Identity(String customercode, String walletcode) {
        this.customercode = customercode;
        this.walletcode = walletcode;
        this.mechantid = null;
    }

    public SSEnt_Identity(String mechantid) {
        this.customercode = null;
        this.walletcode = null;
        this.mechantid = mechantid;
    }
    
    public SSEnt_Identity() {
        
    }    
        
}
