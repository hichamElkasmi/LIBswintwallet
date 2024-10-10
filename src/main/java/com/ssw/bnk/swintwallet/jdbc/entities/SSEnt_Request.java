package com.ssw.bnk.swintwallet.jdbc.entities;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author MOKHLISS
 */
@Getter @Setter 
public class SSEnt_Request {
    
    private String date;
    private String addid;
    private String adddata;
    private String amount;
    private String currency;
    private String label;
    private String trxtype;
    
    public SSEnt_Request(String date, String addid, String adddata, String amount, String currency, String label, String trxtype) {
        this.date = date;
        this.addid = addid;
        this.adddata = adddata;
        this.amount = amount;
        this.currency = currency;
        this.label = label;
        this.trxtype = trxtype;
    }

    public String setAddid() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
