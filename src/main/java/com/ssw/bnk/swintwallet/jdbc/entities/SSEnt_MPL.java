package com.ssw.bnk.swintwallet.jdbc.entities;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author MOKHLISS
 */
@Getter @Setter 
public class SSEnt_MPL {
    
    private String merprocode;
    private String merprolabel;
    private SSEnt_Activities[] meractivitieslist;
    private SSEnt_PaymentMode[] merpaymentmode;

    public SSEnt_MPL(String merprocode, String merprolabel, SSEnt_Activities[] meractivitieslist, SSEnt_PaymentMode[] merpaymentmode) {
        this.merprocode = merprocode;
        this.merprolabel = merprolabel;
        this.meractivitieslist = meractivitieslist;
        this.merpaymentmode = merpaymentmode;
    }
    
}
