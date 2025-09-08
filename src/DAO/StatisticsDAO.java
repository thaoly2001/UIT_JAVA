package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * DAO cho phần Thống kê (Statistics)
 */
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

    public Map<String, Integer> getStudentPerformanceStatistics() {
        String sql = "SELECT "
                + "SUM(CASE WHEN score >= 8.5 THEN 1 ELSE 0 END) AS gioi, "
                + "SUM(CASE WHEN score >= 7.0 AND score < 8.5 THEN 1 ELSE 0 END) AS kha, "
                + "SUM(CASE WHEN score >= 5.0 AND score < 7.0 THEN 1 ELSE 0 END) AS trungbinh, "
                + "SUM(CASE WHEN score < 5.0 THEN 1 ELSE 0 END) AS yeu "
                + "FROM enrollments";

        Map<String, Integer> result = new HashMap<>();
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                result.put("Giỏi", rs.getInt("gioi"));
                result.put("Khá", rs.getInt("kha"));
                result.put("Trung bình", rs.getInt("trungbinh"));
                result.put("Yếu", rs.getInt("yeu"));
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
}
