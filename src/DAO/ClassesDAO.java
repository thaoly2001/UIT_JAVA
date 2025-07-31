package DAO;

import MODEL.Classes;
import MODEL.Subject;
import MODEL.Teacher;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClassesDAO extends KetNoiCSDL {

    private static ClassesDAO instance;

    public static ClassesDAO getInstance() {
        if (instance == null) {
            instance = new ClassesDAO();
        }
        return instance;
    }

    // Thêm lớp học
    public Classes insert(Classes cls) {
        String sql = "INSERT INTO classes (name, subject_id, teacher_id, is_deleted) VALUES (?, ?, ?, ?)";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, cls.getName());
            stmt.setLong(2, cls.getSubject().getId());
            if (cls.getTeacher() != null) {
                stmt.setLong(3, cls.getTeacher().getId());
            } else {
                stmt.setNull(3, Types.BIGINT);
            }
            stmt.setBoolean(4, cls.isDeleted());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        cls.setId(rs.getLong(1));
                    }
                }
                return cls;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Cập nhật lớp học
    public boolean update(Long id, Classes cls) {
        String sql = "UPDATE classes SET name = ?, subject_id = ?, teacher_id = ?, is_deleted = ? WHERE id = ?";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cls.getName());
            stmt.setLong(2, cls.getSubject().getId());
            if (cls.getTeacher() != null) {
                stmt.setLong(3, cls.getTeacher().getId());
            } else {
                stmt.setNull(3, Types.BIGINT);
            }
            stmt.setBoolean(4, cls.isDeleted());
            stmt.setLong(5, id);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Xoá mềm (soft delete)
    public boolean delete(long id) {
        String sql = "UPDATE classes SET is_deleted = 1 WHERE id = ?";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Tìm theo ID
    public Classes findById(long id) {
        String sql = "SELECT * FROM classes WHERE id = ?";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return extractClassFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Lấy tất cả lớp học (kể cả đã bị xóa mềm)
    public List<Classes> findAll() {
        List<Classes> list = new ArrayList<>();
        String sql = "SELECT * FROM classes";

        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(extractClassFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Hàm hỗ trợ: trích xuất dữ liệu lớp học từ ResultSet
    private Classes extractClassFromResultSet(ResultSet rs) throws SQLException {
        Long id = rs.getLong("id");
        String name = rs.getString("name");
        Long subjectId = rs.getLong("subject_id");
        Long teacherId = rs.getLong("teacher_id");
        boolean isDeleted = rs.getBoolean("is_deleted");

        Subject subject = SubjectDAO.getInstance().findById(subjectId);
        Teacher teacher = (teacherId != 0) ? TeacherDAO.getInstance().findById(teacherId) : null;

        return new Classes(id, name, subject, teacher, isDeleted);
    }
}
