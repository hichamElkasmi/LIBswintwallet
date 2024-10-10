/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssw.bnk.swintwallet.jdbc.entities;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Hicham Elkasmi
 */
@Getter
@Setter
public class SSEnt_Request_VSS {
        private String date;
    private String addid;
    private String adddata;
    private String currency;
    private String label;
    private String trxtype;
    private String denocode;
    private String mnocode;
    
    public SSEnt_Request_VSS(String date, String addid, String adddata, String currency, String label, String trxtype, String dno_code, String mno_code) {
        this.date = date;
        this.addid = addid;
        this.adddata = adddata;
        this.currency = currency;
        this.label = label;
        this.trxtype = trxtype;
        this.denocode = dno_code;
        this.mnocode = mno_code;
    }
}
