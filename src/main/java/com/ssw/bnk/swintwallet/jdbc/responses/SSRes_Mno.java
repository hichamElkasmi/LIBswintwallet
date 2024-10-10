package com.ssw.bnk.swintwallet.jdbc.responses;

import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_Auth;
import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_Header;
import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_Banks;
import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_Denomination;
import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_Mno;
import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_Service;
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
public class SSRes_Mno extends SSRes_Base{
    
    private Array  mnoList;
    
    @Getter @Setter 
    private SSEnt_Mno[] mno;

    public SSRes_Mno() {
        super();
        mno = null;
    }
   
    public SSRes_Mno(SSEnt_Auth sSEnt_Auth) {
        super(sSEnt_Auth);
        mno = null;
    }
        
    public SSRes_Mno(SSEnt_Header header, SSEnt_Mno[] mno, SS_Stt status) {
        super(header,status);
        this.mno = mno;
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
                mnoList = stream.readArray();
                parseMnoList();
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
    
    public void parseMnoList(){
        
        try {
                Object[] objstructs =  (Object[])mnoList.getArray();
                mno = null;
                if(objstructs.length==0){
                    status = new SS_Stt(scode(ST_NODATA), smsg(ST_NODATA),
                                        ecode(ER_CLEAN), emsg(ER_CLEAN));
                }
                else{
                    mno = new SSEnt_Mno[objstructs.length];
                    int i =0;
                    for(Object obj : objstructs){//list operators
                           Struct s= (Struct)obj;
                            if(s != null ){
                                   Object [] str = (Object[]) s.getAttributes();
                                   Object[] objstructsin =  (Object[])((Array)str[3]).getArray();
                                   SSEnt_Service[] sSEnt_Services = null;
                                   if(objstructsin.length>0){
                                        sSEnt_Services = new SSEnt_Service[objstructsin.length];
                                        int j=0;
                                        for(Object objin : objstructsin){//list services
                                            Struct sin= (Struct)objin;
                                            if(sin != null ){
                                                Object [] strin = (Object[]) sin.getAttributes();
                                                Object[] objstructsin1 =  (Object[])((Array)strin[2]).getArray();
                                                SSEnt_Denomination[] sSEnt_Denominations = null;
                                                if(objstructsin1.length>0){
                                                     sSEnt_Denominations = new SSEnt_Denomination[objstructsin1.length];
                                                     int k=0;
                                                     for(Object objin1 : objstructsin1){//list Denominations 
                                                         Struct sin1= (Struct)objin1;
                                                         if(sin1 != null ){
                                                             Object [] strin1 = (Object[]) sin1.getAttributes();
                                                             sSEnt_Denominations[k] =  new SSEnt_Denomination((String)strin1[0], (String)strin1[1]);
                                                         }
                                                         k++;
                                                     }
                                                }
                                                sSEnt_Services[j] =  new SSEnt_Service((String)strin[0], (String)strin[1], sSEnt_Denominations);
                                            }
                                            j++;
                                        }
                                   }
                                 
                                   mno[i] =  new SSEnt_Mno((String)str[0], (String)str[2], (String)str[1], sSEnt_Services);
                             }
                            i++;
                       }
                }
            }catch (Exception ex) {
                mno = null;
                status = new SS_Stt(scode(ST_ERROR), smsg(ST_ERROR),
                        ecode(ER_PARSE), emsg(ER_PARSE)+ex.getMessage());
            }  
    }
}
