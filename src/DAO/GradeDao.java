/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.*;
import MODEL.Grade;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author DELL
 */
public class GradeDao extends KetNoiCSDL {

    public Grade findByMaSV(String MaSV) {
        Grade grade = null;
        String sql = "SELECT * FROM Grade WHERE MaSV = ? AND isDeleted = 0";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, MaSV);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                grade = new Grade();
                grade.setID(rs.getInt(1));
                grade.setMaSV(rs.getString(2));
                grade.setTiengAnh(rs.getFloat(3));
                grade.setTinHoc(rs.getFloat(4));
                grade.setGDTC(rs.getFloat(5));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return grade;
    }

    public List<Grade> getAll() {
        ArrayList<Grade> list = new ArrayList<>();
        String sql = "Select * from Grade where isDeleted = 0";
        try {
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                Grade temp = new Grade();
                temp.setID(rs.getInt(1));
                temp.setMaSV(rs.getString(2));
                temp.setTiengAnh(rs.getFloat(3));
                temp.setTinHoc(rs.getFloat(4));
                temp.setGDTC(rs.getFloat(5));
                list.add(temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
/*
    public ArrayList<Grade> getFromGrade(String IDGrade) {
        ArrayList<Grade> list = new ArrayList<>();
        String sql = "SELECT * FROM grade where ID = ? and isDeteted = 0";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, IDGrade);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Grade temp = new Grade();
                temp.setID(rs.getByte(1));
                temp.setMaSV(rs.getString(2));
                temp.setTiengAnh(rs.getFloat(3));
                temp.setTinHoc(rs.getFloat(4));
                temp.setGDTC(rs.getFloat(5));
                list.add(temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
*/

    public boolean insert(Grade grade) {
        int i = 0;
        String sql = "INSERT INTO Grade(MaSV, TiengAnh, TinHoc, GDTC) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, grade.getMaSV());
            ps.setFloat(2, grade.getTiengAnh());
            ps.setFloat(3, grade.getTinHoc());
            ps.setFloat(4, grade.getGDTC());
            ps.setBoolean(5, false);
            i = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (i > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean update(Grade grade) {
        int i = 0;
        try {
            String sql = "Update grade set TiengAnh=? , Tinhoc=?, GDTC=? where Id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setFloat(1, grade.getTiengAnh());
            ps.setFloat(2, grade.getTinHoc());
            ps.setFloat(3, grade.getGDTC());
            ps.setInt(4, grade.getID());
            i = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (i > 0) {
            return true;
        } else {
            return false;
        }
    }
    ///////////////////////////////////////////////////////////////

//    public static void main(String[] args) {
//        GradeDao gr=new GradeDao();
//         ArrayList<Grade> getList=(ArrayList<Grade>) gr.getAll();
//        for(Grade temp : getList) {
//            System.out.println(temp.getID() + " - " + temp.getMaSV()+"-"+temp.getTiengAnh()+"-"+temp.getTinHoc()+"-"+temp.getGDTC());
//        }
//    
//}
    public boolean delete(String maSV) {
        int i = 0;
        try {
            String sql = "update grade set isDeleted = 1 where maSV = ?";
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

    public List<Object[]> sortByMark() {
        List<Object[]> result = new ArrayList<>();
        try {
            String sql = "select top(3) grade.masv, hoten, tienganh, tinhoc, gdtc, round((tienganh+tinhoc+gdtc)/3,2) as 'diemtb'"
                    + " from students inner join grade on students.masv = grade.masv"
                    + " where grade.isDeleted = 0"
                    + " order by diemtb desc";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                //                index 0 ||        index 1    ||    index 2 ......
                Object[] row = {rs.getString(1), rs.getString(2), rs.getFloat(3), rs.getFloat(4), rs.getFloat(5), rs.getFloat(6)};
                result.add(row);
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

//    public static void main(String[] args) {
//        GradeDao gradeDAO = new GradeDao();
//        List<Object[]> result = gradeDAO.sortByMark();
//        System.out.println(result.get(0)[1]);
//        
//    }
}
