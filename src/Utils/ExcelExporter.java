
package Utils;

import Constaint.ExportFileName;
import org.apache.poi.ss.usermodel.*;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import javax.swing.*;
import javax.swing.table.TableModel;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

public class ExcelExporter {

    public static <T> void exportToExcel(JTable table, List<T> dataList, ExportFileName defaultFileName) {
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

            TableModel model = table.getModel();
            Row headerRow = sheet.createRow(0);
            for (int col = 0; col < model.getColumnCount(); col++) {
                headerRow.createCell(col).setCellValue(model.getColumnName(col));
            }

            int rowNum = 1;
            for (T obj : dataList) {
                Row row = sheet.createRow(rowNum++);
                int colNum = 0;
                for (Field field : obj.getClass().getDeclaredFields()) {
                    field.setAccessible(true);
                    Object value = field.get(obj);
                    row.createCell(colNum++).setCellValue(value != null ? value.toString() : "");
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

}

