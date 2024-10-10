package com.ssw.bnk.swintwallet.jdbc.entities;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Hicham ELKASMI
 */
@Getter @Setter 
public class SSEnt_VoucherTranStruct {
    
    private String denovalue;
    private String operacode;
    private String transdate;
    private String pin;

    public SSEnt_VoucherTranStruct() {
    }

    public SSEnt_VoucherTranStruct(String denovalue, String operacode, String transdate,String pin) {
        this.denovalue = denovalue;
        this.operacode = operacode;
        this.transdate = transdate;
        this.pin = pin;
    }

}
