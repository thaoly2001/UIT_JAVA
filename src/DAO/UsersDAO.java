package DAO;

import Utils.EmailUtil;
import MODEL.Users;
import java.sql.ResultSet;
import java.sql.*;
import java.util.Vector;
import javax.swing.JOptionPane;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import Utils.PageResult;
import Constaint.ActionPaging;
import java.util.ArrayList;
import java.util.List;
import Utils.EmailUtil;

public class UsersDAO extends KetNoiCSDL {

    private static UsersDAO instance;

    public static UsersDAO getInstance() {
        if (instance == null) {
            instance = new UsersDAO();
        }
        return instance;
    }

    public UsersDAO() {
        super(); 
    }

    public Users login(String username, String password) {
        Users user = null;
        String sql = "SELECT * FROM Users u WHERE u.username = ? AND u.password = ? AND u.is_deleted = 0";
        try (Connection conn = getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                user = new Users();
                user.setId(rs.getLong("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getByte("role"));
                user.setEmail(rs.getString("email"));
                user.setIsDeleted(rs.getBoolean("is_deleted"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public boolean forgotPassword(Long id) {
        String username = null;
        String email = null;

        String selectSql = "SELECT username, email FROM Users WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement selectStmt = conn.prepareStatement(selectSql)) {
            selectStmt.setLong(1, id);
            ResultSet rs = selectStmt.executeQuery();
            if (rs.next()) {
                username = rs.getString("username");
                email = rs.getString("email");
            } else {
                System.out.println("Người dùng với ID " + id + " không tồn tại.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        if (username == null || email == null || email.isEmpty()) {
            System.out.println("Không tìm thấy email liên kết cho người dùng có ID " + id + " hoặc email trống.");
            return false;
        }

        String newPassword = EmailUtil.generateRandomPassword(16);
        String updateSql = "UPDATE Users SET password = ? WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
            updateStmt.setString(1, newPassword);
            updateStmt.setLong(2, id);

            if (updateStmt.executeUpdate() > 0) {
                String subject = "Mật khẩu mới của bạn";
                String content = "Kính gửi " + username + ",\n\n" +
                                 "Bạn đã yêu cầu đặt lại mật khẩu. Mật khẩu mới của bạn là:\n\n" +
                                 "Mật khẩu: " + newPassword + "\n\n" +
                                 "Vui lòng đăng nhập và thay đổi mật khẩu của bạn ngay lập tức để đảm bảo an toàn.\n\n" +
                                 "Trân trọng,\n" +
                                 "Hệ thống quản lý";
                EmailUtil.sendEmail(email, subject, content);
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean add(Users user) {
        String sql = "INSERT INTO Users (username, password, role, email, is_deleted) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            String generatedPassword = EmailUtil.generateRandomPassword(16);
            user.setPassword(generatedPassword);

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword()); 
            stmt.setByte(3, user.getRole());
            stmt.setString(4, user.getEmail());
            stmt.setBoolean(5, user.isIsDeleted());
            if (stmt.executeUpdate() > 0) {
                String subject = "Mật khẩu tài khoản của bạn";
                String content = "Kính gửi " + user.getUsername() + ",\n\n" +
                                 "Tài khoản của bạn đã được tạo thành công.\n" +
                                 "Dưới đây là thông tin đăng nhập của bạn:\n\n" +
                                 "Tên đăng nhập: " + user.getUsername() + "\n" +
                                 "Mật khẩu: " + generatedPassword + "\n\n" + 
                                 "Vui lòng thay đổi mật khẩu của bạn sau khi đăng nhập lầu để đảm bảo an toàn.\n\n" +
                                 "Trân trọng,\n" +
                                 "Hệ thống quản lý";
                EmailUtil.sendEmail(user.getEmail(), subject, content);
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(Users user) {
        String sql = "UPDATE Users SET username = ?, password = ?, role = ?, email = ?, is_deleted = ? WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setByte(3, user.getRole());
            stmt.setString(4, user.getEmail());
            stmt.setBoolean(5, user.isIsDeleted());
            stmt.setLong(6, user.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(long id) {
        String sql = "DELETE FROM Users WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Users findById(long id) {
        Users user = null;
        String sql = "SELECT * FROM Users WHERE id = ? AND is_deleted = 0";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                user = new Users();
                user.setId(rs.getLong("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getByte("role"));
                user.setEmail(rs.getString("email"));
                user.setIsDeleted(rs.getBoolean("is_deleted"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public Users findByEmail(String email) {
        Users user = null;
        String sql = "SELECT * FROM Users WHERE email = ? AND is_deleted = 0";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                user = mapResultSetToUsers(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    private Users mapResultSetToUsers(ResultSet rs) throws SQLException {
        Users user = new Users();
        user.setId(rs.getLong("id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setRole(rs.getByte("role"));
        user.setEmail(rs.getString("email"));
        user.setIsDeleted(rs.getBoolean("is_deleted"));
        return user;
    }

    public PageResult<Users> search(String keyword, int page, int pageSize) {
        List<Users> list = new ArrayList<>();
        int totalRecords = 0;

        StringBuilder selectSql = new StringBuilder("WITH filtered AS ( "); 
        selectSql.append("SELECT id, username, password, role, email, is_deleted "); 
        selectSql.append("FROM users "); 
        selectSql.append("WHERE is_deleted = 0 "); 
    
        if (keyword != null && !keyword.trim().isEmpty()) { 
            selectSql.append("AND (username LIKE ? OR email LIKE ?) "); 
        } 
        selectSql.append(") "); 
        selectSql.append("SELECT *, COUNT(*) OVER() AS total_count ");
        selectSql.append("FROM filtered ");
        selectSql.append("ORDER BY id DESC ");
        selectSql.append("OFFSET ? ROWS FETCH NEXT ? ROWS ONLY");

        try (Connection conn = getConnection();
             PreparedStatement selectStmt = conn.prepareStatement(selectSql.toString())) {
            int paramIndex = 1;
            if (keyword != null && !keyword.trim().isEmpty()) {
                String searchKeyword = "%" + keyword.trim() + "%";
                selectStmt.setString(paramIndex++, searchKeyword);
                selectStmt.setString(paramIndex++, searchKeyword);
            }
            int validPageSize = Math.max(pageSize, 1);
            int offset = Math.max(page - 1, 0) * validPageSize;
            selectStmt.setInt(paramIndex++, offset);
            selectStmt.setInt(paramIndex++, validPageSize);

            try (ResultSet rs = selectStmt.executeQuery()) {
                while (rs.next()) {
                    if (totalRecords == 0) {
                        totalRecords = rs.getInt("total_count");
                    }
                    list.add(mapResultSetToUsers(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new PageResult<>(list, page, pageSize, totalRecords);
    }
}
