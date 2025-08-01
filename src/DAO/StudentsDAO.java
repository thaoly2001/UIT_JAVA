package DAO;

import MODEL.Student;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentsDAO extends KetNoiCSDL {

    private static StudentsDAO instance;

    public static StudentsDAO getInstance() {
        if (instance == null) {
            instance = new StudentsDAO();
        }
        return instance;
    }

    public Student findById(Long id) {
        String sql = "SELECT * FROM students WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapResultSetToStudent(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Student> getAll() {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM students";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(mapResultSetToStudent(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Student insert(Student student) {
        String sql = "INSERT INTO students (name, email, phone, address, gender, birthday) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, student.getName());
            stmt.setString(2, student.getEmail());
            stmt.setString(3, student.getPhone());
            stmt.setString(4, student.getAddress());
            stmt.setString(5, student.getGender());
            stmt.setDate(6, Date.valueOf(student.getBirthday()));
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        student.setId(rs.getLong(1));
                    }
                }
                if (rows > 0) {
                    try (ResultSet rs = stmt.getGeneratedKeys()) {
                        if (rs.next()) {
                            student.setId(rs.getLong(1)); // gán ID tự sinh
                        }
                    }
                    return student;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean update(Student student) {
        String sql = "UPDATE students SET name=?, email=?, phone=?, address=?, gender=?, birthday=? WHERE id=?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, student.getName());
            stmt.setString(2, student.getEmail());
            stmt.setString(3, student.getPhone());
            stmt.setString(4, student.getAddress());
            stmt.setString(5, student.getGender());
            stmt.setDate(6, Date.valueOf(student.getBirthday()));
            stmt.setLong(7, student.getId());
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(Long id) {
        String sql = "DELETE FROM students WHERE id=?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Student> searchStudents(String keyword, int page, int pageSize) {
        List<Student> list = new ArrayList<>();
        boolean hasKeyword = keyword != null && !keyword.trim().isEmpty();

        StringBuilder sql = new StringBuilder("SELECT * FROM students");
        if (hasKeyword) {
            sql.append(" WHERE name LIKE ? OR email LIKE ?");
        }
        sql.append(" ORDER BY id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY");

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
                    Student s = new Student();
                    s.setId(rs.getLong("id"));
                    s.setName(rs.getString("name"));
                    s.setEmail(rs.getString("email"));
                    s.setPhone(rs.getString("phone"));
                    s.setAddress(rs.getString("address"));
                    s.setGender(rs.getString("gender"));
                    s.setBirthday(rs.getDate("birthday").toLocalDate());
                    list.add(s);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public int countSearchStudents(String keyword) {
        boolean hasKeyword = keyword != null && !keyword.trim().isEmpty();

        StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM students");
        if (hasKeyword) {
            sql.append(" WHERE name LIKE ? OR email LIKE ?");
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

    private Student mapResultSetToStudent(ResultSet rs) throws SQLException {
        Student s = new Student();
        s.setId(rs.getLong("id"));
        s.setName(rs.getString("name"));
        s.setEmail(rs.getString("email"));
        s.setPhone(rs.getString("phone"));
        s.setAddress(rs.getString("address"));
        s.setGender(rs.getString("gender"));
        s.setBirthday(rs.getDate("birthday").toLocalDate());
        return s;
    }
}
