package com.ssw.bnk.swintwallet.jdbc.entities;

import com.ssw.bnk.swintwallet.jdbc.in.SS_Stt;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author MOKHLISS
 */
@Getter @Setter 
public class SSEnt_Auth {
    
    public static final String OK="0";
    
    private SSEnt_Header header;
    private SS_Stt status;

    public SSEnt_Auth() {
    }
        
    public SSEnt_Auth(SSEnt_Header header, SS_Stt status) {
        this.header = header;
        this.status = status;
    }

    public boolean checkStatus(){
        return status.codestatus.equals(OK);
    }
}
