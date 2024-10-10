package com.ssw.bnk.swintwallet.jdbc.responses;

import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_Identity;
import com.ssw.bnk.swintwallet.jdbc.in.SS_Stt;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;
import java.sql.Struct;

import static com.ssw.bnk.swintwallet.jdbc.in.SS_Df.*;


/**
 *
 * @author MOKHLISS
 */
public class SSRes_EnrolmentCustomer extends SSRes_Enrolment{
    
    private Struct wrResult;
    
    
    public SSRes_EnrolmentCustomer(){
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
                wrResult = (Struct) stream.readObject();
                parseWrResult();
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
    
    public void parseWrResult(){
        
        try {
                    if(wrResult!=null){
                        Object [] str = (Object[]) wrResult.getAttributes();
                        identity = new SSEnt_Identity((String)str[0], (String)str[1]);
                    }
            }catch (Exception ex) {
                wrResult=null;
                status = new SS_Stt(scode(ST_ERROR), smsg(ST_ERROR),
                        ecode(ER_PARSE), emsg(ER_PARSE)+ex.getMessage());
           }          
    }
}
