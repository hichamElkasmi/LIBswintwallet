package com.ssw.bnk.swintwallet.jdbc.responses;

import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_Auth;
import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_Header;
import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_Cities;
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
public class SSRes_Cities extends SSRes_Base{
    
    private Array  citiesList;
    
    @Getter @Setter 
    private SSEnt_Cities[] cities;

    public SSRes_Cities() {
        super();
        cities = null;
    }
        
    public SSRes_Cities(SSEnt_Auth sSEnt_Auth) {
        super(sSEnt_Auth);
        cities = null;
    }
        
    public SSRes_Cities(SSEnt_Header header, SSEnt_Cities[] cities, SS_Stt status) {
        super(header,status);
        this.cities = cities;
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
                citiesList = stream.readArray();
                parseCitiesList();
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
    
    public void parseCitiesList(){
        try {
            
                Object[] objstructs =  (Object[])citiesList.getArray();
                cities = null;
                if(objstructs == null || objstructs.length==0){
                    status = new SS_Stt(scode(ST_NODATA), smsg(ST_NODATA),
                                        ecode(ER_CLEAN), emsg(ER_CLEAN));
                }
                else{
                    cities = new SSEnt_Cities[objstructs.length];
                    int i =0;
                    for(Object obj : objstructs){
                           Struct s= (Struct)obj;
                            if(s != null ){

                                   Object [] str = (Object[]) s.getAttributes();
                                   cities[i] =  new SSEnt_Cities((String)str[0], (String)str[1]);
                             }
                            i++;
                       }
                }
            }catch (Exception ex) {
                cities = null;
                status = new SS_Stt(scode(ST_ERROR), smsg(ST_ERROR),
                        ecode(ER_PARSE), emsg(ER_PARSE)+ex.getMessage());
            }  
    }
}
