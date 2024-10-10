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
public class SSEnt_Address implements SQLData{
    @Getter(AccessLevel.NONE) @Setter(AccessLevel.NONE) 
    private final String SQL_TYPE="WALLET.ADRESSSTRUCT";
    
    private String addrtype;
    private String addcorr;
    private String addr1;
    private String addr2;
    private String addstreet;
    private String addzipd;
    private String citycode;
    private String postalcode;
    private String country;
    
    public SSEnt_Address(String addrtype, String addcorr, String addr1, String addr2, String addstreet, String addzipd, String citycode, String postalcode, String country) {
        this.addrtype = addrtype;
        this.addcorr = addcorr;
        this.addr1 = addr1;
        this.addr2 = addr2;
        this.addstreet = addstreet;
        this.addzipd = addzipd;
        this.citycode = citycode;
        this.postalcode = postalcode;
        this.country = country;
    }
    
    @JsonIgnore
    @Override
    public String getSQLTypeName() throws SQLException {
        return SQL_TYPE;
    }

    @Override
    public void readSQL(SQLInput stream, String typeName) throws SQLException {
        addrtype =   stream.readString();
        addcorr =   stream.readString();
        addr1 =   stream.readString();
        addr2 =   stream.readString();
        addstreet =   stream.readString();
        addzipd =   stream.readString();
        citycode =   stream.readString();
        postalcode =   stream.readString();
        country =   stream.readString();
    }

    @Override
    public void writeSQL(SQLOutput stream) throws SQLException {
        stream.writeString(addrtype);
        stream.writeString(addcorr);
        stream.writeString(addr1);
        stream.writeString(addr2);
        stream.writeString(addstreet);
        stream.writeString(addzipd);
        stream.writeString(citycode);
        stream.writeString(postalcode);
        stream.writeString(country);
    }
}
