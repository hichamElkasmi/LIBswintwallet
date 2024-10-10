package com.ssw.bnk.swintwallet.jdbc.responses;

import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_Auth;
import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_Header;
import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_Wallet;
import com.ssw.bnk.swintwallet.jdbc.in.SS_Stt;
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
public class SSRes_Wallets extends SSRes_Base{
    

    private Array  entitylist;
    
    @Getter @Setter 
    private SSEnt_Wallet[] wallet;

    public SSRes_Wallets() {
        super();
        wallet = null;
    }
   
    public SSRes_Wallets(SSEnt_Auth sSEnt_Auth) {
        super(sSEnt_Auth);
        wallet = null;
    }
        
    public SSRes_Wallets(SSEnt_Header header, SSEnt_Wallet[] wallet, SS_Stt status) {
        super(header,status);
        this.wallet = wallet;
    }
    
    @Override
    public String getSQLTypeName() throws SQLException {
        return sqlType;
    }

    @Override
    public void readSQL(SQLInput stream, String typeName) throws SQLException {
        /*stream.readString();//requestid
        errorcode = stream.readString();
        errordesc = stream.readString();
        entitylist = stream.readArray();*/
        

        status = new SS_Stt(scode(ST_APPROVED), smsg(ST_APPROVED), ecode(ER_CLEAN), emsg(ER_CLEAN));
      
        try{
            stream.readString();//requestid
            errorcode = stream.readString();
            errordesc = stream.readString();
            if(errorcode.equalsIgnoreCase(OK_SS)){
                entitylist = stream.readArray();
                parseEntitylist();
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
    
    public void parseEntitylist(){
        
        try {
                Object[] objstructs =  (Object[])entitylist.getArray();
                wallet = null;
                if(objstructs == null || objstructs.length==0){
                    status = new SS_Stt(scode(ST_NODATA), smsg(ST_NODATA), ecode(ER_CLEAN), emsg(ER_CLEAN));
                }
                else{
                    wallet = new SSEnt_Wallet[objstructs.length];
                    int i =0;
                    for(Object obj : objstructs){
                           Struct s= (Struct)obj;
                            if(s != null ){
                                
                                   Object [] str = (Object[]) s.getAttributes();
                                   wallet[i] =  new SSEnt_Wallet((String)str[0], (String)str[4], (String)str[6], (String)str[2]);
                             }
                            i++;
                       }
                }
            }catch (Exception ex) {
                wallet = null;
                status = new SS_Stt(scode(ST_ERROR), smsg(ST_ERROR),
                        ecode(ER_PARSE), emsg(ER_PARSE)+ex.getMessage());
            }        
    }
}
