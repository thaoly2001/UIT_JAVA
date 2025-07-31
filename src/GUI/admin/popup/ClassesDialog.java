/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package GUI.admin.popup;

import DAO.ClassesDAO;
import DAO.SubjectDAO;
import DAO.TeacherDAO;
import GUI.admin.popup.search.SearchSubjectDialog;
import GUI.admin.popup.search.SearchTeacherDialog;
import MODEL.Classes;
import MODEL.Student;
import Utils.validationUtils;
import java.util.Objects;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class ClassesDialog extends javax.swing.JDialog {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ClassesDialog.class.getName());
    private final JTable table;
    private final Long ID;

    public ClassesDialog(java.awt.Frame parent, boolean modal, Student stu, JTable t) {
        super(parent, modal);
        initComponents();
        table = t;
        fillForm(stu);
        ID = Objects.nonNull(stu) ? stu.getId() : null;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        nameLabel = new javax.swing.JLabel();
        nameText = new javax.swing.JTextField();
        btnReset = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        nameLabel1 = new javax.swing.JLabel();
        teacherTxt = new javax.swing.JTextField();
        nameLabel3 = new javax.swing.JLabel();
        nameLabel7 = new javax.swing.JLabel();
        showTeacherBtn = new javax.swing.JButton();
        showSubjectBtn = new javax.swing.JButton();
        idTxt1 = new javax.swing.JTextField();
        subjectTxt = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        nameLabel.setText("Tên:");

        nameText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameTextActionPerformed(evt);
            }
        });

        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        btnAdd.setText("Thêm mới");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        nameLabel1.setText("ID:");

        teacherTxt.setEditable(false);
        teacherTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                teacherTxtActionPerformed(evt);
            }
        });

        nameLabel3.setText("Môn học:");

        nameLabel7.setText("Giảng Viên:");

        showTeacherBtn.setText("+");
        showTeacherBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showTeacherBtnActionPerformed(evt);
            }
        });

        showSubjectBtn.setText("+");
        showSubjectBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showSubjectBtnActionPerformed(evt);
            }
        });

        idTxt1.setEditable(false);
        idTxt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idTxt1ActionPerformed(evt);
            }
        });

        subjectTxt.setEditable(false);
        subjectTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subjectTxtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nameText))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(nameLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(idTxt1, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(nameLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(1, 1, 1)
                                    .addComponent(teacherTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(showTeacherBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(btnAdd)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btnReset)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(nameLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(subjectTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(showSubjectBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(0, 33, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(idTxt1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(nameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nameText, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameLabel3)
                    .addComponent(showSubjectBtn)
                    .addComponent(subjectTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(nameLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(showTeacherBtn)
                        .addComponent(teacherTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nameTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameTextActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        String error = validationUtils.validateClasses(getData());
        if (error == null) {
            if (Objects.isNull(ID)) {
                this.create();
            } else {
                this.update();
            }
            this.dispose();
        } else
            JOptionPane.showMessageDialog(this,
                    error,
                    "Thiếu thông tin",
                    JOptionPane.WARNING_MESSAGE
            );
    }//GEN-LAST:event_btnAddActionPerformed

    private void teacherTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_teacherTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_teacherTxtActionPerformed

    private void showSubjectBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showSubjectBtnActionPerformed
        SearchSubjectDialog dialog = new SearchSubjectDialog((java.awt.Frame) javax.swing.SwingUtilities.getWindowAncestor(this), true, subjectTxt);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }//GEN-LAST:event_showSubjectBtnActionPerformed

    private void showTeacherBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showTeacherBtnActionPerformed
        SearchTeacherDialog dialog = new SearchTeacherDialog((java.awt.Frame) javax.swing.SwingUtilities.getWindowAncestor(this), true, teacherTxt);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }//GEN-LAST:event_showTeacherBtnActionPerformed

    private void idTxt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idTxt1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idTxt1ActionPerformed

    private void subjectTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subjectTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_subjectTxtActionPerformed

    private void fillForm(Student student) {
        if (student == null) {
            return;
        }
        btnAdd.setText("Sửa");
        teacherTxt.setText(student.getId().toString());
        nameText.setText(student.getName());

    }

    private void create() {
        Classes cl = getData();
        ClassesDAO.getInstance().insert(cl);
        JOptionPane.showMessageDialog(this,
                "Tạo lớp học thành công!",
                "Thông báo",
                JOptionPane.INFORMATION_MESSAGE
        );
        DefaultTableModel model = (DefaultTableModel) table.getModel();
    }

    private void update() {
        ClassesDAO.getInstance().update(ID, getData());
        JOptionPane.showMessageDialog(this,
                "Cập nhật lớp học thành công!",
                "Thông báo",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private Classes getData() {
        Classes cl = new Classes();
        cl.setName(nameText.getText());
        cl.setSubject(SubjectDAO.getInstance().findById(getId(subjectTxt.getText())));
        cl.setTeacher(TeacherDAO.getInstance().findById(getId(teacherTxt.getText())));
        return cl;
    }

    private Long getId(String input) {
        return Long.valueOf(input.split(" - ")[0]);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnReset;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JTextField idTxt1;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JLabel nameLabel1;
    private javax.swing.JLabel nameLabel3;
    private javax.swing.JLabel nameLabel7;
    private javax.swing.JTextField nameText;
    private javax.swing.JButton showSubjectBtn;
    private javax.swing.JButton showTeacherBtn;
    private javax.swing.JTextField subjectTxt;
    private javax.swing.JTextField teacherTxt;
    // End of variables declaration//GEN-END:variables
}
