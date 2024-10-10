package com.ssw.bnk.swintwallet.jdbc.entities;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author MOKHLISS
 */
@Getter @Setter 
public class SSEnt_Balance {
    
    private String available;
    private String currencycode;

    public SSEnt_Balance(String available, String currencycode) {
        this.available = available;
        this.currencycode = currencycode;
    }    
}
