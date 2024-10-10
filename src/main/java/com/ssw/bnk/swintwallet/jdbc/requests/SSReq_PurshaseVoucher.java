/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssw.bnk.swintwallet.jdbc.requests;

import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_Header;
import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_Initiator;
import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_Request;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author HICHAM ELKAMSI
 */
public class SSReq_PurshaseVoucher extends SSReq_Base{
    @Getter @Setter 
    protected SSEnt_Initiator initiator;
    @Getter @Setter 
    protected SSEnt_Request   request;


    public SSReq_PurshaseVoucher() {
    }
    
    public SSReq_PurshaseVoucher(SSEnt_Header header, SSEnt_Initiator initiator, SSEnt_Request request) {
        super(header);
        this.initiator = initiator;
        this.request = request;
    }
}
