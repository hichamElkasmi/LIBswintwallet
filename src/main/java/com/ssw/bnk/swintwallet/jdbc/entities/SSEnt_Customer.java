package com.ssw.bnk.swintwallet.jdbc.entities;

import com.owlike.genson.annotation.JsonIgnore;
import java.sql.Date;
import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

/**
 *
 * @author MOKHLISS
 */
public class SSEnt_Customer extends SSEnt_Data implements SQLData{

    private final String SQL_TYPE="WALLET.CUSTINFO";
    

    public SSEnt_Customer(){
        
    }
 
    @JsonIgnore
    @Override
    public String getSQLTypeName() throws SQLException {
        return SQL_TYPE;
    }

    @Override
    public void readSQL(SQLInput stream, String typeName) throws SQLException {
            enfirstname         =   stream.readString();      
            enlastname          =   stream.readString();      
            arfirstname         =   stream.readString();      
            arlastname          =   stream.readString();      
            gender              =   stream.readString();                  
            title               =   String.valueOf(stream.readInt());      
            dateob              =   String.valueOf(stream.readDate());      
            placeob             =   stream.readString();      
            mobile              =   stream.readString();      
            email               =   stream.readString();      
            arr_address         =   stream.readArray();      
            bankcode            =   stream.readString();      
            branchcode          =   stream.readString();      
            nationalid          =   stream.readString();      
            nationality         =   stream.readString();      
            accountprogramcode  =   stream.readString();      
            accountnumber       =   stream.readString();      
            passportnumber      =   stream.readString();      
            passportexpiration  =  String.valueOf(stream.readDate());  
    }

    @Override
    public void writeSQL(SQLOutput stream) throws SQLException {
            stream.writeString(enfirstname);      
            stream.writeString(enlastname);      
            stream.writeString(arfirstname);      
            stream.writeString(arlastname);      
            stream.writeString(gender);      
            stream.writeInt(Integer.valueOf(title));   
            stream.writeDate(Date.valueOf(dateob));
            stream.writeString(placeob);      
            stream.writeString(mobile);      
            stream.writeString(email);      
            stream.writeArray(arr_address);      
            stream.writeString(bankcode);      
            stream.writeString(branchcode);      
            stream.writeString(nationalid);      
            stream.writeString(nationality);      
            stream.writeString(accountprogramcode);      
            stream.writeString(accountnumber);      
            stream.writeString(passportnumber);      
            stream.writeDate(Date.valueOf(passportexpiration));  
    }
}
