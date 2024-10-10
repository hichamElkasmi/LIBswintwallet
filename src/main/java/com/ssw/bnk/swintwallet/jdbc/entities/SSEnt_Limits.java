package com.ssw.bnk.swintwallet.jdbc.entities;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author MOKHLISS
 */
@Getter @Setter 
public class SSEnt_Limits {
    
    private String label;
    private String value;
    private String valuetype;

    public SSEnt_Limits(String label, String value, String valuetype) {
        this.label = label;
        this.value = value;
        this.valuetype = valuetype;
    }
}
