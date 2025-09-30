package Constaint;

import java.util.Arrays;
import java.util.List;

public class ExcelHeaderConstants {

    public static final List<String> STUDENT_HEADERS = Arrays.asList(
            "ID", "Tên", "Email", "Điện thoại", "Địa chỉ", "Giới tính", "Ngày sinh"
    );

    public static final List<String> CLASS_HEADERS = List.of(
            "ID", "Tên lớp", "Mô tả", "Giáo viên", "Môn học", "Số lượng", "Ngày tạo"
    );

    public static final List<String> TEACHER_HEADERS = List.of(
            "ID", "Tên", "Email", "Số điện thoại", "Giới Tính", "Ngày tạo", "Ngày cập nhật"
    );}