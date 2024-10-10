package com.ssw.bnk.swintwallet.jdbc.entities;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Hicham ELKASMI
 */
@Getter @Setter 
public class SSEnt_Authorization_VSS {
    
    private String referenceid;
    private String authcode;
    private SSEnt_VoucherData voucherDT;
    
    public SSEnt_Authorization_VSS() {
        
    }
     
        public SSEnt_Authorization_VSS(String referenceid, String authcode) {
        this.referenceid = referenceid;
        this.authcode = authcode;
        this.voucherDT = new SSEnt_VoucherData();
    }
        
    public SSEnt_Authorization_VSS(String referenceid, String authcode,SSEnt_VoucherData voucherdt) {
        this.referenceid = referenceid;
        this.authcode = authcode;
        this.voucherDT = voucherdt;
    }

}
