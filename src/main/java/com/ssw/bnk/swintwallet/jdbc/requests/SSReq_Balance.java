package com.ssw.bnk.swintwallet.jdbc.requests;

import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_Header;
import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_Initiator;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author MOKHLISS
 */
public class SSReq_Balance extends SSReq_Base{
    
    @Getter @Setter 
    private SSEnt_Initiator initiator;

    public SSReq_Balance() {
    }
        
    public SSReq_Balance(SSEnt_Header header, SSEnt_Initiator initiator) {
        super(header);
        this.initiator = initiator;
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
      stream.writeString(initiator.getType());
       stream.writeString(initiator.getIdentifier());
        stream.writeString(initiator.getCardcode());
        stream.writeString("WALLET");
    }

    
}
