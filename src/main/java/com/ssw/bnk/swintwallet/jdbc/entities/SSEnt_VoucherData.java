package com.ssw.bnk.swintwallet.jdbc.entities;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Hicham ELKASMI
 */
@Getter @Setter 
public class SSEnt_VoucherData {
    
    private String pincode;
    private String voucherserial;
    private String expirydate;
    private String otherinfo;
    
    public SSEnt_VoucherData() {
        
    }
        
    public SSEnt_VoucherData(String pincode, String voucherserial, String expirydate, String other) {
        this.pincode = pincode;
        this.voucherserial = voucherserial;
        this.expirydate = expirydate;
        this.otherinfo = other;
    }

}
