/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import MODEL.Users;
import java.sql.*;
import java.util.Vector;
import javax.swing.JOptionPane;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author DELL
 */
public class UsersDAO extends KetNoiCSDL {
      public UsersDAO() {
        super(); // gọi constructor KetNoiCSDL để con != null
    }

    public Users login(String username, String password) {
        Users user = null;
        String sql = "SELECT * FROM Users WHERE Username = ? AND Password = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user = new Users();
                user.setUsername(rs.getString(1));
                user.setPassword(rs.getString(2));
                user.setRole(rs.getByte(3));
                user.setFullname(rs.getString(4));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    /*
    public  Users login2(String username) {
        Users user = null;
        String sql = "SELECT * FROM Users WHERE Username = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user = new Users();
                user.setUsername(rs.getString(1));
                user.setPassword(rs.getString(2));
                user.setRole(rs.getByte(3));
                user.setFullname(rs.getString(4));
            }
        } catch (Exception e) {
          
            JOptionPane.showMessageDialog(null, "Sai Tk or MK");
            return null;
        }
        return user;
    }
     */
    public Users findByUsernameAndEmail(String username, String email) {
        Users user = null;
        String sql = "select * from users where username = ? and email = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, email);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user = new Users();
                user.setUsername(rs.getString(1));
                user.setPassword(rs.getString(2));
                user.setRole(rs.getByte(3));
                user.setFullname(rs.getString(4));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public boolean updatePassword(Users user) {
        int check = 0;
        String sql = "update users set password = ? where username = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user.getPassword());
            ps.setString(2, user.getUsername());
            check = ps.executeUpdate();
            if (check > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

//    public boolean xuatFile(Users user) {
//        int rowNum = 1;
//        int check = 0;
//
//        try {
//            String sql = " select G.MASV, S.Hoten, G.TiengAnh, G.TinHoc, G.GDTC, round((tienganh+tinhoc+gdtc)/3,2) as DTB"
//                    + " from GRADE G join students S ON G.MASV = S.MASV ";
//            Statement stm = con.createStatement();
//            ResultSet rs = stm.executeQuery(sql);
//            //Vector vt = null;
//            while (rs.next()) {
//                Vector vt = new Vector();
//                vt.add(rs.getString("MASV"));
//                vt.add(rs.getString("Hoten"));
//                vt.add(rs.getString("TiengAnh"));
//                vt.add(rs.getString("TinHoc"));
//                vt.add(rs.getString("GDTC"));
//                vt.add(rs.getString("DTB"));
//
//                if (check > 0) {
//                    return true;
//                } else {
//                    return false;
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//    }

    //
//    public boolean xuatFileRow(Users user) {
//        int rowNum = 1;
//        int check = 0;
//        Workbook workbook = new XSSFWorkbook();
//        Sheet sheet = workbook.createSheet("GRADE");
//        Row headerRow = sheet.createRow(0);
//        try {
//            String sql = " select G.MASV, S.Hoten, G.TiengAnh, G.TinHoc, G.GDTC, round((tienganh+tinhoc+gdtc)/3,2) as DTB"
//                    + " from GRADE G join students S ON G.MASV = S.MASV ";
//            Statement stm = con.createStatement();
//            ResultSet rs = stm.executeQuery(sql);
//            //Vector vt = null;
//            while (rs.next()) {
//                Row row = sheet.createRow(rowNum++);
//                row.createCell(0).setCellValue(rs.getString("MaSV"));
//                row.createCell(1).setCellValue(rs.getString("Hoten"));
//                row.createCell(2).setCellValue(rs.getString("TiengAnh"));
//                row.createCell(3).setCellValue(rs.getString("TinHoc"));
//                row.createCell(4).setCellValue(rs.getString("GDTC"));
//                row.createCell(5).setCellValue(rs.getString("DTB"));
//
//                if (check > 0) {
//                    return true;
//                } else {
//                    return false;
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//    }

//    public static 
}
