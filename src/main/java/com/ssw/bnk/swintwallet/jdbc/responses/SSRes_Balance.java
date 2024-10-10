package com.ssw.bnk.swintwallet.jdbc.responses;

import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_Auth;
import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_Header;
import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_Balance;
import com.ssw.bnk.swintwallet.jdbc.in.SS_Stt;
import java.math.BigDecimal;
import java.sql.Array;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;
import java.sql.Struct;
import lombok.Getter;
import lombok.Setter;

import static com.ssw.bnk.swintwallet.jdbc.in.SS_Df.*;

/**
 *
 * @author MOKHLISS
 */
public class SSRes_Balance extends SSRes_Base{
    
    private Array  entitybal;
    @Getter @Setter 
    private SSEnt_Balance balance;

    public SSRes_Balance() {
        super();
        balance = null;
    }

    public SSRes_Balance(SSEnt_Auth sSEnt_Auth) {
        super(sSEnt_Auth);
        balance = null;
    }
    
    public SSRes_Balance(SSEnt_Header header, SSEnt_Balance balance, SS_Stt status) {
        super(header,status);
        this.balance = balance;
    }
    
    @Override
    public String getSQLTypeName() throws SQLException {
        return sqlType;
    }

    @Override
    public void readSQL(SQLInput stream, String typeName){
        
        status = new SS_Stt(scode(ST_APPROVED), smsg(ST_APPROVED),
                          ecode(ER_CLEAN), emsg(ER_CLEAN));
      
        try{
            stream.readString();//requestid
            errorcode = stream.readString();
            errordesc = stream.readString();
            if(errorcode.equalsIgnoreCase(OK_SS)){
                entitybal = stream.readArray();
                parseEntitybal();
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
    
    public void parseEntitybal(){

        try {
                Object[] objstructs =  (Object[])entitybal.getArray();
                balance = null;
                if(objstructs == null || objstructs.length!=1)
                    status = new SS_Stt(scode(ST_NODATA), smsg(ST_NODATA),
                                        ecode(ER_CLEAN), emsg(ER_CLEAN));
                else{
                    for(Object obj : objstructs){
                           Struct s= (Struct)obj;
                            if(s != null ){

                                   Object [] str = (Object[]) s.getAttributes();
                                   balance =  new SSEnt_Balance(((BigDecimal)str[0]).toString(), (String)str[1]);
                             }
                       }
                }
            }catch (Exception ex) {
                balance = null;
                status = new SS_Stt(scode(ST_ERROR), smsg(ST_ERROR),
                             ecode(ER_PARSE), emsg(ER_PARSE)+ex.getMessage());
            }    
    }
}
