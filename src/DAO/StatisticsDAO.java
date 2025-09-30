package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatisticsDAO extends KetNoiCSDL {

    private static StatisticsDAO instance;

    private StatisticsDAO() {
    }

    public static StatisticsDAO getInstance() {
        if (instance == null) {
            instance = new StatisticsDAO();
        }
        return instance;
    }

    public Map<String, Integer> getStudentPerformanceStatistics(String subjectName) {
        StringBuilder sql = new StringBuilder("SELECT "
                + "SUM(CASE WHEN e.score >= 8.5 THEN 1 ELSE 0 END) AS gioi, "
                + "SUM(CASE WHEN e.score >= 7.0 AND e.score < 8.5 THEN 1 ELSE 0 END) AS kha, "
                + "SUM(CASE WHEN e.score >= 5.0 AND e.score < 7.0 THEN 1 ELSE 0 END) AS trungbinh, "
                + "SUM(CASE WHEN e.score < 5.0 THEN 1 ELSE 0 END) AS yeu "
                + "FROM enrollments e ");

        if (subjectName != null && !subjectName.isEmpty() && !subjectName.equals("Tất cả môn học")) {
            sql.append(" JOIN classes c ON e.class_id = c.id ");
            sql.append(" JOIN subjects s ON c.subject_id = s.id ");
            sql.append(" WHERE s.name = ?");
        }

        Map<String, Integer> result = new HashMap<>();
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
            if (subjectName != null && !subjectName.isEmpty() && !subjectName.equals("Tất cả môn học")) {
                stmt.setString(1, subjectName);
            }

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    result.put("Giỏi", rs.getInt("gioi"));
                    result.put("Khá", rs.getInt("kha"));
                    result.put("Trung bình", rs.getInt("trungbinh"));
                    result.put("Yếu", rs.getInt("yeu"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyMap();
        }
        return result;
    }

    public Map<String, Integer> getStudentPerformanceStatistics(String subjectName, String className) {
        StringBuilder sql = new StringBuilder("SELECT "
                + "SUM(CASE WHEN e.score >= 8.5 THEN 1 ELSE 0 END) AS gioi, "
                + "SUM(CASE WHEN e.score >= 7.0 AND e.score < 8.5 THEN 1 ELSE 0 END) AS kha, "
                + "SUM(CASE WHEN e.score >= 5.0 AND e.score < 7.0 THEN 1 ELSE 0 END) AS trungbinh, "
                + "SUM(CASE WHEN e.score < 5.0 THEN 1 ELSE 0 END) AS yeu "
                + "FROM enrollments e ");

        List<String> conditions = new ArrayList<>();
        List<Object> params = new ArrayList<>();

        if (subjectName != null && !subjectName.isEmpty() && !subjectName.equals("Tất cả môn học")) {
            sql.append(" JOIN classes c ON e.class_id = c.id ");
            sql.append(" JOIN subjects s ON c.subject_id = s.id ");
            conditions.add("s.name = ?");
            params.add(subjectName);
        }

        if (className != null && !className.isEmpty() && !className.equals("Tất cả lớp học")) {
            if (!conditions.isEmpty() && (subjectName == null || subjectName.equals("Tất cả môn học"))) {
                sql.append(" JOIN classes c ON e.class_id = c.id ");
            }
            conditions.add("c.name = ?");
            params.add(className);
        }

        if (!conditions.isEmpty()) {
            sql.append(" WHERE ").append(String.join(" AND ", conditions));
        }

        Map<String, Integer> result = new HashMap<>();
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
            for (int i = 0; i < params.size(); i++) {
                stmt.setObject(i + 1, params.get(i));
            }

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    result.put("Giỏi", rs.getInt("gioi"));
                    result.put("Khá", rs.getInt("kha"));
                    result.put("Trung bình", rs.getInt("trungbinh"));
                    result.put("Yếu", rs.getInt("yeu"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyMap();
        }
        return result;
    }

    public Map<String, Map<String, Integer>> getSubjectResultStatistics() {
        String sql = "SELECT sub.name AS subject_name, "
                + "SUM(CASE WHEN e.score >= 5.0 THEN 1 ELSE 0 END) AS qua_mon, "
                + "SUM(CASE WHEN e.score < 5.0 THEN 1 ELSE 0 END) AS rot_mon "
                + "FROM enrollments e "
                + "JOIN classes c ON e.class_id = c.id "
                + "JOIN subjects sub ON c.subject_id = sub.id "
                + "WHERE e.is_deleted = 0 "
                + "GROUP BY sub.name";

        Map<String, Map<String, Integer>> result = new HashMap<>();
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String subjectName = rs.getString("subject_name");
                int quaMon = rs.getInt("qua_mon");
                int rotMon = rs.getInt("rot_mon");

                Map<String, Integer> subResult = new HashMap<>();
                subResult.put("Qua môn", quaMon);
                subResult.put("Rớt môn", rotMon);

                result.put(subjectName, subResult);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyMap();
        }

        return result;
    }

    public List<Map<String, Object>> getSubjectResultStatistics(int limit, String subjectName) {
        List<Map<String, Object>> stats = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT " + (limit > 0 ? "TOP (?) " : "") + "s.name AS subjectName, COUNT(DISTINCT st.id) AS studentCount " +
                     "FROM subjects s " +
                     "JOIN classes c ON s.id = c.subject_id " +
                     "JOIN enrollments e ON c.id = e.class_id " +
                     "JOIN students st ON e.student_id = st.id ");
        
        List<String> conditions = new ArrayList<>();
        List<Object> params = new ArrayList<>();

        if (subjectName != null && !subjectName.isEmpty() && !subjectName.equals("Tất cả môn học")) {
            conditions.add("s.name = ?");
            params.add(subjectName);
        }

        if (!conditions.isEmpty()) {
            sql.append(" WHERE ").append(String.join(" AND ", conditions));
        }

        sql.append(" GROUP BY s.name " +
                     "ORDER BY studentCount DESC");

    
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql.toString())) {
    
            int paramIndex = 1;
            if (limit > 0) {
                preparedStatement.setInt(paramIndex++, limit);
            }
            for (Object param : params) {
                preparedStatement.setObject(paramIndex++, param);
            }
    
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Map<String, Object> stat = new HashMap<>();
                stat.put("subjectName", rs.getString("subjectName"));
                stat.put("studentCount", rs.getLong("studentCount"));
                stats.add(stat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stats;
    }
}
