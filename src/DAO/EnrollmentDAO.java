/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import MODEL.Enrollment;
import MODEL.Subject;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
public class EnrollmentDAO extends KetNoiCSDL{

    private static EnrollmentDAO instance;

    public static EnrollmentDAO getInstance() {
        if (instance == null) {
            instance = new EnrollmentDAO();
        }
        return instance;
    }

    public EnrollmentDAO() {
    }
  

public List<Enrollment> getBySubject(Subject subject) {
    List<Enrollment> list = new ArrayList<>();
    String sql = "SELECT * FROM enrollments WHERE subject_id = ?";

    try (Connection conn = getConnection(); 
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setLong(1, subject.getId());

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Enrollment e = new Enrollment();
                e.setId(rs.getLong("id"));
                e.setScore(rs.getDouble("score"));
                e.setEnrollmentDate(rs.getDate("enrollmentDate").toLocalDate());
                
                // set subject hiện tại (đã có)
                e.setSubject(subject);
                
                list.add(e);
            }
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return list;
}

   
public List<Enrollment> getAll() {
    List<Enrollment> list = new ArrayList<>();
    String sql = "SELECT * FROM enrollments ";

    try (Connection conn = getConnection(); 
         PreparedStatement ps = conn.prepareStatement(sql);ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Enrollment e = new Enrollment();
                e.setId(rs.getLong("id"));
                e.setScore(rs.getDouble("score"));
                e.setEnrollmentDate(rs.getDate("enrollmentDate").toLocalDate());
                
                list.add(e);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return list;
}
}