package com.ssw.bnk.swintwallet.jdbc.entities;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author MOKHLISS
 */
@Getter @Setter 
public class SSEnt_Branchs {
    
    private String branchcode;
    private String branchlabel;

    public SSEnt_Branchs(String branchcode, String branchlabel) {
        this.branchcode = branchcode;
        this.branchlabel = branchlabel;
    }
}
