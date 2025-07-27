
package DAO;
import java.sql.*;
public class KetNoiCSDL {
    protected Connection con;
    public KetNoiCSDL() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost\\MSSQLSERVER01:1433;instanceName=MSSQLSERVER01;databaseName=QuanLiSV;encrypt=true;trustServerCertificate=true";
            String username = "java";
            String password = "java";
            con = DriverManager.getConnection(url, username, password);
            System.out.println("Kết nối thành công!");
        } catch (Exception e) {
            System.out.println("Kết nối database thất bại!");
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new KetNoiCSDL();
    }
}

