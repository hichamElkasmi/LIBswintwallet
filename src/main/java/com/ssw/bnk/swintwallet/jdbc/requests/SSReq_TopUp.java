package com.ssw.bnk.swintwallet.jdbc.requests;

import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;
import java.sql.Timestamp;


/**
 *
 * @author MOKHLISS
 */
public class SSReq_TopUp extends SSReq_Transaction{
    
    public SSReq_TopUp(SSReq_Transaction sSReq_Transaction) {
        
        super(sSReq_Transaction.header,sSReq_Transaction.initiator,sSReq_Transaction.request);
        
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
        try{
            stream.writeString(header.getIdmsg());
            stream.writeTimestamp(Timestamp.valueOf(request.getDate()+".000000"));
            stream.writeString("TC");
         stream.writeString(initiator.getIdentifier());
            stream.writeString(initiator.getCardcode());
            stream.writeString("WALLET");        
            stream.writeDouble(Double.valueOf(request.getAmount()));
            stream.writeString(request.getCurrency());
            stream.writeString(request.getLabel());
            stream.writeString(request.getTrxtype());
        }
        catch(Exception e){
            request = null;
        }

    }    
}
