package com.ssw.bnk.swintwallet.jdbc.entities;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author MOKHLISS
 */
@Getter @Setter 
public class SSEnt_Activities {
    
    private String maccode;
    private String maciden;
    private String maclabel;

    public SSEnt_Activities(String maccode, String maciden, String maclabel) {
        this.maccode = maccode;
        this.maciden = maciden;
        this.maclabel = maclabel;
    }

}
