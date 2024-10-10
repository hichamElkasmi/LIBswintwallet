package com.ssw.bnk.swintwallet.jdbc;

import com.owlike.genson.Genson;
import java.sql.Connection;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 *
 * @author MOKHLISS
 */
public class SW_INT {
    
    public static DataSource datasource = null;
    public static Genson.Builder builder;
    private static boolean IS_LIVE = true;
    
    public static void startApp(boolean context) throws Exception{
        builder = new Genson.Builder();
        builder.setSkipNull(true);
        IS_LIVE = context;
        initDb();
    }
          /*  String DB_CONNECTION = "jdbc:oracle:thin:@" + "172.18.1.199" + ":"
                + "1528" + ":" + "TDW12C" + "";
        String DB_CONNECTION = "jdbc:oracle:thin:@" + "172.22.68.112" + ":"
                + "1521" + ":" + "msrt12c" + "";*/
    
    private static void initDb() throws Exception{
        if(isLiveCtx()){
        Context ctx = new InitialContext();
        datasource = (DataSource) ctx.lookup("java:/comp/env/jdbc/msrtJNDI");
        }
    }
    
    public static Connection getDBConnection() throws Exception {
        return datasource.getConnection();
    }
    
    public static boolean isLiveCtx() throws Exception {
        return IS_LIVE;
    }
}
