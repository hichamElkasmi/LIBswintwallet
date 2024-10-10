package com.ssw.bnk.swintwallet.jdbc.requests;

import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_Header;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author MOKHLISS
 */

public class SSReq_HeadBank extends SSReq_Base{
    
    @Getter @Setter 
    private String bankcode;
    
    public SSReq_HeadBank() {
    }
        
    public SSReq_HeadBank(SSEnt_Header header, String bankcode){
        super(header);
        this.bankcode = bankcode;
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
        stream.writeString(bankcode);
    } 
}
