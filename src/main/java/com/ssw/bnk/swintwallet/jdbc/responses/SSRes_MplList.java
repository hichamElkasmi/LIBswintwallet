package com.ssw.bnk.swintwallet.jdbc.responses;

import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_Auth;
import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_Activities;
import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_Header;
import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_MPL;
import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_PaymentMode;
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
public class SSRes_MplList extends SSRes_Base{
    
    private Array  programData;
    
    @Getter @Setter 
    private SSEnt_MPL[] mpl;

    public SSRes_MplList() {
        super();
        mpl = null;
    }

    public SSRes_MplList(SSEnt_Auth sSEnt_Auth) {
        super(sSEnt_Auth);
        mpl = null;
    }
        
    public SSRes_MplList(SSEnt_Header header, SSEnt_MPL[] mpl, SS_Stt status) {
        super(header,status);
        this.mpl = mpl;
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
                programData = stream.readArray();
                parseProgramData();
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
    
    public void parseProgramData(){
        
        try {
                Object[] objstructs =  (Object[])programData.getArray();
                mpl = null;
                if(objstructs.length==0){
                    status = new SS_Stt(scode(ST_NODATA), smsg(ST_NODATA),
                                        ecode(ER_CLEAN), emsg(ER_CLEAN));
                }
                else{
                    mpl = new SSEnt_MPL[objstructs.length];
                    int i =0;
                    for(Object obj : objstructs){
                           Struct s= (Struct)obj;
                            if(s != null ){
                                   Object [] str = (Object[]) s.getAttributes();
                                   Object[] objstructsin =  (Object[])((Array)str[2]).getArray();
                                   SSEnt_Activities[] sSEnt_Activitieses = null;
                                   if(objstructsin.length>0){
                                        sSEnt_Activitieses = new SSEnt_Activities[objstructsin.length];
                                        int j=0;
                                        for(Object objin : objstructsin){
                                            Struct sin= (Struct)objin;
                                            if(sin != null ){
                                                Object [] strin = (Object[]) sin.getAttributes();
                                                sSEnt_Activitieses[j] =  new SSEnt_Activities((String)strin[0], (String)strin[1], (String)strin[2]);
                                            }
                                            j++;
                                        }
                                   }
                                   objstructsin =  (Object[])((Array)str[3]).getArray();
                                   SSEnt_PaymentMode[] sSEnt_PaymentModes = null;
                                   if(objstructsin.length>0){
                                        sSEnt_PaymentModes = new SSEnt_PaymentMode[objstructsin.length];
                                        int j=0;
                                        for(Object objin : objstructsin){
                                            Struct sin= (Struct)objin;
                                            if(sin != null ){
                                                Object [] strin = (Object[]) sin.getAttributes();
                                                sSEnt_PaymentModes[j] =  new SSEnt_PaymentMode((String)strin[0], (String)strin[1],
                                                        (String)strin[2], (String)strin[3]);
                                            }
                                            j++;
                                        }
                                   }

                                   mpl[i] =  new SSEnt_MPL((String)str[0], (String)str[1], sSEnt_Activitieses, sSEnt_PaymentModes);
                             }
                            i++;
                       }
                }
            }catch (Exception ex) {
                mpl = null;
                status = new SS_Stt(scode(ST_ERROR), smsg(ST_ERROR),
                        ecode(ER_PARSE), emsg(ER_PARSE)+ex.getMessage());
            }  
    }
    
}
