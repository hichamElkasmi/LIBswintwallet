package com.ssw.bnk.swintwallet.jdbc.entities;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author MOKHLISS
 */
@Getter @Setter 
public class SSEnt_Trasansaction {
    
    private String transnumber;
    private String transdate;
    private String translabel;
    private String transamount;
    private String transtype;
    private String transsupport;

    public SSEnt_Trasansaction() {
    }

    public SSEnt_Trasansaction(String transnumber, String transdate, String translabel, String transamount, String transtype, String transsupport) {
        this.transnumber = transnumber;
        this.transdate = transdate;
        this.translabel = translabel;
        this.transamount = transamount;
        this.transtype = transtype;
        this.transsupport = transsupport;
    }

}
