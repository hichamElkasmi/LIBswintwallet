package com.ssw.bnk.swintwallet.jdbc.responses;

import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_Auth;
import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_Branchs;
import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_Header;
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
public class SSRes_Branchs extends SSRes_Base{
    
    private Array  branchList;
    
    @Getter @Setter 
    private SSEnt_Branchs[] branchs;

    public SSRes_Branchs() {
        super();
        branchs = null;
    }
    
    public SSRes_Branchs(SSEnt_Auth sSEnt_Auth) {
        super(sSEnt_Auth);
        branchs = null;
    }
    
    public SSRes_Branchs(SSEnt_Header header, SSEnt_Branchs[] branchs, SS_Stt status) {
        super(header,status);
        this.branchs = branchs;
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
                branchList = stream.readArray();
                parseBranchList();
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
    
    public void parseBranchList(){
        
        try {
                Object[] objstructs =  (Object[])branchList.getArray();
                branchs = null;

                if(objstructs == null || objstructs.length==0){
                    
                }
                else{
                    branchs = new SSEnt_Branchs[objstructs.length];
                    int i =0;
                    for(Object obj : objstructs){
                           Struct s= (Struct)obj;
                            if(s != null ){
                                   Object [] str = (Object[]) s.getAttributes();
                                   branchs[i] =  new SSEnt_Branchs((String)str[0], (String)str[1]);
                             }
                            i++;
                       }
                }
            }catch (Exception ex) {
                branchs = null;
                status = new SS_Stt(scode(ST_ERROR), smsg(ST_ERROR),
                        ecode(ER_PARSE), emsg(ER_PARSE)+ex.getMessage());
            }         
    }
}
