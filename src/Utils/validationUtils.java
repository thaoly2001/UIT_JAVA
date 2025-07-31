package Utils;

import MODEL.Classes;
import MODEL.Student;
import MODEL.Subject;
import MODEL.Teacher;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 *
 * @author ADMIN
 */
public class validationUtils {

    public static String validateTeacher(Teacher te) {
        if (te == null) {
            return "Giáo viên không được null.";
        }
        if (te.getName() == null || te.getName().trim().isEmpty()) {
            return "Tên không được để trống.";
        }
        if (te.getEmail() == null || !te.getEmail().matches("^[\\w.-]+@[\\w.-]+\\.\\w{2,}$")) {
            return "Email không hợp lệ.";
        }
        if (te.getPhone() == null || !te.getPhone().matches("^0\\d{9}$")) {
            return "Số điện thoại không hợp lệ.";
        }
        if (te.getAddress() == null || te.getAddress().trim().isEmpty()) {
            return "Địa chỉ không được để trống.";
        }
        if (te.getGender() == null
                || (!te.getGender().equalsIgnoreCase("Nam")
                && !te.getGender().equalsIgnoreCase("Nữ")
                && !te.getGender().equalsIgnoreCase("Khác"))) {
            return "Giới tính không hợp lệ.";
        }
        if (te.getBirthday() == null || te.getBirthday().isAfter(java.time.LocalDate.now())) {
            return "Ngày sinh không hợp lệ.";
        }
        if (te.getDepartment() == null || te.getDepartment().trim().isEmpty()) {
            return "Khoa/Bộ môn không được để trống.";
        }

        return null; // Hợp lệ
    }

    public static String validateSubject(Subject subject) {
        if (subject == null) {
            return "Môn học không được null.";
        }

        if (subject.getName() == null || subject.getName().trim().isEmpty()) {
            return "Tên môn học không được để trống.";
        }

        if (subject.getCredit() < 1 || subject.getCredit() > 10) {
            return "Số tín chỉ phải nằm trong khoảng từ 1 đến 10.";
        }

        return null;
    }

    public static String validateStudent(Student student) {
        if (student == null) {
            return "Sinh viên không được null.";
        }

        if (student.getName() == null || student.getName().trim().isEmpty()) {
            return "Tên sinh viên không được để trống.";
        }

        if (student.getEmail() == null || student.getEmail().trim().isEmpty()) {
            return "Email không được để trống.";
        }

        if (!student.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            return "Email không hợp lệ.";
        }

        if (student.getPhone() == null || student.getPhone().trim().isEmpty()) {
            return "Số điện thoại không được để trống.";
        }

        if (!student.getPhone().matches("^\\d{9,11}$")) {
            return "Số điện thoại phải có từ 9 đến 11 chữ số.";
        }

        if (student.getAddress() == null || student.getAddress().trim().isEmpty()) {
            return "Địa chỉ không được để trống.";
        }

        if (student.getGender() == null || (!student.getGender().equalsIgnoreCase("Nam") && !student.getGender().equalsIgnoreCase("Nữ"))) {
            return "Giới tính phải là Nam hoặc Nữ.";
        }

        if (student.getBirthday() == null) {
            return "Ngày sinh không được để trống.";
        }

        if (student.getBirthday().isAfter(LocalDate.now())) {
            return "Ngày sinh không được lớn hơn ngày hiện tại.";
        }
        
        String validDate = validateDate(student.getBirthday().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        
        return validDate==null?null:validDate;
    }

    public static String validateDate(String dateStr) {
        if (dateStr == null || dateStr.trim().isEmpty()) {
            return "Ngày không được để trống.";
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try {
            LocalDate date = LocalDate.parse(dateStr, formatter);

            if (date.isAfter(LocalDate.now())) {
                return"Ngày không được lớn hơn ngày hiện tại.";
            }

        } catch (DateTimeParseException e) {
            return "Định dạng ngày không hợp lệ. Định dạng đúng là dd/MM/yyyy (ví dụ: 31/12/2023).";
        }
        return null;
    }
    
    public static String validateClasses(Classes cls) {
    if (cls == null) {
        return "Lớp học không được null.";
    }

    if (cls.getName() == null || cls.getName().trim().isEmpty()) {
        return "Tên lớp không được để trống.";
    }

    if (cls.getSubject() == null) {
        return "Môn học không được để trống.";
    }

    if (cls.getTeacher() == null) {
        return "Giảng viên không được để trống.";
    }

    return null; // Hợp lệ
}

}
