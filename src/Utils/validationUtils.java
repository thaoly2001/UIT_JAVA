package Utils;

import MODEL.Subject;
import MODEL.Teacher;

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

}
