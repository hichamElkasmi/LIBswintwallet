package com.ssw.bnk.swintwallet.jdbc.responses;

import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_Auth;
import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_Address;
import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_Header;
import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_Customer;
import com.ssw.bnk.swintwallet.jdbc.in.SS_Stt;
import java.math.BigDecimal;
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
public class SSRes_CustomerInfo extends SSRes_Base{
    
    private Object  customerData;
    
    @Getter @Setter 
    private SSEnt_Customer customer;

    public SSRes_CustomerInfo() {
        super();
        customer = null;
    }

    public SSRes_CustomerInfo(SSEnt_Auth sSEnt_Auth) {
        super(sSEnt_Auth);
        customer = null;
    }
    
    public SSRes_CustomerInfo(SSEnt_Header header, SSEnt_Customer customer, SS_Stt status) {
        super(header,status);
        this.customer = customer;
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
                customerData = stream.readObject();
                parseCustomerData();
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
   
    public void parseCustomerData(){
        try {
                customer = null;
                customer = new SSEnt_Customer();
                Object [] strin = (Object[]) ((Struct)customerData).getAttributes();
                customer.setEnfirstname((String)strin[0]);
                customer.setEnlastname((String)strin[1]);
                customer.setArfirstname((String)strin[2]);
                customer.setArlastname((String)strin[3]);
                customer.setGender((String)strin[4]);
                if(strin[5]!=null)
                    customer.setTitle(((BigDecimal)strin[5]).toString());
                if(strin[6]!=null)
                    customer.setDateob((new Date(((Timestamp)strin[6]).getTime())).toString());
                customer.setPlaceob((String)strin[7]);
                customer.setMobile((String)strin[8]);
                customer.setEmail((String)strin[9]);
                customer.setBankcode((String)strin[10]);
                customer.setBranchcode((String)strin[11]);
                customer.setNationalid((String)strin[12]);
                customer.setNationality((String)strin[13]);
               // customer.setAccountnumber((String)strin[15]);
                customer.setCustomercode((String)strin[16]);

                SSEnt_Address[] sSEnt_Address = new SSEnt_Address[1];
                sSEnt_Address[0] = new SSEnt_Address("01", "01", (String)strin[17],(String)strin[18],
                        (String)strin[19],(String)strin[20],(String)strin[21],(String)strin[22],(String)strin[23]);

                customer.setAddress(sSEnt_Address);
                customer.setPassportnumber((String)strin[24]);
                if(strin[23]!=null)
                    customer.setPassportexpiration((new Date(((Timestamp)strin[25]).getTime())).toString());

            }catch (Exception ex) {
                customer = null;
                status = new SS_Stt(scode(ST_ERROR), smsg(ST_ERROR),
                        ecode(ER_PARSE), emsg(ER_PARSE)+ex.getMessage());
            }      
    }
}
