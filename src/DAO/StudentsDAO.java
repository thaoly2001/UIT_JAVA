/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import MODEL.Students;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author DELL
 */
public class StudentsDAO extends KetNoiCSDL {
    
    public Students findById(String MaSV) {
        Students stu = null;
        String sql = "SELECT * FROM Students WHERE MaSV = ? AND isDeleted = 0";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, MaSV);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                stu = new Students();
                stu.setMaSV(rs.getString(1));
                stu.setHoTen(rs.getString(2));
                stu.setEmail(rs.getString(3)); 
                stu.setSDT(rs.getString(4)); 
                stu.setGioiTinh(rs.getString(5)); 
                stu.setDiaChi(rs.getString(6)); 
                stu.setHinh(rs.getString(7)); 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stu;
    }
    
    public List<Students> getAll() {
        List<Students> list = new ArrayList<>();

        String sql = "Select * from Students where isDeleted = 0"; // chi lay nhung sv chua bi XOA
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Students temp = new Students();
                temp.setMaSV(rs.getString(1));
                temp.setHoTen(rs.getString(2));
                temp.setEmail(rs.getString(3));
                temp.setSDT(rs.getString(4));
                temp.setGioiTinh(rs.getString(5));
                temp.setDiaChi(rs.getString(6));
                temp.setHinh(rs.getString(7));
                list.add(temp);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean insert(Students model) {
        try {
            String sql = "insert into Students values (?,?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, model.getMaSV());
            ps.setString(2, model.getHoTen());
            ps.setString(3, model.getEmail());
            ps.setString(4, model.getSDT());
            ps.setString(5, model.getGioiTinh());
            ps.setString(6, model.getDiaChi());
            ps.setString(7, model.getHinh());
            ps.setBoolean(8, false);
            int i = ps.executeUpdate();
            if (i == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(String maSV) {
        int i = 0;
        try {
            // String sql = "delete from Students where masv=?";
            String sql = "update students set isDeleted = 1 where maSV = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, maSV);
            i = ps.executeUpdate();
            if (i == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }
    
    public boolean update(Students model){
        int i=0;
        try {
            String sql="update students set HoTen=?, Email=?, SoDT=?,GioiTinh=?,DiaChi=?,Hinh=? where masv = ?";
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,model.getHoTen() );
            ps.setString(2, model.getEmail());
            ps.setString(3, model.getSDT());
            ps.setString(4, model.getGioiTinh());
            ps.setString(5, model.getDiaChi());
            ps.setString(6, model.getHinh());
            ps.setString(7, model.getMaSV());
            i=ps.executeUpdate();
            if(i>0){
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

//    public static void main(String[] args) {
//        StudentsDAO stDAO = new StudentsDAO();
//        Students stu = stDAO.findById("PS01");
//        System.out.println(stu.getHoTen());
//    }
    
}


