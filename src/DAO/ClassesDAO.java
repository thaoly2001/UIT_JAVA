package DAO;

import MODEL.Classes;
import MODEL.Subject;
import MODEL.Teacher;
import Utils.PageResult;

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

public PageResult<Classes> search(String keyword, int page, int pageSize) {
    List<Classes> list = new ArrayList<>();
    int totalRecords = 0;

    String countSql = "SELECT COUNT(*) "
            + "FROM classes c "
            + "JOIN teachers t ON c.teacher_id = t.id "
            + "JOIN subjects s ON c.subject_id = s.id "
            + "WHERE (c.name LIKE ? OR t.name LIKE ? OR s.name LIKE ?) AND  c.is_deleted = 0 ";

    String dataSql = "SELECT c.id, c.name, c.subject_id, c.teacher_id, c.is_deleted "
            + "FROM classes c "
            + "JOIN teachers t ON c.teacher_id = t.id "
            + "JOIN subjects s ON c.subject_id = s.id "
            + "WHERE (c.name LIKE ? OR t.name LIKE ? OR s.name LIKE ?)  AND  c.is_deleted = 0  "
            + "ORDER BY c.id DESC "
            + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

    try (Connection conn = getConnection()) {
        try (PreparedStatement stmt = conn.prepareStatement(countSql)) {
            String keywordPattern = "%" + keyword + "%";
            stmt.setString(1, keywordPattern);
            stmt.setString(2, keywordPattern);
            stmt.setString(3, keywordPattern);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    totalRecords = rs.getInt(1);
                }
            }
        }

        try (PreparedStatement stmt = conn.prepareStatement(dataSql)) {
            String keywordPattern = "%" + keyword + "%";
            stmt.setString(1, keywordPattern);
            stmt.setString(2, keywordPattern);
            stmt.setString(3, keywordPattern);

            int offset = Math.max(page, 0) * pageSize;

            stmt.setInt(4, offset);
            stmt.setInt(5, pageSize);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    list.add(extractClassFromResultSet(rs));
                }
            }
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return new PageResult<>(list, page, pageSize, totalRecords);
}

    public List<Classes> export(String keyword) {
        List<Classes> list = new ArrayList<>();

    String sql = "SELECT c.id, c.name, c.subject_id, c.teacher_id, c.is_deleted "
                + "FROM classes c "
                + "JOIN teachers t ON c.teacher_id = t.id "
                + "JOIN subjects s ON c.subject_id = s.id "
                + "WHERE (c.name LIKE ? OR t.name LIKE ? OR s.name LIKE ?) "
                + "AND c.is_deleted = 0 "
                + "ORDER BY c.id DESC";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            String keywordPattern = "%" + keyword + "%";
            stmt.setString(1, keywordPattern);
            stmt.setString(2, keywordPattern);
            stmt.setString(3, keywordPattern);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    list.add(extractClassFromResultSet(rs));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

public PageResult<Classes> searchByTeacherId(String keyword, Long teacherId, int page, int pageSize) {
    List<Classes> list = new ArrayList<>();
    int totalRecords = 0;

    String countSql = "SELECT COUNT(*) "
            + "FROM classes c "
            + "JOIN teachers t ON c.teacher_id = t.id "
            + "JOIN subjects s ON c.subject_id = s.id "
            + "WHERE (c.name LIKE ? OR t.name LIKE ? OR s.name LIKE ?) "
            + "AND c.is_deleted = 0 "
            + "AND c.teacher_id = ?";

    String dataSql = "SELECT c.id, c.name, c.subject_id, c.teacher_id, c.is_deleted "
            + "FROM classes c "
            + "JOIN teachers t ON c.teacher_id = t.id "
            + "JOIN subjects s ON c.subject_id = s.id "
            + "WHERE (c.name LIKE ? OR t.name LIKE ? OR s.name LIKE ?) "
            + "AND c.is_deleted = 0 "
            + "AND c.teacher_id = ? "
            + "ORDER BY c.id DESC "
            + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

    try (Connection conn = getConnection()) {
        String keywordPattern = "%" + keyword + "%";

        try (PreparedStatement stmt = conn.prepareStatement(countSql)) {
            stmt.setString(1, keywordPattern);
            stmt.setString(2, keywordPattern);
            stmt.setString(3, keywordPattern);
            stmt.setLong(4, teacherId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    totalRecords = rs.getInt(1);
                }
            }
        }

        try (PreparedStatement stmt = conn.prepareStatement(dataSql)) {
            stmt.setString(1, keywordPattern);
            stmt.setString(2, keywordPattern);
            stmt.setString(3, keywordPattern);
            stmt.setLong(4, teacherId);

            int offset = Math.max(page, 0) * pageSize;
            stmt.setInt(5, offset);
            stmt.setInt(6, pageSize);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    list.add(extractClassFromResultSet(rs));
                }
            }
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return new PageResult<>(list, page, pageSize, totalRecords);
}


    public Classes insert(Classes cls) {
        String sql = "INSERT INTO classes (name, subject_id, teacher_id) VALUES (?, ?, ?)";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, cls.getName());
            stmt.setLong(2, cls.getSubject().getId());
            if (cls.getTeacher() != null) {
                stmt.setLong(3, cls.getTeacher().getId());
            } else {
                stmt.setNull(3, Types.BIGINT);
            }

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

    public boolean update(Long id, Classes cls) {
        String sql = "UPDATE classes SET name = ?, subject_id = ?, teacher_id = ? WHERE id = ?";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cls.getName());
            stmt.setLong(2, cls.getSubject().getId());
            if (cls.getTeacher() != null) {
                stmt.setLong(3, cls.getTeacher().getId());
            } else {
                stmt.setNull(3, Types.BIGINT);
            }
            stmt.setLong(4, id);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

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

    public Classes findById(long id) {
        String sql = "SELECT * FROM classes WHERE id = ? AND is_deleted = 0";
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

    public List<Classes> findAll() {
        List<Classes> list = new ArrayList<>();
        String sql = "SELECT * FROM classes WHERE is_deleted = 0";

        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(extractClassFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Classes> findClassesBySubjectName(String subjectName) {
        List<Classes> classes = new ArrayList<>();
        String sql = "SELECT c.* FROM classes c JOIN subjects s ON c.subject_id = s.id WHERE s.name = ? AND c.is_deleted = 0";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, subjectName);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    classes.add(extractClassFromResultSet(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classes;
    }

    private Classes extractClassFromResultSet(ResultSet rs) throws SQLException {
        Long id = rs.getLong("id");
        String name = rs.getString("name");
        Long subjectId = rs.getLong("subject_id");
        Long teacherId = rs.getLong("teacher_id");

        Subject subject =(subjectId != 0) ? SubjectDAO.getInstance().findById(subjectId) : null;
        Teacher teacher = (teacherId != 0) ? TeacherDAO.getInstance().findById(teacherId) : null;

        return new Classes(id, name, subject, teacher);
    }
}
