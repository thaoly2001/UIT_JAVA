
package Utils;

import Constaint.ExportFileName;
import org.apache.poi.ss.usermodel.*;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import javax.swing.*;
import javax.swing.table.TableModel;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

public class ExcelExporter {

    public static <T> void exportToExcel(JTable table, List<T> dataList, ExportFileName defaultFileName, byte[] chartImageBytes, int imageRow, int imageCol) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Chọn nơi lưu file Excel");

        fileChooser.setSelectedFile(new File(defaultFileName.getFileName() + ".xlsx"));

        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();

            if (!fileToSave.getAbsolutePath().endsWith(".xlsx")) {
                fileToSave = new File(fileToSave.getAbsolutePath() + ".xlsx");
            }

            try (Workbook workbook = new XSSFWorkbook()) {
                Sheet sheet = workbook.createSheet("Data");

                // Create a cell style with borders
                CellStyle borderStyle = workbook.createCellStyle();
                borderStyle.setBorderBottom(BorderStyle.THIN);
                borderStyle.setBorderTop(BorderStyle.THIN);
                borderStyle.setBorderLeft(BorderStyle.THIN);
                borderStyle.setBorderRight(BorderStyle.THIN);

                TableModel model = table.getModel();
                Row headerRow = sheet.createRow(0);
                for (int col = 0; col < model.getColumnCount(); col++) {
                    Cell cell = headerRow.createCell(col);
                    cell.setCellValue(model.getColumnName(col));
                    cell.setCellStyle(borderStyle);
                }

                int rowNum = 1;
                for (int i = 0; i < model.getRowCount(); i++) {
                    Row row = sheet.createRow(rowNum++);
                    for (int j = 0; j < model.getColumnCount(); j++) {
                        Object value = model.getValueAt(i, j);
                        Cell cell = row.createCell(j);
                        cell.setCellValue(value != null ? value.toString() : "");
                        cell.setCellStyle(borderStyle);
                    }
                }

                if (chartImageBytes != null && chartImageBytes.length > 0) {
                    int pictureIdx = workbook.addPicture(chartImageBytes, Workbook.PICTURE_TYPE_PNG);
                    XSSFDrawing drawing = (XSSFDrawing) sheet.createDrawingPatriarch();
                    XSSFClientAnchor anchor = new XSSFClientAnchor(0, 0, 0, 0, imageCol, imageRow, imageCol + 10, imageRow + 20);
                    drawing.createPicture(anchor, pictureIdx);
                }

                try (FileOutputStream out = new FileOutputStream(fileToSave)) {
                    workbook.write(out);
                    JOptionPane.showMessageDialog(null, "Xuất Excel thành công: " + fileToSave.getAbsolutePath());
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Lỗi khi export: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public static void exportToExcel(JTable table, ExportFileName defaultFileName, byte[] chartImageBytes, int imageRow, int imageCol) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Chọn nơi lưu file Excel");

        fileChooser.setSelectedFile(new File(defaultFileName.getFileName() + ".xlsx"));

        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();

            if (!fileToSave.getAbsolutePath().endsWith(".xlsx")) {
                fileToSave = new File(fileToSave.getAbsolutePath() + ".xlsx");
            }

            try (Workbook workbook = new XSSFWorkbook()) {
                Sheet sheet = workbook.createSheet("Data");

                // Create a cell style with borders
                CellStyle borderStyle = workbook.createCellStyle();
                borderStyle.setBorderBottom(BorderStyle.THIN);
                borderStyle.setBorderTop(BorderStyle.THIN);
                borderStyle.setBorderLeft(BorderStyle.THIN);
                borderStyle.setBorderRight(BorderStyle.THIN);

                TableModel model = table.getModel();
                Row headerRow = sheet.createRow(0);
                for (int col = 0; col < model.getColumnCount(); col++) {
                    Cell cell = headerRow.createCell(col);
                    cell.setCellValue(model.getColumnName(col));
                    cell.setCellStyle(borderStyle);
                }

                int rowNum = 1;
                for (int i = 0; i < model.getRowCount(); i++) {
                    Row row = sheet.createRow(rowNum++);
                    for (int j = 0; j < model.getColumnCount(); j++) {
                        Object value = model.getValueAt(i, j);
                        Cell cell = row.createCell(j);
                        cell.setCellValue(value != null ? value.toString() : "");
                        cell.setCellStyle(borderStyle);
                    }
                }

                if (chartImageBytes != null && chartImageBytes.length > 0) {
                    int pictureIdx = workbook.addPicture(chartImageBytes, Workbook.PICTURE_TYPE_PNG);
                    XSSFDrawing drawing = (XSSFDrawing) sheet.createDrawingPatriarch();
                    XSSFClientAnchor anchor = new XSSFClientAnchor(0, 0, 0, 0, imageCol, imageRow, imageCol + 10, imageRow + 20);
                    drawing.createPicture(anchor, pictureIdx);
                }

                try (FileOutputStream out = new FileOutputStream(fileToSave)) {
                    workbook.write(out);
                    JOptionPane.showMessageDialog(null, "Xuất Excel thành công: " + fileToSave.getAbsolutePath());
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Lỗi khi export: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public static <T> void exportToExcel(List<String> headers, List<T> dataList, ExportFileName defaultFileName) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Chọn nơi lưu file Excel");

        fileChooser.setSelectedFile(new File(defaultFileName.getFileName() + ".xlsx"));

        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();

            if (!fileToSave.getAbsolutePath().endsWith(".xlsx")) {
                fileToSave = new File(fileToSave.getAbsolutePath() + ".xlsx");
            }

            try (Workbook workbook = new XSSFWorkbook()) {
                Sheet sheet = workbook.createSheet("Data");

                // Create a cell style with borders
                CellStyle borderStyle = workbook.createCellStyle();
                borderStyle.setBorderBottom(BorderStyle.THIN);
                borderStyle.setBorderTop(BorderStyle.THIN);
                borderStyle.setBorderLeft(BorderStyle.THIN);
                borderStyle.setBorderRight(BorderStyle.THIN);

                Row headerRow = sheet.createRow(0);
                for (int col = 0; col < headers.size(); col++) {
                    Cell cell = headerRow.createCell(col);
                    cell.setCellValue(headers.get(col));
                    cell.setCellStyle(borderStyle);
                }

                int rowNum = 1;
                for (T obj : dataList) {
                    Row row = sheet.createRow(rowNum++);
                    int colNum = 0;
                    for (Field field : obj.getClass().getDeclaredFields()) {
                        field.setAccessible(true);
                        Object value = field.get(obj);
                        Cell cell = row.createCell(colNum++);
                        cell.setCellValue(value != null ? value.toString() : "");
                        cell.setCellStyle(borderStyle);
                    }
                }

                try (FileOutputStream out = new FileOutputStream(fileToSave)) {
                    workbook.write(out);
                    JOptionPane.showMessageDialog(null, "Xuất Excel thành công: " + fileToSave.getAbsolutePath());
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Lỗi khi export: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public static <T> void exportToExcel(JTable table, List<T> dataList, ExportFileName defaultFileName) {
        exportToExcel(table, dataList, defaultFileName, null, 0, 0);
    }

    public static void exportToExcel(JTable table, ExportFileName defaultFileName) {
        exportToExcel(table, defaultFileName, null, 0, 0);
    }
}

