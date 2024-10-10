package com.ssw.bnk.swintwallet.jdbc.responses;

import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_Auth;
import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_Authorization;
import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_Header;
import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_VoucherTranStruct;
import com.ssw.bnk.swintwallet.jdbc.in.SS_Stt;
import java.sql.Array;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;
import java.sql.Struct;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

import static com.ssw.bnk.swintwallet.jdbc.in.SS_Df.*;
import java.util.Calendar;

/**
 *
 * @author MOKHLISS
 */
public class SSRes_HistoryVoucher extends SSRes_Base{
    
    private Array  trxList;
    
    @Getter @Setter 
    private SSEnt_VoucherTranStruct[] transactions;

    public SSRes_HistoryVoucher() {
        super();
        transactions = null;
    }
    
    public SSRes_HistoryVoucher(SSEnt_Auth sSEnt_Auth) {
        super(sSEnt_Auth);
        transactions = null;
    }
    
    public SSRes_HistoryVoucher(SSEnt_Header header, SSEnt_VoucherTranStruct[] transactions, SS_Stt status) {
        super(header,status);
        this.transactions = transactions;
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
                trxList = stream.readArray();
                parseTrxList();
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
    
    public void parseTrxList(){
        String strDate;
        try {
                Object[] objstructs =  (Object[])trxList.getArray();
                transactions = null;
                if(objstructs == null || objstructs.length==0){
                    status = new SS_Stt(scode(ST_NODATA), smsg(ST_NODATA),
                                    ecode(ER_CLEAN), emsg(ER_CLEAN));
                }
                else{
                    transactions = new SSEnt_VoucherTranStruct[objstructs.length];
                    int i =0;
                    for(Object obj : objstructs){
                           Struct s= (Struct)obj;
                            if(s != null ){

                                   Object [] str = (Object[]) s.getAttributes();
                                   try{
                                        Instant instant = ((Timestamp)str[0]).toInstant() ;
                                        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
                                        strDate = dateFormat.format(Date.from(instant));
                                    }catch (Exception ex) {
                                        strDate = null;
                                    }
                                   transactions[i] =  new SSEnt_VoucherTranStruct((String)str[1],(String)str[2],strDate,(String)str[3]);
                             }
                            i++;
                       }
                }
            }catch (Exception ex) {
                transactions = null;
                status = new SS_Stt(scode(ST_ERROR), smsg(ST_ERROR),
                        ecode(ER_STREAM), emsg(ER_STREAM)+ex.getMessage());
            }         
    }
}
