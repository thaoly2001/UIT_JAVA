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

    private ChartPanel createTinhTrangChart() {
        return createTinhTrangChart(null, null);
    }

    private ChartPanel createTinhTrangChart(String subjectName, String className) {
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

        return new ChartPanel(chart);
    }

    private void refreshTinhTrangChart() {
        String selectedSubject = (String) subjectComboBox.getSelectedItem();
        String selectedClass = (String) classComboBox.getSelectedItem();
        JPanel tinhTrangPanel = (JPanel) table.getComponentAt(table.indexOfTab("Theo tình trạng"));
        tinhTrangPanel.remove(1); // Remove old chart
        tinhTrangPanel.add(createTinhTrangChart(selectedSubject, selectedClass), BorderLayout.CENTER); // Add new chart
        tinhTrangPanel.revalidate();
        tinhTrangPanel.repaint();
    }

    private ChartPanel createKetQuaMonChart(int limit, String subjectName) {
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

        return new ChartPanel(chart);
    }

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
        ketQuaMonPanel.remove(1); // Remove old chart
        ketQuaMonPanel.add(createKetQuaMonChart(limit, "Tất cả môn học"), BorderLayout.CENTER);
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
        JPanel subjectSelectionPanel = new JPanel();
        subjectSelectionPanel.add(subjectLabel);
        subjectSelectionPanel.add(subjectComboBox);
        subjectSelectionPanel.add(classLabel);
        subjectSelectionPanel.add(classComboBox);
        tinhTrangPanel.add(subjectSelectionPanel, BorderLayout.NORTH);
        tinhTrangPanel.add(createTinhTrangChart(null, null), BorderLayout.CENTER);

        table.add("Theo tình trạng", tinhTrangPanel);

        JPanel ketQuaMonPanel = new JPanel(new BorderLayout());
        JPanel topSelectionPanel = new JPanel();
        
        topSelectionPanel.add(topLabel);
        topSelectionPanel.add(topComboBox);
        ketQuaMonPanel.add(topSelectionPanel, BorderLayout.NORTH);
        ketQuaMonPanel.add(createKetQuaMonChart(0, "Tất cả môn học"), BorderLayout.CENTER);

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
