package com.ssw.bnk.swintwallet.jdbc.entities;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author MOKHLISS
 */
@Getter @Setter 
public class SSEnt_Banks {
    
    private String bankcode;
    private String banklabel;

    public SSEnt_Banks(String bankcode, String banklabel) {
        this.bankcode = bankcode;
        this.banklabel = banklabel;
    }
}
