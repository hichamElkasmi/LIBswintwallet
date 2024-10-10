package com.ssw.bnk.swintwallet.jdbc.entities;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author MOKHLISS
 */
@Getter @Setter 
public class SSEnt_Cities {
    
    private String citycode;
    private String citylabel;

    public SSEnt_Cities() {
    }

    public SSEnt_Cities(String citycode, String citylabel) {
        this.citycode = citycode;
        this.citylabel = citylabel;
    }
}
