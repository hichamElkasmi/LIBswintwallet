package com.ssw.bnk.swintwallet.jdbc.in;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author MOKHLISS
 */
@Getter @Setter 
public class SS_Stt {
    
    public String codestatus;
    public String descstatus;
    public String codeerror;
    public String msgerror;

    public SS_Stt() {
    }

        public SS_Stt(String codestatus, String descstatus, String codeerror, String msgerror) {
        this.codestatus = codestatus;
        this.descstatus = descstatus;
        this.codeerror = codeerror;
        this.msgerror = msgerror;
    }
}