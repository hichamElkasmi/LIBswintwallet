/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 * @author rmokh
 */
@Getter @Setter 
public class SSEnt_Data implements SQLData{
    @Getter(AccessLevel.NONE) @Setter(AccessLevel.NONE) 
    private final String SQL_TYPE="WALLET.WALLETINFO";
    @Getter(AccessLevel.NONE) @Setter(AccessLevel.NONE) 
    protected final String SQL_SUB_TYPE_ADR="WALLET.ADRESSLIST";
    @Getter(AccessLevel.NONE) @Setter(AccessLevel.NONE) 
    protected Array arr_address;
    
    protected String enfirstname;
    protected String enlastname;
    protected String arfirstname;
    protected String arlastname;
    protected String gender;
    protected String title;
    protected String dateob;
    protected String placeob;
    protected String mobile;
    protected String email;
    protected SSEnt_Address[] address;
    protected String bankcode;
    protected String branchcode;
    protected String nationalid;
    protected String nationality;
    protected String accountprogramcode;
    protected String accountnumber;
    protected String passportnumber;
    protected String passportexpiration;
    private String  customercode;

    public SSEnt_Data() {
    }

    public SSEnt_Data(String enfirstname, String enlastname, String arfirstname, String arlastname, String gender, String title, String dateob, String placeob, String mobile, String email, SSEnt_Address[] address, String bankcode, String branchcode, String nationalid, String accountprogramcode, String customercode, String accountnumber, String passportnumber, String passportexpiration) {
        this.enfirstname = enfirstname;
        this.enlastname = enlastname;
        this.arfirstname = arfirstname;
        this.arlastname = arlastname;
        this.gender = gender;
        this.title = title;
        this.dateob = dateob;
        this.placeob = placeob;
        this.mobile = mobile;
        this.email = email;
        this.address = address;
        this.bankcode = bankcode;
        this.branchcode = branchcode;
        this.nationalid = nationalid;
        this.accountprogramcode = accountprogramcode;
        this.customercode = customercode;
        this.accountnumber = accountnumber;
        this.passportnumber = passportnumber;
        this.passportexpiration = passportexpiration;
    }

    public void setArrayMembers(Connection con) {
        setArrAddress(con);
    }
    
    private void setArrAddress(Connection con) {
        
        try {

            OracleConnection oracleConnection= null;
            if (con.isWrapperFor(OracleConnection.class)) {
                oracleConnection = con.unwrap(OracleConnection.class);
            }
            if(oracleConnection!=null)
                arr_address = oracleConnection.createARRAY(SQL_SUB_TYPE_ADR, address);
            else
                arr_address = null;
        
        } catch (Exception ex) {
            arr_address = null;
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
        stream.writeString(nationality);      
    }

}
