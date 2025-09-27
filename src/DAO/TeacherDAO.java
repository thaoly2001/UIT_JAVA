package DAO;

import MODEL.Teacher;
import Utils.PageResult;
import java.io.File;
import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherDAO extends KetNoiCSDL {

    private static TeacherDAO instance;

    public static TeacherDAO getInstance() {
        if (instance == null) {
            instance = new TeacherDAO();
        }
        return instance;
    }

    public void insertTeacherWithImage(Teacher teacher, String imagePath) {
        String sql = "INSERT INTO teachers (name, email, phone, address, gender, img, birthday, department) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql); FileInputStream fis = new FileInputStream(new File(imagePath))) {

            ps.setString(1, teacher.getName());
            ps.setString(2, teacher.getEmail());
            ps.setString(3, teacher.getPhone());
            ps.setString(4, teacher.getAddress());
            ps.setString(5, teacher.getGender());
            ps.setBinaryStream(6, fis, (int) new File(imagePath).length());
            ps.setDate(7, new java.sql.Date(System.currentTimeMillis()));
            ps.setString(8, teacher.getDepartment());

            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Thêm teacher thành công!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Teacher insert(Teacher t) {
        String sql = "INSERT INTO teachers (name, email, phone, address, gender, birthday, department) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

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

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        t.setId(rs.getLong(1));
                    }
                }
                return t;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean update(Long id, Teacher t) {
        String sql = "UPDATE teachers SET name = ?, email = ?, phone = ?, address = ?, gender = ?, birthday = ?, department = ?, img = ? "
                + "WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

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

            // Thêm ảnh (BLOB)
            if (t.getImg() != null) {
                stmt.setBytes(8, t.getImg());
            } else {
                stmt.setNull(8, Types.VARBINARY);
            }

            stmt.setLong(9, id);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(long id) {
        String sql = "DELETE FROM teachers WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Teacher findById(Long id) {
        String sql = "SELECT * FROM teachers WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

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

    public Teacher findByEmail(String email) {
        String sql = "SELECT * FROM teachers WHERE email = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return extractTeacherFromResultSet(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Teacher> findAll() {
        List<Teacher> list = new ArrayList<>();
        String sql = "SELECT * FROM teachers";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(extractTeacherFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public PageResult<Teacher> search(String keyword, int page, int pageSize) {
        List<Teacher> list = new ArrayList<>();
        int totalRecords = 0;

        String countSql = "SELECT COUNT(*) FROM teachers "
                + "WHERE (name LIKE ? OR email LIKE ?)  AND is_deleted = 0 ";

        String dataSql = "SELECT id, name, email, phone, address, gender, birthday, department, img "
                + "FROM teachers "
                + "WHERE (name LIKE ? OR email LIKE ?)  AND is_deleted = 0 "
                + "ORDER BY id DESC "
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try (Connection conn = getConnection()) {
            String keywordPattern = "%" + keyword + "%";

            try (PreparedStatement stmt = conn.prepareStatement(countSql)) {
                stmt.setString(1, keywordPattern);
                stmt.setString(2, keywordPattern);

                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        totalRecords = rs.getInt(1);
                    }
                }
            }

            try (PreparedStatement stmt = conn.prepareStatement(dataSql)) {
                stmt.setString(1, keywordPattern);
                stmt.setString(2, keywordPattern);

                int offset = Math.max(page - 1, 0) * pageSize;
                stmt.setInt(3, offset);
                stmt.setInt(4, pageSize);

                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        list.add(extractTeacherFromResultSet(rs));
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new PageResult<>(list, page, pageSize, totalRecords);
    }

    private Teacher extractTeacherFromResultSet(ResultSet rs) throws SQLException {
        Teacher teacher = new Teacher();
        teacher.setId(rs.getLong("id"));
        teacher.setName(rs.getString("name"));
        teacher.setEmail(rs.getString("email"));
        teacher.setPhone(rs.getString("phone"));
        teacher.setAddress(rs.getString("address"));
        teacher.setGender(rs.getString("gender"));
        teacher.setImg(rs.getBytes("gender"));
        Date birthdaySql = rs.getDate("birthday");
        if (birthdaySql != null) {
            teacher.setBirthday(birthdaySql.toLocalDate());
        } else {
            teacher.setBirthday(null);
        }

        teacher.setImg(rs.getBytes("img"));
        teacher.setDepartment(rs.getString("department"));

        return teacher;
    }

}
