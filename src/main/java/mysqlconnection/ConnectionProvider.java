package mysqlconnection;

import java.sql.Connection;
import java.sql.DriverManager;


public class ConnectionProvider {

    private ConnectionProvider() { }

    private static  Connection con;

    public static Connection getConnection()  {
        if(con==null){
            try{
                Class.forName(DBConnectionData.DATABASE_DRIVER);
                con= DriverManager.getConnection(DBConnectionData.DATABASE_URL,
                                                 DBConnectionData.DATABASE_USER,
                                                 DBConnectionData.DATABASE_PASSWORD);
            }catch (Exception e){
                e.printStackTrace();
            }
            return  con;
        }
        return  con;
    }


}
