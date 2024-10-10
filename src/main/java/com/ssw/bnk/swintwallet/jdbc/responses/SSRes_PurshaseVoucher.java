package com.ssw.bnk.swintwallet.jdbc.responses;

import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_Auth;
import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_Authorization;
import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_Header;
import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_VoucherData;
import com.ssw.bnk.swintwallet.jdbc.in.SS_Stt;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;
import lombok.Getter;
import lombok.Setter;
import static com.ssw.bnk.swintwallet.jdbc.in.SS_Df.*;
import java.sql.Date;
import java.sql.Struct;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author Hicham ELKASMI
 */
public class SSRes_PurshaseVoucher extends SSRes_Transaction{
    
    @Getter @Setter 
    private SSEnt_VoucherData voucherDT;
    
    public SSRes_PurshaseVoucher() {
        super();
        voucherDT = null;
    }
    
    public SSRes_PurshaseVoucher(SSEnt_Auth sSEnt_Auth) {
        super(sSEnt_Auth);
        voucherDT = null;
    }
    
    public SSRes_PurshaseVoucher(SSEnt_Header header, SSEnt_Authorization authorization, SSEnt_VoucherData voucherdt, SS_Stt status) {
        super(header,authorization,status);
        voucherDT = voucherdt;
        
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
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
                authorization = new SSEnt_Authorization(errorcode, errorcode, formatter.format(calendar.getTime()));//add time here just from java
                authorization.setReferenceid(stream.readString());
                authorization.setAuthcode(stream.readString());
                
                Object  voucherSS = stream.readObject();
                Object [] strin = (Object[]) ((Struct)voucherSS).getAttributes();
                voucherDT = new SSEnt_VoucherData ();
                voucherDT.setPincode((String)strin[0]);
                voucherDT.setVoucherserial((String)strin[1]); 
                voucherDT.setExpirydate((new Date(((Timestamp)strin[2]).getTime())).toString());
                voucherDT.setOtherinfo((String)strin[3]);         
            }
            else
            {
                status = new SS_Stt(scode(ST_REJECTED), smsg(ST_REJECTED),errorcode, errordesc);
            }

        }
        catch(Exception ex){
                status = new SS_Stt(scode(ST_ERROR), smsg(ST_ERROR),ecode(ER_STREAM), emsg(ER_STREAM)+ex.getMessage());
        }
    }

    @Override
    public void writeSQL(SQLOutput stream) throws SQLException {

    }
    
}
