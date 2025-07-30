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
        super(); 
    }

    public Users login(String username, String password) {
        Users user = null;
        String sql = "SELECT * FROM Users u WHERE u.username = ? AND u.password = ?";
        System.out.println(username);
        try (Connection conn = getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                user = new Users();
                user.setId(rs.getLong("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getByte("role"));
                user.setFullname(rs.getString("fullname"));
                user.setEmail(rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public boolean updatePassword(Users user) {
        int check = 0;
        String sql = "update users set password = ? where username = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getPassword());
            stmt.setString(2, user.getUsername());
            check = stmt.executeUpdate();
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
