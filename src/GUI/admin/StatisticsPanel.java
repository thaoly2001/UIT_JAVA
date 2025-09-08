package GUI.admin;

import DAO.StatisticsDAO;
import java.util.Map;
import org.jfree.chart.*;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

public class StatisticsPanel extends javax.swing.JPanel {

    public StatisticsPanel() {
        initComponents();
        loadCharts();
    }

    public void loadCharts() {
        table.add("Theo tình trạng", createTinhTrangChart());

        table.add("Theo môn học", createKetQuaMonChart());
    }

    private ChartPanel createTinhTrangChart() {
        DefaultPieDataset dataset = new DefaultPieDataset();

        Map<String, Integer> data = StatisticsDAO.getInstance().getStudentPerformanceStatistics();
        for (Map.Entry<String, Integer> entry : data.entrySet()) {
            dataset.setValue(entry.getKey(), entry.getValue());
        }

        JFreeChart chart = ChartFactory.createPieChart(
                "Thống kê tình trạng học tập",
                dataset,
                true, true, false
        );

        return new ChartPanel(chart);
    }

    private ChartPanel createKetQuaMonChart() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        Map<String, Map<String, Integer>> data = StatisticsDAO.getInstance().getSubjectResultStatistics();
        for (Map.Entry<String, Map<String, Integer>> subject : data.entrySet()) {
            String subjectName = subject.getKey();
            Map<String, Integer> stats = subject.getValue();

            dataset.addValue(stats.getOrDefault("Qua môn", 0), "Qua môn", subjectName);
            dataset.addValue(stats.getOrDefault("Rớt môn", 0), "Rớt môn", subjectName);
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "Báo cáo kết quả học tập theo môn",
                "Môn học",
                "Số lượng sinh viên",
                dataset
        );

        CategoryPlot plot = chart.getCategoryPlot();
        CategoryAxis xAxis = plot.getDomainAxis();
        xAxis.setCategoryLabelPositions(
                CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 4) // xoay 45 độ
        );

        xAxis.setTickLabelFont(new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 11));

        return new ChartPanel(chart);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        table = new javax.swing.JTabbedPane();

        setPreferredSize(new java.awt.Dimension(655, 458));

        table.setPreferredSize(new java.awt.Dimension(655, 458));

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
    // End of variables declaration//GEN-END:variables
}
