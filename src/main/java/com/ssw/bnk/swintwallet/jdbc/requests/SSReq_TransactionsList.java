package com.ssw.bnk.swintwallet.jdbc.requests;

import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_Header;
import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_Filter;
import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_Initiator;
import com.ssw.bnk.swintwallet.jdbc.in.SS_Chk;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;
import java.sql.Timestamp;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author MOKHLISS
 */
public class SSReq_TransactionsList extends SSReq_Base{
    
    @Getter @Setter 
    private SSEnt_Initiator initiator;
    @Getter @Setter 
    private SSEnt_Filter filter;

    public SSReq_TransactionsList() {
    }

    public SSReq_TransactionsList(SSEnt_Header header, SSEnt_Initiator initiator, SSEnt_Filter filter) {
        super(header);
        this.initiator = initiator;
        this.filter = filter;
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
        if(filter.getNtrans() == null || filter.getNtrans().isEmpty())
            stream.writeDouble(Double.valueOf(SS_Chk.LEN_MXTRX));
        else
            stream.writeDouble(Double.valueOf(filter.getNtrans()));
        if(filter.getTransactiontype() == null || filter.getTransactiontype().isEmpty())
            stream.writeString(null);
        else
            stream.writeString(filter.getTransactiontype().toUpperCase());
        if(filter.getIdtrans()== null || filter.getIdtrans().isEmpty())
            stream.writeString(null);
        else
            stream.writeString(filter.getIdtrans());
        if(filter.getStartdate() == null || filter.getStartdate().isEmpty())
            stream.writeTimestamp(null);
        else
            stream.writeTimestamp(Timestamp.valueOf(filter.getStartdate()+" 00:00:00.000"));
        if(filter.getEnddate() == null || filter.getEnddate().isEmpty())
            stream.writeTimestamp(null);
        else
            stream.writeTimestamp(Timestamp.valueOf(filter.getEnddate()+" 23:59:59.000"));
    }
   
}
