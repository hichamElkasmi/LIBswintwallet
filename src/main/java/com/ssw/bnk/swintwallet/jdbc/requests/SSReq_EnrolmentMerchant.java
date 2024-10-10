package com.ssw.bnk.swintwallet.jdbc.requests;

import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_Header;
import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_Merchant;
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

public class SSReq_EnrolmentMerchant extends SSReq_Base{
    
    @Getter @Setter 
    private SSEnt_Merchant merchant;

    public SSReq_EnrolmentMerchant(SSEnt_Header header, SSEnt_Merchant merchant) {
        super(header);
        this.merchant = merchant;
    }

    public void setSqlType(String sqlType, Connection con) {
        setSqlType(sqlType);
        merchant.setArrayMembers(con);
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
        stream.writeObject(merchant);
    }
        
}
