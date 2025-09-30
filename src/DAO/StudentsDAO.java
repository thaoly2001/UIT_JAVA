package DAO;

import MODEL.Student;
import Utils.PageResult;
import java.sql.*;
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
        String sql = "SELECT * FROM students WHERE id = ? AND is_deleted = 0";
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

    public Student findByEmail(String email) {
        String sql = "SELECT * FROM students WHERE email = ? AND is_deleted = 0";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapResultSetToStudent(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isEmailExists(String email, Long studentId) {
        String sql = "SELECT COUNT(*) FROM students WHERE email = ? AND is_deleted = 0";
        if (studentId != null) {
            sql += " AND id != ?";
        }
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            if (studentId != null) {
                stmt.setLong(2, studentId);
            }
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Student> getAll() {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM students WHERE is_deleted = 0";
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
        if (isEmailExists(student.getEmail(), null)) {
            System.out.println("Error: Email already exists.");
            return null;
        }
        String sql = "INSERT INTO students (name, email, phone, address, gender, birthday, img, is_deleted) VALUES (?, ?, ?, ?, ?, ?, ?, 0)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, student.getName());
            stmt.setString(2, student.getEmail());
            stmt.setString(3, student.getPhone());
            stmt.setString(4, student.getAddress());
            stmt.setString(5, student.getGender());
            stmt.setDate(6, student.getBirthday());
            stmt.setBytes(7, student.getImg());
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        student.setId(rs.getLong(1));
                    }
                }
                return student;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean update(Student student) {
        if (isEmailExists(student.getEmail(), student.getId())) {
            System.out.println("Error: Email already exists for another student.");
            return false;
        }
        String sql = "UPDATE students SET name=?, email=?, phone=?, address=?, gender=?, birthday=?, img=? WHERE id=? AND is_deleted = 0";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, student.getName());
            stmt.setString(2, student.getEmail());
            stmt.setString(3, student.getPhone());
            stmt.setString(4, student.getAddress());
            stmt.setString(5, student.getGender());
            stmt.setDate(6, student.getBirthday());
            stmt.setBytes(7, student.getImg());
            stmt.setLong(8, student.getId());
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(Long id) {
        String sql = "UPDATE students SET is_deleted = 1 WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public PageResult<Student> search(String keyword, int page, int pageSize) {
        List<Student> list = new ArrayList<>();
        int totalRecords = 0;

        String sql = "WITH filtered AS ( " +
                     "    SELECT id, name, email, phone, address, gender, birthday, img " +
                     "    FROM students " +
                     "    WHERE (name LIKE ? OR email LIKE ?) AND is_deleted = 0 " +
                     ") " +
                     "SELECT *, COUNT(*) OVER() AS total_count " +
                     "FROM filtered " +
                     "ORDER BY id DESC " +
                     "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            String keywordPattern = "%" + keyword + "%";
            stmt.setString(1, keywordPattern);
            stmt.setString(2, keywordPattern);

            int validPageSize = Math.max(pageSize, 1);
            int offset = Math.max(page - 1, 0) * validPageSize;
            stmt.setInt(3, offset);
            stmt.setInt(4, validPageSize);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    if (totalRecords == 0) {
                        totalRecords = rs.getInt("total_count");
                    }
                    Student student = mapResultSetToStudent(rs);
                    list.add(student);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }



        return new PageResult<>(list, page, pageSize, totalRecords);
    }

    public int countSearchStudents(String keyword) {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM students WHERE (name LIKE ? OR email LIKE ? OR phone LIKE ?) AND is_deleted = 0";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            String searchKeyword = "%" + keyword + "%";
            stmt.setString(1, searchKeyword);
            stmt.setString(2, searchKeyword);
            stmt.setString(3, searchKeyword);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    private Student mapResultSetToStudent(ResultSet rs) throws SQLException {
        Student s = new Student();
        s.setId(rs.getLong("id"));
        s.setName(rs.getString("name"));
        s.setEmail(rs.getString("email"));
        s.setPhone(rs.getString("phone"));
        s.setAddress(rs.getString("address"));
        s.setGender(rs.getString("gender"));
        s.setBirthday(java.sql.Date.valueOf(rs.getDate("birthday").toLocalDate()));
        s.setImg(rs.getBytes("img"));
        return s;
    }
}
