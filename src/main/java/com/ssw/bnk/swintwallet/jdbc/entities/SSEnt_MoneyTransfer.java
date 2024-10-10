package com.ssw.bnk.swintwallet.jdbc.entities;

import java.sql.Date;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Hicham ELKASMI
 */
@Getter @Setter 
public class SSEnt_MoneyTransfer {
    
    private String transactiondate;
    private String terminaltype ;
    private String terminalcode ;
    private String entitycodefrom;
    private String entitytypefrom;
    private String entitycodeto;
    private String entitytypeto;
    private String amount;
    private String currencycode;
    private String additionaldata;
    
    public SSEnt_MoneyTransfer() {
        
    }
        
    public SSEnt_MoneyTransfer(String transactiondate, String terminaltype, String terminalcode, String entitycodefrom, String entitytypefrom, String entitycodeto, String entitytypeto, String amount, String currencycode, String additionaldata) {
        this.transactiondate = transactiondate;
        this.terminaltype = terminaltype;
        this.terminalcode = terminalcode;
        this.entitycodefrom = entitycodefrom;
        this.entitytypefrom = entitytypefrom;
        this.entitycodeto = entitycodeto;
        this.entitytypeto = entitytypeto;
        this.amount = amount;
        this.currencycode = currencycode;
        this.additionaldata = additionaldata;
    }

}
