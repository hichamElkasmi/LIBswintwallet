package com.ssw.bnk.swintwallet.jdbc.requests;

import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_Header;
import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_Criteria;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author MOKHLISS
 */
public class SSReq_Info extends SSReq_Base{

    @Getter @Setter 
    SSEnt_Criteria criteria;

    public SSReq_Info() {
    }

    public SSReq_Info(SSEnt_Header header, SSEnt_Criteria criteria) {
        super(header);
        this.criteria = criteria;
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
        stream.writeString(criteria.getNationalid());
        stream.writeString("");
        stream.writeString(criteria.getBankcode());
    }
    
    
    
}
