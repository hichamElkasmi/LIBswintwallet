package com.ssw.bnk.swintwallet.jdbc.entities;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author MOKHLISS
 */
@Getter @Setter 
public class SSEnt_Initiator {
    
    private String type;
    private String identifier;
    private String cardcode;

    public SSEnt_Initiator(String type, String identifier, String cardcode) {
        this.type = type;
        this.identifier = identifier;
        this.cardcode = cardcode;
    }
}
