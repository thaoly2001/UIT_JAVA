/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import MODEL.Enrollment;
import MODEL.Student;
import MODEL.Subject;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class EnrollmentDAO extends KetNoiCSDL {

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

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

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

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
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

    public List<Enrollment> searchEnrollments(String keyword, int page, int pageSize) {
        List<Enrollment> list = new ArrayList<>();
        boolean hasKeyword = keyword != null && !keyword.trim().isEmpty();

        StringBuilder sql = new StringBuilder(
                "SELECT e.id AS eid, e.score, e.enrollmentDate, "
                + "s.id AS sid, s.name AS sname, s.email, s.phone, s.address, s.gender, s.birthday, "
                + "sub.id AS subid, sub.name AS subname, sub.credit, sub.isdeleted "
                + "FROM enrollments e "
                + "JOIN students s ON e.student_id = s.id "
                + "JOIN subjects sub ON e.subject_id = sub.id"
        );

        if (hasKeyword) {
            sql.append(" WHERE s.name LIKE ? OR sub.name LIKE ?");
        }

        sql.append(" ORDER BY e.id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY");

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
            int paramIndex = 1;

            if (hasKeyword) {
                stmt.setString(paramIndex++, "%" + keyword + "%");
                stmt.setString(paramIndex++, "%" + keyword + "%");
            }

            stmt.setInt(paramIndex++, (page - 1) * pageSize);
            stmt.setInt(paramIndex, pageSize);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    // Lấy sinh viên
                    Student student = new Student();
                    student.setId(rs.getLong("sid"));
                    student.setName(rs.getString("sname"));
                    student.setEmail(rs.getString("email"));
                    student.setPhone(rs.getString("phone"));
                    student.setAddress(rs.getString("address"));
                    student.setGender(rs.getString("gender"));
                    student.setBirthday(rs.getDate("birthday").toLocalDate());

                    // Lấy môn học
                    Subject subject = new Subject();
                    subject.setId(rs.getLong("subid"));
                    subject.setName(rs.getString("subname"));
                    subject.setCredit(rs.getInt("credit"));
                    subject.setIsdeleted(rs.getBoolean("isdeleted"));

                    // Tạo enrollment
                    Enrollment e = new Enrollment();
                    e.setId(rs.getLong("eid"));
                    e.setStudent(student);
                    e.setSubject(subject);
                    e.setScore(rs.getDouble("score"));
                    e.setEnrollmentDate(rs.getDate("enrollmentDate").toLocalDate());

                    list.add(e);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public int countSearchEnrollments(String keyword) {
        boolean hasKeyword = keyword != null && !keyword.trim().isEmpty();

        StringBuilder sql = new StringBuilder(
                "SELECT COUNT(*) "
                + "FROM enrollments e "
                + "JOIN students s ON e.student_id = s.id "
                + "JOIN subjects sub ON e.subject_id = sub.id"
        );

        if (hasKeyword) {
            sql.append(" WHERE s.name LIKE ? OR sub.name LIKE ?");
        }

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
            if (hasKeyword) {
                stmt.setString(1, "%" + keyword + "%");
                stmt.setString(2, "%" + keyword + "%");
            }

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

}
