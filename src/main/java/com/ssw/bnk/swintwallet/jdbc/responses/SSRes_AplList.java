package com.ssw.bnk.swintwallet.jdbc.responses;

import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_Auth;
import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_Header;
import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_APL;
import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_Limits;
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
public class SSRes_AplList extends SSRes_Base{

    private Array  accProgramList;
    
    @Getter @Setter 
    private SSEnt_APL[] apl;

    public SSRes_AplList() {
        super();
        apl = null;
    }

    public SSRes_AplList(SSEnt_Auth sSEnt_Auth) {
        super(sSEnt_Auth);
        apl = null;
    }
    
    public SSRes_AplList(SSEnt_Header header, SSEnt_APL[] apl, SS_Stt status) {
        super(header,status);
        this.apl = apl;
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
                accProgramList = stream.readArray();
                parseAccProgramList();
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
    
    private void parseAccProgramList(){

        try {
                Object[] objstructs =  (Object[])accProgramList.getArray();
                apl = null;
                if(objstructs == null || objstructs.length==0){
                    status = new SS_Stt(scode(ST_NODATA), smsg(ST_NODATA),
                                        ecode(ER_CLEAN), emsg(ER_CLEAN));
                }
                else{
                    apl = new SSEnt_APL[objstructs.length];
                    int i =0;
                    for(Object obj : objstructs){
                           Struct s= (Struct)obj;
                            if(s != null ){

                                   Object [] str = (Object[]) s.getAttributes();
                                   Object[] objstructsin =  (Object[])((Array)str[2]).getArray();
                                   SSEnt_Limits[] sSEnt_Limitses = null;
                                   if(objstructsin.length>0){
                                        sSEnt_Limitses = new SSEnt_Limits[objstructsin.length];
                                        int j=0;
                                        for(Object objin : objstructsin){
                                            Struct sin= (Struct)objin;
                                            if(sin != null ){
                                                Object [] strin = (Object[]) sin.getAttributes();
                                                sSEnt_Limitses[j] =  new SSEnt_Limits((String)strin[0], (String)strin[1], (String)strin[2]);
                                            }
                                            j++;
                                        }
                                   }
                                   apl[i] =  new SSEnt_APL((String)str[0], (String)str[1], sSEnt_Limitses);
                             }
                            i++;
                       }
                }
            }catch (Exception ex) {
                apl = null;
                status = new SS_Stt(scode(ST_ERROR), smsg(ST_ERROR),
                        ecode(ER_PARSE), emsg(ER_PARSE)+ex.getMessage());
            }
    }
    
}
