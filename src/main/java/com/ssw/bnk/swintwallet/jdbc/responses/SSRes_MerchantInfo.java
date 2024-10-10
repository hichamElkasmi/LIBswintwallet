package com.ssw.bnk.swintwallet.jdbc.responses;

import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_Auth;
import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_Address;
import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_Header;
import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_Merchant;
import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_PaymentMode;
import com.ssw.bnk.swintwallet.jdbc.in.SS_Stt;
import java.math.BigDecimal;
import java.sql.Array;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;
import java.sql.Struct;
import java.sql.Timestamp;
import lombok.Getter;
import lombok.Setter;

import static com.ssw.bnk.swintwallet.jdbc.in.SS_Df.*;

/**
 *
 * @author MOKHLISS
 */
public class SSRes_MerchantInfo extends SSRes_Base{
    
    private Array  merchantData;
    
    @Getter @Setter 
    private SSEnt_Merchant merchant;

    public SSRes_MerchantInfo() {
        super();
        merchant = null;
    }

    public SSRes_MerchantInfo(SSEnt_Auth sSEnt_Auth) {
        super(sSEnt_Auth);
        merchant = null;
    }
    
    public SSRes_MerchantInfo(SSEnt_Header header, SSEnt_Merchant merchant, SS_Stt status) {
        super(header,status);
        this.merchant = merchant;
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
                merchantData = stream.readArray();
                parseMerchantData();
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
   
    public void parseMerchantData(){
        
        try {
                Object[] objstructs =  (Object[])merchantData.getArray();
                merchant = null;
                if(objstructs == null || objstructs.length==0){
                    status = new SS_Stt(scode(ST_NODATA), smsg(ST_NODATA),
                                    ecode(ER_CLEAN), emsg(ER_CLEAN));
                }
                else{
                    merchant = new SSEnt_Merchant();
                    Object [] strin = (Object[]) ((Struct)objstructs[0]).getAttributes();
                    merchant.setEnfirstname((String)strin[0]);
                    merchant.setEnlastname((String)strin[1]);
                    merchant.setGender((String)strin[2]);
                    if(strin[3]!=null)
                        merchant.setTitle(((BigDecimal)strin[3]).toString());
                    if(strin[4]!=null)
                        merchant.setDateob((new Date(((Timestamp)strin[4]).getTime())).toString());
                    merchant.setPlaceob((String)strin[5]);
                    merchant.setMobile((String)strin[6]);
                    merchant.setEmail((String)strin[7]);
                    merchant.setBankcode((String)strin[8]);
                    merchant.setBranchcode((String)strin[9]);
                    merchant.setNationalid((String)strin[10]);
                    merchant.setNationality((String)strin[11]);
                    merchant.setAccountnumber((String)strin[12]);
                    merchant.setMerprogramcode((String)strin[13]);
                    merchant.setMeractivitycode((String)strin[15]);
                    //merchant.setMerpaymentmode((String)strin[18]);

                    SSEnt_Address[] sSEnt_Address = new SSEnt_Address[1];
                    sSEnt_Address[0] = new SSEnt_Address("01", "01", (String)strin[20],(String)strin[21],
                            (String)strin[22],(String)strin[23],(String)strin[24],(String)strin[25],(String)strin[26]);
                    merchant.setAddress(sSEnt_Address);
                    merchant.setMerchantid((String)strin[27]);
                    merchant.setMerchantname((String)strin[28]);
                    merchant.setPassportnumber((String)strin[29]);
                    if(strin[30]!=null)
                        merchant.setPassportexpiration((new Date(((Timestamp)strin[30]).getTime())).toString());

                    if(strin[18]!=null)
                    {
                        Object[] objstructsin =  (Object[])((Array)strin[18]).getArray();
                        if(objstructsin.length>0){
                             SSEnt_PaymentMode[] sSEnt_PaymentModes = new SSEnt_PaymentMode[objstructsin.length];
                             int j=0;
                             for(Object objin : objstructsin){
                                 Struct sin= (Struct)objin;
                                 if(sin != null ){
                                     Object [] strin1 = (Object[]) sin.getAttributes();
                                     sSEnt_PaymentModes[j] =  new SSEnt_PaymentMode((String)strin1[0], (String)strin1[1],
                                             (String)strin1[2], (String)strin1[3]);
                                 }
                                 j++;
                             }
                             merchant.setMerpaymentmode(sSEnt_PaymentModes);
                        }
                    }
                }
            }catch (Exception ex) {
                merchant = null;
                status = new SS_Stt(scode(ST_ERROR), smsg(ST_ERROR),
                        ecode(ER_PARSE), emsg(ER_PARSE)+ex.getMessage());
            }          
    }  
}
