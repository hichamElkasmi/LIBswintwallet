package com.ssw.bnk.swintwallet.jdbc.responses;

import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_Auth;
import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_Authorization;
import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_Header;
import com.ssw.bnk.swintwallet.jdbc.in.SS_Stt;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import lombok.Getter;
import lombok.Setter;

import static com.ssw.bnk.swintwallet.jdbc.in.SS_Df.*;

/**
 *
 * @author MOKHLISS
 */
public class SSRes_Transaction extends SSRes_Base{
    
    @Getter @Setter 
     SSEnt_Authorization authorization;
        
    public SSRes_Transaction() {
        super();
        authorization = null;
    }
    
    public SSRes_Transaction(SSEnt_Auth sSEnt_Auth) {
        super(sSEnt_Auth);
        authorization = null;
    }
    
    public SSRes_Transaction(SSEnt_Header header, SSEnt_Authorization authorization, SS_Stt status) {
        super(header,status);
        this.authorization = authorization;
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
            if(errorcode.equalsIgnoreCase(OK_SS)){
                authorization = new SSEnt_Authorization();
                authorization.setReferenceid(stream.readString());
                authorization.setAuthcode(stream.readString());
                Instant instant = ((Timestamp)stream.readTimestamp()).toInstant() ;
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/YY hh:mm:ss");
                authorization.setAuthDate(dateFormat.format(java.util.Date.from(instant)));
            }
            else
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
