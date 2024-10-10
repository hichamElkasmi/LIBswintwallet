package com.ssw.bnk.swintwallet.jdbc.requests;

import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_Header;
import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author MOKHLISS
 */

public class SSReq_Base implements SQLData{
    
    protected String sqlType;
    
    @Getter @Setter 
    protected SSEnt_Header header;
    

    public SSReq_Base() {
    }

    public SSReq_Base(SSEnt_Header header) {
        this.header = header;
    }
    public void setSqlType(String sqlType) {
        this.sqlType = sqlType;
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
    }
}
