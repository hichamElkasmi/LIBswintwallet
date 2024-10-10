package com.ssw.bnk.swintwallet.jdbc.entities;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author MOKHLISS
 */
@Getter @Setter 
public class SSEnt_Criteria {
    
    private String nationalid;
    private String bankcode;

    public SSEnt_Criteria(String nationalid, String bankcode) {
        this.nationalid = nationalid;
        this.bankcode = bankcode;
    }
}
