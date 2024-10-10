package com.ssw.bnk.swintwallet.jdbc.entities;

import com.owlike.genson.annotation.JsonIgnore;
import java.sql.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import oracle.jdbc.OracleConnection;

/**
 *
 * @author MOKHLISS
 */
@Getter @Setter 
public class SSEnt_Merchant extends SSEnt_Data implements SQLData{
    @Getter(AccessLevel.NONE) @Setter(AccessLevel.NONE) 
    private final String SQL_TYPE="WALLET.MERINFO";
    @Getter(AccessLevel.NONE) @Setter(AccessLevel.NONE) 
    private final String SQL_SUB_TYPE_PAY="WALLET.MERPAYMODDATA";
    
    @Getter(AccessLevel.NONE) @Setter(AccessLevel.NONE) 
    private Array paymentMode;        
            
    private String merchantid;
    private String merchantname;
    private String merprogramcode;
    private String meractivitycode;
    private SSEnt_Identity identity;
    private SSEnt_PaymentMode[] merpaymentmode;

    public SSEnt_Merchant() {
    }

    public SSEnt_Merchant(String merchantid, String merchantname, String merprogramcode, String meractivitycode, SSEnt_PaymentMode[] merpaymentmode, String enfirstname, String enlastname, String gender, String title, String dateob, String placeob, String mobile, String email, SSEnt_Address[] address, String bankcode, String branchcode, String nationalid, String accountprogramcode, String customercode, String accountnumber, String passportnumber, String passportexpiration) {
        super(enfirstname, enlastname, null, null, gender, title, dateob, placeob, mobile, email, address, bankcode, branchcode, nationalid, accountprogramcode, customercode, accountnumber, passportnumber, passportexpiration);
        this.merchantid = merchantid;
        this.merchantname = merchantname;
        this.merprogramcode = merprogramcode;
        this.meractivitycode = meractivitycode;
        this.merpaymentmode = merpaymentmode;
    }

    @Override
    public void setArrayMembers(Connection con) {
        super.setArrayMembers(con);
        setPaymentMode(con);
    }
        
    private void setPaymentMode(Connection con) {
        
        try {

            OracleConnection oracleConnection= null;
            if (con.isWrapperFor(OracleConnection.class)) {
                oracleConnection = con.unwrap(OracleConnection.class);
            }
            if(oracleConnection!=null)
                paymentMode = oracleConnection.createARRAY(SQL_SUB_TYPE_PAY, merpaymentmode);
            else
                paymentMode = null;
        
        } catch (Exception ex) {
            paymentMode = null;
        }
    }
    
    @JsonIgnore
    @Override
    public String getSQLTypeName() throws SQLException {
        return SQL_TYPE;
    }

    @Override
    public void readSQL(SQLInput stream, String typeName) throws SQLException {
        
    }

    @Override
    public void writeSQL(SQLOutput stream) throws SQLException {
        stream.writeString(merchantname); 
        stream.writeString(enfirstname); 
        stream.writeString(enlastname);  
        stream.writeString(gender);   
        stream.writeString(title);  
        stream.writeDate(Date.valueOf(dateob)); 
        stream.writeString(placeob); 
        stream.writeString(mobile); 
        stream.writeString(email); 
        stream.writeArray(arr_address); 
        stream.writeString(bankcode); 
        stream.writeString(branchcode); 
        stream.writeString(nationalid); 
        stream.writeString(nationality); 
        stream.writeString(accountnumber); 
        stream.writeString(merprogramcode); 
        stream.writeString(meractivitycode); 
        stream.writeArray(paymentMode); 
        stream.writeString(accountprogramcode); 
        stream.writeString(passportnumber); 
        stream.writeDate(Date.valueOf(passportexpiration)); 
    }

        
}
