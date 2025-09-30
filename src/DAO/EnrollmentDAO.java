package DAO;

import MODEL.Classes;
import MODEL.Enrollment;
import MODEL.Student;
import MODEL.Subject;
import MODEL.Teacher;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentDAO extends KetNoiCSDL {

    private static EnrollmentDAO instance;

    public static EnrollmentDAO getInstance() {
        if (instance == null) {
            instance = new EnrollmentDAO();
        }
        return instance;
    }

    public EnrollmentDAO() {
    }

    public List<Enrollment> getAll() {
        List<Enrollment> list = new ArrayList<>();
        String sql = "SELECT e.id AS e_id, e.score, e.enrollmentDate, "
                + "s.id AS s_id, s.name AS s_name, s.email AS s_email, "
                + "c.id AS c_id, c.name AS c_name, "
                + "sub.id AS sub_id, sub.name AS sub_name "
                + "FROM enrollments e "
                + "JOIN students s ON e.student_id = s.id "
                + "JOIN classes c ON e.class_id = c.id "
                + "JOIN subjects sub ON c.subject_id = sub.id "
                + "WHERE e.is_deleted = 0";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Enrollment enrollment = new Enrollment();
                enrollment.setId(rs.getLong("e_id"));
                enrollment.setScore(rs.getDouble("score"));
                enrollment.setEnrollmentDate(rs.getDate("enrollmentDate").toLocalDate());

                Student student = new Student();
                student.setId(rs.getLong("s_id"));
                student.setName(rs.getString("s_name"));
                student.setEmail(rs.getString("s_email"));
                enrollment.setStudent(student);

                Subject subject = new Subject();
                subject.setId(rs.getLong("sub_id"));
                subject.setName(rs.getString("sub_name"));

                Classes classes = new Classes();
                classes.setId(rs.getLong("c_id"));
                classes.setName(rs.getString("c_name"));
                classes.setSubject(subject);

                enrollment.setClasses(classes);

                list.add(enrollment);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<Enrollment> findByEmail(String email) {
        List<Enrollment> list = new ArrayList<>();
        String sql = "SELECT e.id AS e_id, e.score, e.enrollment_date, "
                + "s.id AS s_id, s.name AS s_name, s.email AS s_email, "
                + "c.id AS c_id, c.name AS c_name, "
                + "sub.id AS sub_id, sub.name AS sub_name, "
                + "t.id AS t_id, t.name AS t_name, t.email AS t_email "
                + "FROM enrollments e "
                + "JOIN students s ON e.student_id = s.id "
                + "JOIN classes c ON e.class_id = c.id "
                + "JOIN subjects sub ON c.subject_id = sub.id "
                + "JOIN teachers t ON c.teacher_id = t.id "
                + "WHERE s.email = ? AND e.is_deleted = 0";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Enrollment enrollment = new Enrollment();
                enrollment.setId(rs.getLong("e_id"));
                enrollment.setScore(rs.getDouble("score"));
                enrollment.setEnrollmentDate(rs.getDate("enrollment_date").toLocalDate());

                Student student = new Student();
                student.setId(rs.getLong("s_id"));
                student.setName(rs.getString("s_name"));
                student.setEmail(rs.getString("s_email"));
                enrollment.setStudent(student);

                Subject subject = new Subject();
                subject.setId(rs.getLong("sub_id"));
                subject.setName(rs.getString("sub_name"));

                Teacher teacher = new Teacher();
                teacher.setId(rs.getLong("t_id"));
                teacher.setName(rs.getString("t_name"));
                teacher.setEmail(rs.getString("t_email"));

                Classes classes = new Classes();
                classes.setId(rs.getLong("c_id"));
                classes.setName(rs.getString("c_name"));
                classes.setSubject(subject);
                classes.setTeacher(teacher);

                enrollment.setClasses(classes);

                list.add(enrollment);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<Enrollment> getByStudentId(Long studentId) {
        List<Enrollment> list = new ArrayList<>();
        String sql = "SELECT e.id AS e_id, e.score, e.enrollment_date, "
                + "st.id AS st_id, st.name AS st_name, st.email AS st_email, "
                + "c.id AS c_id, c.name AS c_name, "
                + "t.id AS t_id, t.name AS t_name, "
                + "sub.id AS sub_id, sub.name AS sub_name, sub.credit, sub.is_deleted "
                + "FROM enrollments e "
                + "JOIN students st ON e.student_id = st.id "
                + "JOIN classes c ON e.class_id = c.id "
                + "JOIN teachers t ON c.teacher_id = t.id "
                + "JOIN subjects sub ON c.subject_id = sub.id "
                + "WHERE e.student_id = ? "
                + "AND e.is_deleted = 0 "
                + "AND st.is_deleted = 0 "
                + "AND c.is_deleted = 0 "
                + "AND t.is_deleted = 0 "
                + "AND sub.is_deleted = 0";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, studentId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Enrollment enrollment = new Enrollment();
                enrollment.setId(rs.getLong("e_id"));
                enrollment.setScore(rs.getDouble("score"));
                enrollment.setEnrollmentDate(rs.getDate("enrollment_date").toLocalDate());

                Student student = new Student();
                student.setId(rs.getLong("st_id"));
                student.setName(rs.getString("st_name"));
                student.setEmail(rs.getString("st_email"));
                enrollment.setStudent(student);

                Teacher teacher = new Teacher();
                teacher.setId(rs.getLong("t_id"));
                teacher.setName(rs.getString("t_name"));

                Subject subject = new Subject();
                subject.setId(rs.getLong("sub_id"));
                subject.setName(rs.getString("sub_name"));
                subject.setCredit(rs.getInt("credit"));
                subject.setIsdeleted(rs.getBoolean("is_deleted"));

                Classes classes = new Classes();
                classes.setId(rs.getLong("c_id"));
                classes.setName(rs.getString("c_name"));
                classes.setSubject(subject);
                classes.setTeacher(teacher);

                enrollment.setClasses(classes);

                list.add(enrollment);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }

    public List<Enrollment> getByClassId(Long classId) {
        List<Enrollment> list = new ArrayList<>();
        String sql = "SELECT e.id AS e_id, e.score, e.enrollment_date, "
                + "s.id AS s_id, s.name AS s_name, s.email AS s_email, "
                + "c.id AS c_id, c.name AS c_name, "
                + "sub.id AS sub_id, sub.name AS sub_name, sub.credit, sub.is_deleted "
                + "FROM enrollments e "
                + "JOIN students s ON e.student_id = s.id "
                + "JOIN classes c ON e.class_id = c.id "
                + "JOIN subjects sub ON c.subject_id = sub.id "
                + "WHERE e.class_id = ? AND e.is_deleted = 0";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, classId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Enrollment enrollment = new Enrollment();
                enrollment.setId(rs.getLong("e_id"));
                enrollment.setScore(rs.getDouble("score"));
                enrollment.setEnrollmentDate(rs.getDate("enrollment_date").toLocalDate());

                Student student = new Student();
                student.setId(rs.getLong("s_id"));
                student.setName(rs.getString("s_name"));
                student.setEmail(rs.getString("s_email"));
                enrollment.setStudent(student);

                Subject subject = new Subject();
                subject.setId(rs.getLong("sub_id"));
                subject.setName(rs.getString("sub_name"));
                subject.setCredit(rs.getInt("credit"));
                subject.setIsdeleted(rs.getBoolean("is_deleted"));

                Classes classes = new Classes();
                classes.setId(rs.getLong("c_id"));
                classes.setName(rs.getString("c_name"));
                classes.setSubject(subject);

                enrollment.setClasses(classes);

                list.add(enrollment);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }

    public List<Enrollment> getByClassId(Long classId, int page, int pageSize) {
        List<Enrollment> list = new ArrayList<>();

        if (page <= 0) {
            page = 1;
        }

        String sql = "SELECT e.id AS e_id, e.score, e.enrollment_date, "
                + "s.id AS s_id, s.name AS s_name, s.email AS s_email, "
                + "c.id AS c_id, c.name AS c_name, "
                + "sub.id AS sub_id, sub.name AS sub_name, sub.credit, sub.is_deleted "
                + "FROM enrollments e "
                + "JOIN students s ON e.student_id = s.id "
                + "JOIN classes c ON e.class_id = c.id "
                + "JOIN subjects sub ON c.subject_id = sub.id "
                + "WHERE e.class_id = ? AND e.is_deleted = 0 "
                + "ORDER BY e.id";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, classId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Enrollment enrollment = new Enrollment();
                enrollment.setId(rs.getLong("e_id"));
                enrollment.setScore(rs.getDouble("score"));
                enrollment.setEnrollmentDate(rs.getDate("enrollment_date").toLocalDate());

                Student student = new Student();
                student.setId(rs.getLong("s_id"));
                student.setName(rs.getString("s_name"));
                student.setEmail(rs.getString("s_email"));
                enrollment.setStudent(student);

                Subject subject = new Subject();
                subject.setId(rs.getLong("sub_id"));
                subject.setName(rs.getString("sub_name"));
                subject.setCredit(rs.getInt("credit"));
                subject.setIsdeleted(rs.getBoolean("is_deleted"));

                Classes classes = new Classes();
                classes.setId(rs.getLong("c_id"));
                classes.setName(rs.getString("c_name"));
                classes.setSubject(subject);

                enrollment.setClasses(classes);

                list.add(enrollment);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }

    public List<Enrollment> searchEnrollments(String keyword, int page, int pageSize) {
        List<Enrollment> list = new ArrayList<>();
        boolean hasKeyword = keyword != null && !keyword.trim().isEmpty();

        StringBuilder sql = new StringBuilder(
                "SELECT e.id AS eid, e.score, e.enrollment_date, "
                + "s.id AS sid, s.name AS sname, s.email, s.phone, s.address, s.gender, s.birthday, "
                + "c.id AS cid, c.name AS cname, "
                + "sub.id AS subid, sub.name AS subname, sub.credit, sub.is_deleted AS sub_deleted "
                + "FROM enrollments e "
                + "JOIN students s ON e.student_id = s.id "
                + "JOIN classes c ON e.class_id = c.id "
                + "JOIN subjects sub ON c.subject_id = sub.id "
                + "WHERE e.is_deleted = 0 "
        );

        if (hasKeyword) {
            sql.append("AND (s.name LIKE ? OR sub.name LIKE ? OR c.name LIKE ?) ");
        }

        sql.append("ORDER BY e.id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY");

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
            int paramIndex = 1;

            if (hasKeyword) {
                stmt.setString(paramIndex++, "%" + keyword + "%");
                stmt.setString(paramIndex++, "%" + keyword + "%");
                stmt.setString(paramIndex++, "%" + keyword + "%");
            }

            stmt.setInt(paramIndex++, (page - 1) * pageSize);
            stmt.setInt(paramIndex, pageSize);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Student student = new Student();
                    student.setId(rs.getLong("sid"));
                    student.setName(rs.getString("sname"));
                    student.setEmail(rs.getString("email"));
                    student.setPhone(rs.getString("phone"));
                    student.setAddress(rs.getString("address"));
                    student.setGender(rs.getString("gender"));
                    student.setBirthday(rs.getDate("birthday"));

                    Subject subject = new Subject();
                    subject.setId(rs.getLong("subid"));
                    subject.setName(rs.getString("subname"));
                    subject.setCredit(rs.getInt("credit"));
                    subject.setIsdeleted(rs.getBoolean("sub_deleted"));

                    Classes classes = new Classes();
                    classes.setId(rs.getLong("cid"));
                    classes.setName(rs.getString("cname"));
                    classes.setSubject(subject);

                    Enrollment e = new Enrollment();
                    e.setId(rs.getLong("eid"));
                    e.setStudent(student);
                    e.setClasses(classes);
                    e.setScore(rs.getDouble("score"));
                    e.setEnrollmentDate(rs.getDate("enrollment_date").toLocalDate());

                    list.add(e);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public int countSearchEnrollments(String keyword) {
        boolean hasKeyword = keyword != null && !keyword.trim().isEmpty();

        StringBuilder sql = new StringBuilder(
                "SELECT COUNT(*) "
                + "FROM enrollments e "
                + "JOIN students s ON e.student_id = s.id "
                + "JOIN subjects sub ON e.subject_id = sub.id"
        );

        if (hasKeyword) {
            sql.append(" WHERE s.name LIKE ? OR sub.name LIKE ?");
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

    public boolean insert(Enrollment enrollment) {
        String sql = "INSERT INTO enrollments(student_id, class_id, score, is_deleted) "
                + "VALUES (?, ?, ?, 0)";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, enrollment.getStudent().getId());
            ps.setLong(2, enrollment.getClasses().getId());
            ps.setDouble(3, enrollment.getScore());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(Enrollment enrollment) {
        String sql = "UPDATE enrollments "
                + "SET student_id=?, class_id=?, score=? WHERE id=?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, enrollment.getStudent().getId());
            ps.setLong(2, enrollment.getClasses().getId());
            ps.setDouble(3, enrollment.getScore());
            ps.setLong(4, enrollment.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
public boolean updateByStudentAndClass(long studentId, long classId, double score) {
    String sql = "UPDATE enrollments "
               + "SET score=? "
               + "WHERE student_id=? AND class_id=? AND is_deleted=0";
    try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setDouble(1, score);
        ps.setLong(2, studentId);
        ps.setLong(3, classId);
        return ps.executeUpdate() > 0;
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}
    public boolean softDelete(Long id) {
        String sql = "UPDATE enrollments SET is_deleted=1 WHERE id=?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
