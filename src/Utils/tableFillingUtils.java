/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import MODEL.Classes;
import MODEL.Enrollment;
import MODEL.Student;
import MODEL.Subject;
import MODEL.Teacher;

/**
 *
 * @author ADMIN
 */
public class tableFillingUtils {

    public static Object[] fillStu(Student stu) {
        return new Object[]{
            stu.getId(),
            stu.getName(),
            stu.getEmail(),
            stu.getPhone(),
            stu.getGender(),
            stu.getBirthday()
        };
    }
   public static Object[] fillStuSearch(Student stu) {
        return new Object[]{
            stu.getId(),
            stu.getName(),
            stu.getEmail()
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
            sub.getCredit(),
            sub.isIsdeleted()
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
            classes.getTeacher() != null ? classes.getTeacher().getName() : "Chưa phân công",
            classes.isDeleted() ? "Đã xóa" : "Còn hoạt động"
        };
    }

}
