package com.ssw.bnk.swintwallet.jdbc.requests;

import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_Header;
import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_Customer;
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

public class SSReq_EnrolmentCustomer extends SSReq_Base{
    
    @Getter @Setter 
    private SSEnt_Customer customer;

    public SSReq_EnrolmentCustomer(SSEnt_Header header, SSEnt_Customer customer) {
        super(header);
        this.customer = customer;
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
        stream.writeObject(customer);
    }

}
