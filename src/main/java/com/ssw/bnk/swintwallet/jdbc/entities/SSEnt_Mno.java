package com.ssw.bnk.swintwallet.jdbc.entities;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author MOKHLISS
 */
@Getter @Setter 
public class SSEnt_Mno {
    
    private String mnocode;
    private String mnoiden; // EKH ADD MNO ID, REQUESTED BY MASSARAT FOR MATCHING NEED
    private String mnolabel;
    private SSEnt_Service[] services;
    
    public SSEnt_Mno(String mnocode, String mnoiden, String mnolabel, SSEnt_Service[] services) {
        this.mnocode = mnocode;
        this.mnolabel = mnolabel;
        this.services = services;
        this.mnoiden = mnoiden;
    }
}
