package Constaint;

public enum ExportFileName {
    CLASSES("DanhSachLopHoc"),
    STUDENT("DanhSachHocSinh"),
    TEACHER("DanhSachGiaoVien"),
    SUBJECT("DanhSachMonHoc"),
    STATISTICS_BY_PERFORMANCE("ThongKeTheoHocLuc"),
    STATISTICS_BY_SUBJECT("ThongKeTheoMonHoc");

    private final String fileName;

    ExportFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
