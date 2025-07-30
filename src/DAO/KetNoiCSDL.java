
package DAO;
import java.sql.*;
public class KetNoiCSDL {
       protected Connection getConnection() throws SQLException {
       String url = "jdbc:sqlserver://localhost:1433;databaseName=QuanLiSV;encrypt=true;trustServerCertificate=true";
             String username = "java";
            String password = "java";
        return DriverManager.getConnection(url, username, password);
    }
}

