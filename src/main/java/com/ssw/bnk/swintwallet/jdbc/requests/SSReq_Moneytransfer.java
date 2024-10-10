package com.ssw.bnk.swintwallet.jdbc.requests;


import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_Header;
import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_Initiator;
import com.ssw.bnk.swintwallet.jdbc.entities.SSEnt_MoneyTransfer;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;
import java.sql.Timestamp;
import lombok.Getter;
import lombok.Setter;

/*
 * @author Hicham Elkasmi
 */
public class SSReq_Moneytransfer extends SSReq_Base{
    
    @Getter @Setter 
    private SSEnt_Initiator initiator;
    @Getter @Setter 
    private SSEnt_MoneyTransfer moneytransfer;



    public SSReq_Moneytransfer() {
    }

    public SSReq_Moneytransfer(SSEnt_Header header, SSEnt_Initiator initiator) {
        super(header);
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
        stream.writeTimestamp(Timestamp.valueOf(moneytransfer.getTransactiondate()+" 00:00:00.000"));
        stream.writeString(moneytransfer.getTerminaltype());
        stream.writeString(moneytransfer.getTerminalcode());
        stream.writeString(moneytransfer.getEntitycodefrom());
        stream.writeString(moneytransfer.getEntitytypefrom());
        stream.writeString(moneytransfer.getEntitycodeto());
        stream.writeString(moneytransfer.getEntitytypeto());
        stream.writeString(moneytransfer.getAmount());
        stream.writeString(moneytransfer.getCurrencycode());
        stream.writeString(moneytransfer.getAdditionaldata());
        
    }
   
}
