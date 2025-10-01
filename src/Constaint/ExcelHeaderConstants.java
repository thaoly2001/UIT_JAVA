package Constaint;

import java.util.Arrays;
import java.util.List;

public class ExcelHeaderConstants {

    public static final List<String> STUDENT_HEADERS = Arrays.asList(
            "ID", "Tên", "Email", "Điện thoại", "Địa chỉ", "Giới tính", "Ngày sinh", "Hình ảnh"
    );

    public static final List<String> CLASS_HEADERS = List.of(
            "ID", "Tên lớp", "Mô tả", "Giáo viên"
    );

    public static final List<String> TEACHER_HEADERS = List.of(
            "ID", "Tên", "Email", "Số điện thoại","Địa chỉ", "Giới Tính", "Ngày Sinh", "Hình ảnh"
    );

    public static final List<String> SUBJECT_HEADERS = List.of(
            "ID", "Tên môn học", "Số tín chỉ"
    );
}