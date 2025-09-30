package GUI.admin;

import DAO.StatisticsDAO;
import DAO.SubjectDAO;
import MODEL.Subject;
import java.util.List;
import java.util.Map;
import org.jfree.chart.*;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import Utils.ExcelExporter;
import Constaint.ExportFileName;
import java.awt.FlowLayout;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.ChartPanel;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class StatisticsPanel extends javax.swing.JPanel {

    public StatisticsPanel() {
        initComponents();
        loadSubjectsIntoComboBox();
        loadCharts();
    }

    public void loadCharts() {
        // All tabs are now added in initComponents(), so this method can be empty.
    }

    private void loadSubjectsIntoComboBox() {
        subjectComboBox.addItem("Tất cả môn học"); // Add an "All Subjects" option
        List<Subject> subjects = SubjectDAO.getInstance().findAllNotDeleted();
        for (Subject subject : subjects) {
            subjectComboBox.addItem(subject.getName());
        }
        subjectComboBox.addActionListener(e -> {
            loadClassesIntoComboBox((String) subjectComboBox.getSelectedItem());
            refreshTinhTrangChart();
        });
        loadClassesIntoComboBox("Tất cả môn học"); // Load classes for "All Subjects" initially
    }

    private void loadClassesIntoComboBox(String subjectName) {
        classComboBox.removeAllItems();
        classComboBox.addItem("Tất cả lớp học"); // Add an "All Classes" option
        if (!"Tất cả môn học".equals(subjectName)) {
            List<MODEL.Classes> classes = DAO.ClassesDAO.getInstance().findClassesBySubjectName(subjectName);
            for (MODEL.Classes cls : classes) {
                classComboBox.addItem(cls.getName());
            }
        }
        classComboBox.addActionListener(e -> refreshTinhTrangChart());
    }

    private JFreeChart createTinhTrangChart() {
        return createTinhTrangChart(null, null);
    }

    private JFreeChart createTinhTrangChart(String subjectName, String className) {
        DefaultPieDataset dataset = new DefaultPieDataset();

        Map<String, Integer> data = StatisticsDAO.getInstance().getStudentPerformanceStatistics(subjectName, className);
        for (Map.Entry<String, Integer> entry : data.entrySet()) {
            if (entry.getValue() > 0) {
                dataset.setValue(entry.getKey(), entry.getValue());
            }
        }

        String chartTitle = "Thống kê tình trạng học tập";
        if (subjectName != null && !subjectName.equals("Tất cả môn học")) {
            chartTitle += " theo " + subjectName;
        }
        if (className != null && !className.equals("Tất cả lớp học")) {
            chartTitle += " - Lớp " + className;
        }

        JFreeChart chart = ChartFactory.createPieChart(
                chartTitle,
                dataset,
                true, true, false
        );

        return chart;
    }

    private void refreshTinhTrangChart() {
        String selectedSubject = (String) subjectComboBox.getSelectedItem();
        String selectedClass = (String) classComboBox.getSelectedItem();
        JPanel tinhTrangPanel = (JPanel) table.getComponentAt(table.indexOfTab("Theo học lực"));
        tinhTrangPanel.remove(1); // Remove old chart
        ChartPanel chartPanel = new ChartPanel(createTinhTrangChart(selectedSubject, selectedClass));
        tinhTrangPanel.add(chartPanel, BorderLayout.CENTER); // Add new chart
        tinhTrangPanel.revalidate();
        tinhTrangPanel.repaint();
    }

    private JFreeChart createKetQuaMonChart(int limit, String subjectName) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        StatisticsDAO statisticsDAO = StatisticsDAO.getInstance();
        try {
            List<Map<String, Object>> subjectResults = statisticsDAO.getSubjectResultStatistics(limit, subjectName);
            for (Map<String, Object> result : subjectResults) {
                String subName = (String) result.get("subjectName");
                Long studentCount = (Long) result.get("studentCount");
                dataset.addValue(studentCount, "Số lượng sinh viên", subName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "Thống kê kết quả theo môn học",
                "Môn học",
                "Số lượng sinh viên",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        CategoryPlot plot = chart.getCategoryPlot();
        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);

        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());

        return chart;
    }

    @SuppressWarnings("unchecked")
    private void refreshKetQuaMonChart() {
        String selectedTop = (String) topComboBox.getSelectedItem();
        int limit = 0; // 0 for all
        if ("5".equals(selectedTop)) {
            limit = 5;
        } else if ("10".equals(selectedTop)) {
            limit = 10;
        }
        // Remove existing chart and add new one
        JPanel ketQuaMonPanel = (JPanel) table.getComponentAt(table.indexOfTab("Theo môn học"));
        // Remove existing chart and add new one
        ketQuaMonPanel.remove(1); // Remove old chart
        ChartPanel chartPanel = new ChartPanel(createKetQuaMonChart(limit, "Tất cả môn học"));
        ketQuaMonPanel.add(new ChartPanel(createKetQuaMonChart(0, "Tất cả môn học")), BorderLayout.CENTER);
        ketQuaMonPanel.revalidate();
        ketQuaMonPanel.repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        table = new javax.swing.JTabbedPane();
        subjectComboBox = new JComboBox<>();
        subjectLabel = new JLabel("Môn Học:");
        classComboBox = new JComboBox<>();
        classLabel = new JLabel("Lớp Học:");

        topComboBox = new JComboBox<>();
        topLabel = new JLabel("Top:");

        setPreferredSize(new java.awt.Dimension(655, 458));

        table.setPreferredSize(new java.awt.Dimension(655, 458));

        JPanel tinhTrangPanel = new JPanel(new BorderLayout());
        JPanel subjectSelectionPanel = new JPanel(new BorderLayout());
        JPanel subjectLeftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        subjectLeftPanel.add(subjectLabel);
        subjectLeftPanel.add(subjectComboBox);
        subjectLeftPanel.add(classLabel);
        subjectLeftPanel.add(classComboBox);
        subjectSelectionPanel.add(subjectLeftPanel, BorderLayout.WEST);
        JButton exportButtonHocLuc = new JButton("Export to Excel");
        exportButtonHocLuc.addActionListener(e -> exportToExcelAction());
        subjectSelectionPanel.add(exportButtonHocLuc, BorderLayout.EAST);
        tinhTrangPanel.add(subjectSelectionPanel, BorderLayout.NORTH);
        tinhTrangPanel.add(new ChartPanel(createTinhTrangChart(null, null)), BorderLayout.CENTER);

        table.add("Theo học lực", tinhTrangPanel);

        JPanel ketQuaMonPanel = new JPanel(new BorderLayout());
        JPanel topSelectionPanel = new JPanel(new BorderLayout());
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        leftPanel.add(topLabel);
        leftPanel.add(topComboBox);
        topSelectionPanel.add(leftPanel, BorderLayout.WEST);
        JButton exportChartButton = new JButton("Export to Excel");
        exportChartButton.addActionListener(e -> exportToExcelAction());
        topSelectionPanel.add(exportChartButton, BorderLayout.EAST);
        ketQuaMonPanel.add(topSelectionPanel, BorderLayout.NORTH);
        ketQuaMonPanel.add(new ChartPanel(createKetQuaMonChart(0, "Tất cả môn học")), BorderLayout.CENTER);

        table.add("Theo môn học", ketQuaMonPanel);

        // Populate topComboBox
        topComboBox.addItem("Tất cả");
        topComboBox.addItem("5");
        topComboBox.addItem("10");
        topComboBox.setSelectedItem("Tất cả"); // Default selection
        topComboBox.addActionListener(e -> refreshKetQuaMonChart());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 643, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(table, javax.swing.GroupLayout.PREFERRED_SIZE, 643, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 458, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(table, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents


    private void exportToExcelAction() {
        int selectedTabIndex = table.getSelectedIndex();
        JFreeChart chartToExport = null;
        String fileName = "ThongKe";
        byte[] chartImageBytes = null;

        if (selectedTabIndex == 0) { // "Theo học lực" tab
            String selectedSubject = (String) subjectComboBox.getSelectedItem();
            String selectedClass = (String) classComboBox.getSelectedItem();
            chartToExport = createTinhTrangChart(selectedSubject, selectedClass);
            fileName = "ThongKeTheoHocLuc";

            // Convert chart to image
            chartImageBytes = convertChartToImage(chartToExport, 800, 600);

            // Fetch data for "Theo học lực" tab
            Map<String, Integer> performanceData = StatisticsDAO.getInstance().getStudentPerformanceStatistics(selectedSubject, selectedClass);
            Vector<String> columnNames = new Vector<>();
            columnNames.add("Tình trạng");
            columnNames.add("Số lượng sinh viên");
            DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

            for (Map.Entry<String, Integer> entry : performanceData.entrySet()) {
                Vector<Object> row = new Vector<>();
                row.add(entry.getKey());
                row.add(entry.getValue());
                tableModel.addRow(row);
            }
            
            // Pass the actual table model and data to ExcelExporter
            ExcelExporter.exportToExcel(new javax.swing.JTable(tableModel),
                    ExportFileName.STATISTICS_BY_PERFORMANCE,
                    chartImageBytes, 0, 3);

        } else if (selectedTabIndex == 1) { // "Theo môn học" tab
            String selectedTop = (String) topComboBox.getSelectedItem();
            int limit = 0;
            if ("5".equals(selectedTop)) {
                limit = 5;
            } else if ("10".equals(selectedTop)) {
                limit = 10;
            }
            chartToExport = createKetQuaMonChart(limit, "Tất cả môn học");
            fileName = "ThongKeTheoMonHoc";

            // Convert chart to image
            chartImageBytes = convertChartToImage(chartToExport, 800, 600);

            // Fetch data for "Theo môn học" tab
            List<Map<String, Object>> subjectResults = StatisticsDAO.getInstance().getSubjectResultStatistics(limit, "Tất cả môn học");
            Vector<String> columnNames = new Vector<>();
            columnNames.add("Môn học");
            columnNames.add("Số lượng sinh viên");
            DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

            for (Map<String, Object> result : subjectResults) {
                Vector<Object> row = new Vector<>();
                row.add(result.get("subjectName"));
                row.add(result.get("studentCount"));
                tableModel.addRow(row);
            }

            // Pass the actual table model and data to ExcelExporter
            ExcelExporter.exportToExcel(new javax.swing.JTable(tableModel), 
                    ExportFileName.STATISTICS_BY_SUBJECT,
                    chartImageBytes, 0, 3);
        }
    }

    private byte[] convertChartToImage(JFreeChart chart, int width, int height) {
        try {
            BufferedImage chartImage = chart.createBufferedImage(width, height);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ChartUtilities.writeBufferedImageAsPNG(baos, chartImage);
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane table;
    private javax.swing.JComboBox<String> subjectComboBox;
    private javax.swing.JLabel subjectLabel;
    private javax.swing.JComboBox<String> topComboBox;
    private javax.swing.JLabel topLabel;
    private javax.swing.JComboBox<String> classComboBox;
    private javax.swing.JLabel classLabel;
    // End of variables declaration//GEN-END:variables
}
