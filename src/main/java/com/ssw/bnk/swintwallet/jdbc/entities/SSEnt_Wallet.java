package com.ssw.bnk.swintwallet.jdbc.entities;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author MOKHLISS
 */
@Getter @Setter 
public class SSEnt_Wallet {
    
    private String wcode;
    private String wstatus;
    private String wprogramcode;
    private String waccountnumber;
    
    public SSEnt_Wallet() {
    }

    public SSEnt_Wallet(String wcode, String wstatus, String wprogramcode, String waccountnumber) {
        this.wcode = wcode;
        this.wstatus = wstatus;
        this.wprogramcode = wprogramcode;
        this.waccountnumber = waccountnumber;
    }
}
