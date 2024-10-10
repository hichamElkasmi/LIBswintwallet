package com.ssw.bnk.swintwallet.jdbc.entities;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author MOKHLISS
 */
@Getter @Setter 
public class SSEnt_APL {
    
    private String accprogramcode;
    private String accprogramlabel;
    private SSEnt_Limits[] limits;

    public SSEnt_APL(String accprogramcode, String accprogramlabel, SSEnt_Limits[] limits) {
        this.accprogramcode = accprogramcode;
        this.accprogramlabel = accprogramlabel;
        this.limits = limits;
    }
}
