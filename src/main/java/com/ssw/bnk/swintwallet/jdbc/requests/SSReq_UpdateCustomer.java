package com.ssw.bnk.swintwallet.jdbc.requests;

import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_Header;
import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_Data;
import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_Identity;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author MOKHLISS
 */
public class SSReq_UpdateCustomer extends SSReq_Base{
    
    
    @Getter @Setter 
    private SSEnt_Identity identity;
    @Getter @Setter 
    private SSEnt_Data customer;

    public SSReq_UpdateCustomer(SSEnt_Header header, SSEnt_Data customer, SSEnt_Identity identity) {
        super(header);
        this.customer = customer;
        this.identity = identity;
    }

    public void setSqlType(String sqlType, Connection con) {
        setSqlType(sqlType);
        customer.setArrayMembers(con);
    }
    
    @Override
    public String getSQLTypeName() throws SQLException {
        return sqlType;
    }

    @Override
    public void readSQL(SQLInput stream, String typeName) throws SQLException {

    }

    @Override
    public void writeSQL(SQLOutput stream) throws SQLException {
        stream.writeString(header.getIdmsg());
        stream.writeString(identity.getWalletcode());
        stream.writeObject(customer);
    }

}
