package com.ssw.bnk.swintwallet.jdbc.responses;

import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_Auth;
import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_Header;
import static com.ssw.bnk.swintwallet.jdbc.in.SS_Df.ER_CLEAN;
import static com.ssw.bnk.swintwallet.jdbc.in.SS_Df.ER_STREAM;
import static com.ssw.bnk.swintwallet.jdbc.in.SS_Df.OK_SS;
import static com.ssw.bnk.swintwallet.jdbc.in.SS_Df.ST_APPROVED;
import static com.ssw.bnk.swintwallet.jdbc.in.SS_Df.ST_ERROR;
import static com.ssw.bnk.swintwallet.jdbc.in.SS_Df.ST_REJECTED;
import static com.ssw.bnk.swintwallet.jdbc.in.SS_Df.ecode;
import static com.ssw.bnk.swintwallet.jdbc.in.SS_Df.emsg;
import static com.ssw.bnk.swintwallet.jdbc.in.SS_Df.scode;
import static com.ssw.bnk.swintwallet.jdbc.in.SS_Df.smsg;
import com.ssw.bnk.swintwallet.jdbc.in.SS_Stt;
import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;
import java.sql.Struct;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author MOKHLISS
 */
public class SSRes_Base implements SQLData{
    
    protected String sqlType;
    @Getter
    protected String errorcode;
    @Getter
    protected String errordesc;
        
     @Getter @Setter
    protected SSEnt_Header header;
     @Getter @Setter
    protected SS_Stt status;

    public SSRes_Base() {
    }
        
    public SSRes_Base(String idCaller) {
        header = null;
        status = null;
    }
   
    public SSRes_Base(SSEnt_Auth sSEnt_Auth) {
        header = sSEnt_Auth.getHeader();
        status = sSEnt_Auth.getStatus();
    }
        
    public SSRes_Base(SSEnt_Header header, SS_Stt status) {
        this.header = header;
        this.status = status;
    }

    public void setSqlType(String sqlType) {
        this.sqlType = sqlType;
    }
    
    public void updateStatus(String idCaller){
        String  err = status.getMsgerror();
        status.setMsgerror(idCaller + err);
    }
    
    @Override
    public String getSQLTypeName() throws SQLException {
        return sqlType;
    }

    @Override
    public void readSQL(SQLInput stream, String typeName) throws SQLException {
        
        status = new SS_Stt(scode(ST_APPROVED), smsg(ST_APPROVED),
                          ecode(ER_CLEAN), emsg(ER_CLEAN));
      
        try{
            stream.readString();//requestid
            errorcode = stream.readString();
            errordesc = stream.readString();
            if(!errorcode.equalsIgnoreCase(OK_SS))
            {
                status = new SS_Stt(scode(ST_REJECTED), smsg(ST_REJECTED),
                                    errorcode, errordesc);
            }
        }
        catch(Exception ex){
                status = new SS_Stt(scode(ST_ERROR), smsg(ST_ERROR),
                        ecode(ER_STREAM), emsg(ER_STREAM)+ex.getMessage());
        }
    }

    @Override
    public void writeSQL(SQLOutput stream) throws SQLException {
        
    }

}
