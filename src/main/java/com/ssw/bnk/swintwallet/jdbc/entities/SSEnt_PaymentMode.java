package com.ssw.bnk.swintwallet.jdbc.entities;

import com.owlike.genson.annotation.JsonIgnore;
import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author MOKHLISS
 */
@Getter @Setter 
public class SSEnt_PaymentMode implements SQLData{
    @Getter(AccessLevel.NONE) @Setter(AccessLevel.NONE)
    private final String SQL_TYPE="WALLET.PAYMODSTRUCT";
    
    private String merpaymodcode;
    private String merpaymodlabel;
    private String merpaymodrate;
    private String merpaymoddefault;

    public SSEnt_PaymentMode(String merpaymodcode, String merpaymodlabel, String merpaymodrate, String merpaymoddefault) {
        this.merpaymodcode = merpaymodcode;
        this.merpaymodlabel = merpaymodlabel;
        this.merpaymodrate = merpaymodrate;
        this.merpaymoddefault = merpaymoddefault;
    }

    @JsonIgnore
    @Override
    public String getSQLTypeName() throws SQLException {
        return SQL_TYPE;
    }

    @Override
    public void readSQL(SQLInput stream, String typeName) throws SQLException {
        merpaymodcode =   stream.readString();
        merpaymodlabel =   stream.readString();
        merpaymodrate =   stream.readString();
        merpaymoddefault =   stream.readString();
    }

    @Override
    public void writeSQL(SQLOutput stream) throws SQLException {
        stream.writeString(merpaymodcode);
        stream.writeString(merpaymodlabel);
        stream.writeString(merpaymodrate);
        stream.writeString(merpaymoddefault);
    }

        
}
