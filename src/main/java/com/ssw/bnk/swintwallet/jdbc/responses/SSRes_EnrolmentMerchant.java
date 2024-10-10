package com.ssw.bnk.swintwallet.jdbc.responses;

import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_Identity;
import com.ssw.bnk.swintwallet.jdbc.in.SS_Stt;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

import static com.ssw.bnk.swintwallet.jdbc.in.SS_Df.*;

/**
 *
 * @author MOKHLISS
 */
public class SSRes_EnrolmentMerchant extends SSRes_Enrolment{

    public SSRes_EnrolmentMerchant() {
        super();
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
                stream.readObject();
                identity = new SSEnt_Identity(stream.readString());
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
