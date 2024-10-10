package com.ssw.bnk.swintwallet.jdbc.entities;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author MOKHLISS
 */
@Getter @Setter 
public class SSEnt_Authorization {
    
    private String referenceid;
    private String authcode;
    private String authDate;
    
    public SSEnt_Authorization() {
        
    }
        
    public SSEnt_Authorization(String referenceid, String authcode, String authDate) {
        this.referenceid = referenceid;
        this.authcode = authcode;
        this.authDate = authDate;
    }

}
