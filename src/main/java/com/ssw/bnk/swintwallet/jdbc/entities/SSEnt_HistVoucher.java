package com.ssw.bnk.swintwallet.jdbc.entities;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Hicham Elkasmi
 */
@Getter @Setter 
public class SSEnt_HistVoucher {
    
    private String operacode;
    private String ntrans;
    private String idtrans;
    private String startdate;
    private String enddate;
    private String transactiontype;

    public SSEnt_HistVoucher(String ntrans, String idtrans, String startdate, String enddate, String transactiontype, String operacode) {
        this.ntrans = ntrans;
        this.idtrans = idtrans;
        this.startdate = startdate;
        this.enddate = enddate;
        this.transactiontype = transactiontype;
        this.operacode = operacode;
        
    }
}
