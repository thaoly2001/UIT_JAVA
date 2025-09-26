/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import GUI.admin.ClassesPanel;
import GUI.admin.StatisticsPanel;
import GUI.admin.StudentPanel;
import GUI.admin.SubjectsPanel;
import GUI.admin.TeacherPanel;
import java.awt.CardLayout;
import java.awt.Component;

public class AdminJFrame extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(AdminJFrame.class.getName());

    public AdminJFrame() {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        mainPanel.add(new StatisticsPanel(), "statistics");
        mainPanel.add(new StudentPanel(), "student");
        mainPanel.add(new TeacherPanel(), "teacher");
        mainPanel.add(new SubjectsPanel(), "subject");
        mainPanel.add(new ClassesPanel(), "classes");

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuPanel1 = new javax.swing.JPanel();
        btnMenuDep = new javax.swing.JButton();
        btnMenuEmp = new javax.swing.JButton();
        btnMenuClass = new javax.swing.JButton();
        btnMenuEnrollment = new javax.swing.JButton();
        btnMenuEmp1 = new javax.swing.JButton();
        mainPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        menuPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnMenuDep.setText("Quản Lý Giảng Viên");
        btnMenuDep.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnMenuDep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuDepActionPerformed(evt);
            }
        });

        btnMenuEmp.setText("Thống Kê");
        btnMenuEmp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnMenuEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuEmpActionPerformed(evt);
            }
        });

        btnMenuClass.setText("Quản Lý Lớp Học");
        btnMenuClass.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnMenuClass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuClassActionPerformed(evt);
            }
        });

        btnMenuEnrollment.setText("Quản lý Môn học");
        btnMenuEnrollment.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnMenuEnrollment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuEnrollmentActionPerformed(evt);
            }
        });

        btnMenuEmp1.setText("Quản Lý Học Sinh");
        btnMenuEmp1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnMenuEmp1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuEmp1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout menuPanel1Layout = new javax.swing.GroupLayout(menuPanel1);
        menuPanel1.setLayout(menuPanel1Layout);
        menuPanel1Layout.setHorizontalGroup(
            menuPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(menuPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnMenuDep, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                    .addComponent(btnMenuEmp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnMenuClass, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnMenuEnrollment, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnMenuEmp1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        menuPanel1Layout.setVerticalGroup(
            menuPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnMenuEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnMenuEmp1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnMenuDep, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnMenuClass, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnMenuEnrollment, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(217, 217, 217))
        );

        mainPanel.setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(menuPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 643, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(menuPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 451, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMenuEmp1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuEmp1ActionPerformed
        CardLayout cl = (CardLayout) mainPanel.getLayout();
        cl.show(mainPanel, "student");
    }//GEN-LAST:event_btnMenuEmp1ActionPerformed

    private void btnMenuEnrollmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuEnrollmentActionPerformed
        CardLayout cl = (CardLayout) mainPanel.getLayout();
        cl.show(mainPanel, "subject");
    }//GEN-LAST:event_btnMenuEnrollmentActionPerformed

    private void btnMenuClassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuClassActionPerformed
        CardLayout cl = (CardLayout) mainPanel.getLayout();
        cl.show(mainPanel, "classes");
    }//GEN-LAST:event_btnMenuClassActionPerformed

    private void btnMenuEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuEmpActionPerformed
        for (Component comp : mainPanel.getComponents()) {
            if (comp instanceof StatisticsPanel) {
                mainPanel.remove(comp);
                break;
            }
        }

        StatisticsPanel newPanel = new StatisticsPanel();
        mainPanel.add(newPanel, "statistics");

        CardLayout cl = (CardLayout) mainPanel.getLayout();
        cl.show(mainPanel, "statistics");

        mainPanel.revalidate();
        mainPanel.repaint();
    }//GEN-LAST:event_btnMenuEmpActionPerformed

    private void btnMenuDepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuDepActionPerformed
        CardLayout cl = (CardLayout) mainPanel.getLayout();
        cl.show(mainPanel, "teacher");
    }//GEN-LAST:event_btnMenuDepActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new AdminJFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMenuClass;
    private javax.swing.JButton btnMenuDep;
    private javax.swing.JButton btnMenuEmp;
    private javax.swing.JButton btnMenuEmp1;
    private javax.swing.JButton btnMenuEnrollment;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel menuPanel1;
    // End of variables declaration//GEN-END:variables
}
