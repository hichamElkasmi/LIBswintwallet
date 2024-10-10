package com.ssw.bnk.swintwallet.jdbc.entities;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author MOKHLISS
 */
@Getter @Setter 
public class SSEnt_Denomination {
    
    private String denominationcode;
    private String denominationvalue;

    public SSEnt_Denomination(String denominationcode, String denominationvalue) {
        this.denominationcode = denominationcode;
        this.denominationvalue = denominationvalue;
    }
}
