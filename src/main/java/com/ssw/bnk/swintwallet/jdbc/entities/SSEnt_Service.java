package com.ssw.bnk.swintwallet.jdbc.entities;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author MOKHLISS
 */
@Getter @Setter 
public class SSEnt_Service {
    
    private String servicecode;
    private String servicelabel;
    private SSEnt_Denomination[] denominations;
    
    public SSEnt_Service(String servicecode, String servicelabel, SSEnt_Denomination[] denominations) {
        this.servicecode = servicecode;
        this.servicelabel = servicelabel;
        this.denominations = denominations;
    }
}
