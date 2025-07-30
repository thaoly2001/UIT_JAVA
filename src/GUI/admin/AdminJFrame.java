/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI.admin;

import java.awt.CardLayout;


public class AdminJFrame extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(AdminJFrame.class.getName());


    public AdminJFrame() {
        initComponents();
        setSize(800, 600);              
        setLocationRelativeTo(null); 
        mainPanel.add(new StudentPanel(), "student");
        mainPanel.add(new TeacherPanel(), "teacher");
        mainPanel.add(new SubjectsPanel(), "subject"); 
        mainPanel.add(new ClassesPanel(), "classes");

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuPanel = new javax.swing.JPanel();
        btnMenuDep = new javax.swing.JButton();
        btnMenuEmp = new javax.swing.JButton();
        btnMenuClass = new javax.swing.JButton();
        btnMenuLogin = new javax.swing.JButton();
        btnMenuEnrollment = new javax.swing.JButton();
        mainPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        menuPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnMenuDep.setText("Quản Lý Giảng Viên");
        btnMenuDep.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnMenuDep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuDepActionPerformed(evt);
            }
        });

        btnMenuEmp.setText("Quản Lý Học Sinh");
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

        btnMenuLogin.setText("Quản Lý Đăng nhập");
        btnMenuLogin.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnMenuLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuLoginActionPerformed(evt);
            }
        });

        btnMenuEnrollment.setText("Quản lý Môn học");
        btnMenuEnrollment.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnMenuEnrollment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuEnrollmentActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout menuPanelLayout = new javax.swing.GroupLayout(menuPanel);
        menuPanel.setLayout(menuPanelLayout);
        menuPanelLayout.setHorizontalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnMenuDep, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnMenuEmp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnMenuClass, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnMenuLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnMenuEnrollment, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        menuPanelLayout.setVerticalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuPanelLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(btnMenuEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnMenuDep, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnMenuClass, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnMenuEnrollment, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnMenuLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58))
        );

        mainPanel.setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(menuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(menuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMenuEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuEmpActionPerformed
        CardLayout cl = (CardLayout) mainPanel.getLayout();
        cl.show(mainPanel, "student");
    }//GEN-LAST:event_btnMenuEmpActionPerformed

    private void btnMenuDepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuDepActionPerformed
        CardLayout cl = (CardLayout) mainPanel.getLayout();
        cl.show(mainPanel, "teacher");
    }//GEN-LAST:event_btnMenuDepActionPerformed

    private void btnMenuClassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuClassActionPerformed
        CardLayout cl = (CardLayout) mainPanel.getLayout();
        cl.show(mainPanel, "classes");
    }//GEN-LAST:event_btnMenuClassActionPerformed

    private void btnMenuLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuLoginActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMenuLoginActionPerformed

    private void btnMenuEnrollmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuEnrollmentActionPerformed
        CardLayout cl = (CardLayout) mainPanel.getLayout();
        cl.show(mainPanel, "subject");
    }//GEN-LAST:event_btnMenuEnrollmentActionPerformed

   
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new AdminJFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMenuClass;
    private javax.swing.JButton btnMenuDep;
    private javax.swing.JButton btnMenuEmp;
    private javax.swing.JButton btnMenuEnrollment;
    private javax.swing.JButton btnMenuLogin;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel menuPanel;
    // End of variables declaration//GEN-END:variables
}
