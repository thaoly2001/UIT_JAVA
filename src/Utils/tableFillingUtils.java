package Utils;

import MODEL.Classes;
import MODEL.Enrollment;
import MODEL.Student;
import MODEL.Subject;
import MODEL.Teacher;

public class tableFillingUtils {

    public static Object[] fillStu(Student stu) {
        return new Object[]{
            stu.getId(),
            stu.getName(),
            stu.getEmail(),
            stu.getPhone(),
            stu.getAddress(),
            stu.getBirthday(),
            stu.getGender(),};
    }

    public static Object[] fillStuSearch(Student stu) {
        return new Object[]{
            stu.getId(),
            stu.getName(),
            stu.getEmail()
        };
    }

    public static Object[] fillStuSWithScore(Enrollment stu) {
        return new Object[]{
            stu.getStudent().getId(),
            stu.getStudent().getName(),
            stu.getStudent().getEmail(),
            stu.getScore()
        };
    }

    public static Object[] fillTeacher(Teacher te) {
        return new Object[]{
            te.getId(),
            te.getName(),
            te.getEmail(),
            te.getPhone(),
            te.getGender()
        };
    }

    public static Object[] fillSubject(Subject sub) {
        return new Object[]{
            sub.getId(),
            sub.getName(),
            sub.getCredit()
        };
    }

    public static Object[] fillSubSearch(Subject stu) {
        return new Object[]{
            stu.getId(),
            stu.getName(),
            stu.getCredit()
        };
    }

    public static Object[] fillClasses(Classes classes) {
        return new Object[]{
            classes.getId(),
            classes.getName(),
            classes.getSubject() != null ? classes.getSubject().getName() : "Không rõ",
            classes.getTeacher() != null ? classes.getTeacher().getName() : "Chưa phân công",};
    }

    public static Object[] fillUser(MODEL.Users user) {
        String roleString;
        switch (user.getRole()) {
            case 0:
                roleString = "Quản trị viên";
                break;
            case 1:
                roleString = "Giáo viên";
                break;
            case 2:
                roleString = "Sinh viên";
                break;
            default:
                roleString = "Không xác định";
                break;
        }
        return new Object[]{
            user.getId(),
            user.getUsername(),
            roleString,
            user.getEmail()
        };
    }
}
