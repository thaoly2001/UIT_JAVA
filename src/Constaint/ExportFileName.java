package Constaint;

public enum ExportFileName {
    CLASSES("DanhSachLopHoc"),
    STUDENT("DanhSachHocSinh"),
    TEACHER("DanhSachGiaoVien"),
    SUBJECT("DanhSachMonHoc");

    private final String fileName;

    ExportFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
