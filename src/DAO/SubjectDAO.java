package DAO;

import MODEL.Subject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubjectDAO extends KetNoiCSDL {
    private static SubjectDAO instance;

    public static SubjectDAO getInstance() {
        if (instance == null) {
            instance = new SubjectDAO();
        }
        return instance;
    }

    // Thêm môn học
    public Subject insert(Subject subject) {
        String sql = "INSERT INTO subjects (name, credit, is_deleted) VALUES (?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, subject.getName());
            stmt.setInt(2, subject.getCredit());
            stmt.setBoolean(3, subject.isIsdeleted());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        subject.setId(rs.getLong(1));
                    }
                }
                if (rows > 0) {
                    try (ResultSet rs = stmt.getGeneratedKeys()) {
                        if (rs.next()) {
                            subject.setId(rs.getLong(1)); // gán ID tự sinh
                        }
                    }
                    return subject;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Cập nhật môn học
    public boolean update(Long id, Subject subject) {
        String sql = "UPDATE subjects SET name = ?, credit = ?, is_deleted = ? WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, subject.getName());
            stmt.setInt(2, subject.getCredit());
            stmt.setBoolean(3, subject.isIsdeleted());
            stmt.setLong(4, id);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Xoá mềm (đánh dấu is_deleted = 1)
    public boolean delete(long id) {
        String sql = "UPDATE subjects SET is_deleted = 1 WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Tìm theo ID
    public Subject findById(long id) {
        String sql = "SELECT * FROM subjects WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return extractSubjectFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Lấy tất cả (cả bị xoá)
    public List<Subject> findAll() {
        List<Subject> list = new ArrayList<>();
        String sql = "SELECT * FROM subjects";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(extractSubjectFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Lấy danh sách môn học chưa bị xoá
    public List<Subject> findAllNotDeleted() {
        List<Subject> list = new ArrayList<>();
        String sql = "SELECT * FROM subjects WHERE is_deleted = 0";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(extractSubjectFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Trích xuất Subject từ ResultSet
    private Subject extractSubjectFromResultSet(ResultSet rs) throws SQLException {
        Subject subject = new Subject();
        subject.setId(rs.getLong("id"));
        subject.setName(rs.getString("name"));
        subject.setCredit(rs.getInt("credit"));
        subject.setIsdeleted(rs.getBoolean("is_deleted"));
        return subject;
    }
}