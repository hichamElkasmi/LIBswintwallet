package com.ssw.bnk.swintwallet.jdbc.requests;

import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;
import java.sql.Timestamp;


/**
 *
 * @author MOKHLISS
 */
public class SSReq_Payment extends SSReq_Transaction{
    

    public SSReq_Payment(SSReq_Transaction sSReq_Transaction) {
        
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
               
        if ("600000".equals(this.getRequest().getTrxtype())) {
            // EKH ADD TO MANAGE WITH THE TYPE PURCHASEVOUCHERRQ
            try{
            stream.writeString(header.getIdmsg()); //requestid
            stream.writeTimestamp(Timestamp.valueOf(request.getDate()+".000000")); //transactiondate
            stream.writeString("CC"); //transctx
            stream.writeString("TEST"); //merchantid
            stream.writeString(initiator.getCardcode()); //entitycode
            stream.writeString("WALLET"); //entitytype
            stream.writeString(request.getAddid()); // mnocode
            stream.writeString(request.getAdddata()); //denominationcode
            // EKH END            
           
        }catch (Exception e) {     
                   sqlType = e.getMessage();
                }

        }
        else 
        {
            try{
            stream.writeString(header.getIdmsg());
            stream.writeTimestamp(Timestamp.valueOf(request.getDate()+".000000"));
            stream.writeString("CM");
            stream.writeString(request.getAddid());
            stream.writeString(initiator.getCardcode());
            stream.writeString("WALLET");        
            stream.writeDouble(Double.valueOf(request.getAmount()));
            stream.writeString(request.getCurrency());
            stream.writeString(request.getLabel());
            stream.writeString(request.getTrxtype());
            }catch (Exception e) {     
                   sqlType = e.getMessage();
                }
        }
    }   
      
}
