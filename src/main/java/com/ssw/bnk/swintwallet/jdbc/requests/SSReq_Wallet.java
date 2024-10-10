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
public class SSReq_Wallet extends SSReq_Base{
    
    @Getter @Setter 
    private SSEnt_Initiator initiator;

    public SSReq_Wallet() {
    }

    public SSReq_Wallet(SSEnt_Header header, SSEnt_Initiator initiator) {
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
    }
    
    
    
}
