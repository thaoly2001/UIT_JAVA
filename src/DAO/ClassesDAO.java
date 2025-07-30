package DAO;

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

    // Thêm giáo viên
   public boolean insert(Teacher t) {
    String sql = "INSERT INTO teachers (name, email, phone, address, gender, birthday, department) VALUES (?, ?, ?, ?, ?, ?, ?)";

    try (Connection conn = getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

        stmt.setString(1, t.getName());
        stmt.setString(2, t.getEmail());
        stmt.setString(3, t.getPhone());
        stmt.setString(4, t.getAddress());
        stmt.setString(5, t.getGender());
        if (t.getBirthday() != null) {
            stmt.setDate(6, Date.valueOf(t.getBirthday())); // LocalDate -> SQL Date
        } else {
            stmt.setNull(6, Types.DATE);
        }
        stmt.setString(7, t.getDepartment());

        int rows = stmt.executeUpdate();
        if (rows > 0) {
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) t.setId(rs.getLong(1)); 
            }
            return true;
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}


    // Cập nhậtextractTeacherFromResultSet
    public boolean update(Long id, Teacher t) {
    String sql = "UPDATE teachers SET name = ?, email = ?, phone = ?, address = ?, gender = ?, birthday = ?, department = ? WHERE id = ?";
    try (Connection conn = getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, t.getName());
        stmt.setString(2, t.getEmail());
        stmt.setString(3, t.getPhone());
        stmt.setString(4, t.getAddress());
        stmt.setString(5, t.getGender());

        if (t.getBirthday() != null) {
            stmt.setDate(6, Date.valueOf(t.getBirthday()));
        } else {
            stmt.setNull(6, Types.DATE);
        }

        stmt.setString(7, t.getDepartment());
        stmt.setLong(8, id);

        return stmt.executeUpdate() > 0;
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}


    // Xoá
    public boolean delete(long id) {
        String sql = "DELETE FROM teachers WHERE id = ?";
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
    public Teacher findById(long id) {
    String sql = "SELECT * FROM teachers WHERE id = ?";
    try (Connection conn = getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setLong(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return extractTeacherFromResultSet(rs);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}


    // Lấy tất cả
    public List<Teacher> findAll() {
        List<Teacher> list = new ArrayList<>();
        String sql = "SELECT * FROM teachers";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(extractTeacherFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    private Teacher extractTeacherFromResultSet(ResultSet rs) throws SQLException {
    Teacher teacher = new Teacher();
    teacher.setId(rs.getLong("id"));
    teacher.setName(rs.getString("name"));
    teacher.setEmail(rs.getString("email"));
    teacher.setPhone(rs.getString("phone"));
    teacher.setAddress(rs.getString("address"));
    teacher.setGender(rs.getString("gender"));

    Date birthdaySql = rs.getDate("birthday");
    if (birthdaySql != null) {
        teacher.setBirthday(birthdaySql.toLocalDate());
    } else {
        teacher.setBirthday(null);
    }

    teacher.setDepartment(rs.getString("department"));
    return teacher;
}

}
