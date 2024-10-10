package com.ssw.bnk.swintwallet.jdbc.entities;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author MOKHLISS
 */
@Getter @Setter 
public class SSEnt_Filter {
    
    private String ntrans;
    private String idtrans;
    private String startdate;
    private String enddate;
    private String transactiontype;

    public SSEnt_Filter(String ntrans, String idtrans, String startdate, String enddate, String transactiontype) {
        this.ntrans = ntrans;
        this.idtrans = idtrans;
        this.startdate = startdate;
        this.enddate = enddate;
        this.transactiontype = transactiontype;
    }
}
