/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

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
            "",
            "",
            "",
            ""
        };
    }
     public static Object[] fillTeacher(Teacher te) {
        return new Object[]{
            te.getId(),
            te.getName(),
            te.getEmail(),
            te.getPhone(),
            te.getGender(),
            te.getDepartment()
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
}
