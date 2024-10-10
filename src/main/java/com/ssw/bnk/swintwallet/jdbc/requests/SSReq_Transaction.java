package com.ssw.bnk.swintwallet.jdbc.requests;

import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_Header;
import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_Initiator;
import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_Request;
import lombok.Getter;
import lombok.Setter;


/**
 *
 * @author MOKHLISS
 */

public class SSReq_Transaction extends SSReq_Base {
    @Getter @Setter 
    protected SSEnt_Initiator initiator;
    @Getter @Setter 
    protected SSEnt_Request   request;


    public SSReq_Transaction() {
    }
    
    public SSReq_Transaction(SSEnt_Header header, SSEnt_Initiator initiator, SSEnt_Request request) {
        super(header);
        this.initiator = initiator;
        this.request = request;
    }

}
