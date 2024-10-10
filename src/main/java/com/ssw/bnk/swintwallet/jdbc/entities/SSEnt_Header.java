package com.ssw.bnk.swintwallet.jdbc.entities;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author MOKHLISS
 */
@Getter @Setter 
public class SSEnt_Header {
    
    private String idmsg;
    private String mac;

    public SSEnt_Header() {
    }
    
    public SSEnt_Header(String idmsg, String mac) {
        this.idmsg = idmsg;
        this.mac = mac;
    }
        
}
