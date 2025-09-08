package DAO;

import MODEL.Subject;
import Utils.PageResult;
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

    public PageResult<Subject> search(String keyword, int page, int pageSize) {
        List<Subject> list = new ArrayList<>();
        int totalRecords = 0;

        String countSql = "SELECT COUNT(*) "
                + "FROM subjects s "
                + "WHERE (s.name LIKE ?) AND s.is_deleted = 0 ";

        String dataSql = "SELECT * "
                + "FROM subjects s "
                + "WHERE (s.name LIKE ?) AND s.is_deleted = 0 "
                + "ORDER BY s.id DESC "
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try (Connection conn = getConnection()) {
            // 1. Đếm tổng số bản ghi
            try (PreparedStatement stmt = conn.prepareStatement(countSql)) {
                String keywordPattern = "%" + keyword + "%";
                stmt.setString(1, keywordPattern);

                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        totalRecords = rs.getInt(1);
                    }
                }
            }

            // 2. Lấy dữ liệu phân trang
            try (PreparedStatement stmt = conn.prepareStatement(dataSql)) {
                String keywordPattern = "%" + keyword + "%";
                stmt.setString(1, keywordPattern);

                int offset = Math.max(page, 0) * pageSize;

                stmt.setInt(2, offset);
                stmt.setInt(3, pageSize);

                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        list.add(extractSubjectFromResultSet(rs));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new PageResult<>(list, page, pageSize, totalRecords);
    }

    private Subject extractSubjectFromResultSet(ResultSet rs) throws SQLException {
        Subject subject = new Subject();
        subject.setId(rs.getLong("id"));
        subject.setName(rs.getString("name"));
        subject.setCredit(rs.getInt("credit"));
        subject.setStatus(rs.getBoolean("status"));
        return subject;
    }

    public Subject insert(Subject subject) {
        String sql = "INSERT INTO subjects (name, credit, status) VALUES (?, ?, ?)";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, subject.getName());
            stmt.setInt(2, subject.getCredit());
            stmt.setBoolean(3, subject.isStatus());
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        subject.setId(rs.getLong(1));
                    }
                }
                return subject;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean update(Long id, Subject subject) {
        String sql = "UPDATE subjects SET name = ?, credit = ?, status = ? WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, subject.getName());
            stmt.setInt(2, subject.getCredit());
            stmt.setBoolean(3, subject.isStatus());
            stmt.setLong(4, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(long id) {
        String sql = "UPDATE subjects SET is_deleted = 1 WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Subject findById(long id) {
        String sql = "SELECT * FROM subjects WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
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

    public List<Subject> findAll() {
        List<Subject> list = new ArrayList<>();
        String sql = "SELECT * FROM subjects";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
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

        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(extractSubjectFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public PageResult<Subject> searchSubjects(String keyword, int page, int pageSize) {
        List<Subject> list = new ArrayList<>();
        int totalRecords = 0;

        String countSql = "SELECT COUNT(*) FROM subjects WHERE name LIKE ? AND is_deleted = 0";

        String dataSql = "SELECT id, name, credit, is_deleted "
                + "FROM subjects "
                + "WHERE name LIKE ? "
                + "ORDER BY id DESC "
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try (Connection conn = getConnection()) {
            // 1. Đếm số bản ghi
            try (PreparedStatement stmt = conn.prepareStatement(countSql)) {
                String pattern = "%" + keyword + "%";
                stmt.setString(1, pattern);

                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        totalRecords = rs.getInt(1);
                    }
                }
            }

            // 2. Lấy dữ liệu phân trang
            try (PreparedStatement stmt = conn.prepareStatement(dataSql)) {
                String pattern = "%" + keyword + "%";
                stmt.setString(1, pattern);

                int safePage = Math.max(page, 1);
                int offset = (safePage - 1) * pageSize;

                stmt.setInt(2, offset);
                stmt.setInt(3, pageSize);

                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        list.add(extractSubjectFromResultSet(rs));
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new PageResult<>(list, page, pageSize, totalRecords);
    }

    public int countSearchSubjects(String keyword) {
        boolean hasKeyword = keyword != null && !keyword.trim().isEmpty();

        StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM subjects WHERE isdeleted = 0");
        if (hasKeyword) {
            sql.append(" AND name LIKE ?");
        }

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
            if (hasKeyword) {
                stmt.setString(1, "%" + keyword + "%");
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

//    // Trích xuất Subject từ ResultSet
//    private Subject extractSubjectFromResultSet(ResultSet rs) throws SQLException {
//        Subject subject = new Subject();
//        subject.setId(rs.getLong("id"));
//        subject.setName(rs.getString("name"));
//        subject.setCredit(rs.getInt("credit"));
//        subject.setIsdeleted(rs.getBoolean("is_deleted"));
//        return subject;
//    }
}
