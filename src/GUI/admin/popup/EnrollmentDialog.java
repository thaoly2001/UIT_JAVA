/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package GUI.admin.popup;

import Constaint.ActionPaging;
import Constaint.EntityState;
import Constaint.TitleConstants;
import DAO.ClassesDAO;
import DAO.EnrollmentDAO;
import DAO.SubjectDAO;
import DAO.TeacherDAO;
import GUI.admin.popup.search.SearchStudentDialog;
import GUI.admin.popup.search.SearchStudnetEnrollmentDialog;
import GUI.admin.popup.search.SearchSubjectDialog;
import GUI.admin.popup.search.SearchTeacherDialog;
import MODEL.Classes;
import MODEL.Enrollment;
import MODEL.Student;
import Utils.CacheData;
import Utils.CachedEntity;
import Utils.tableFillingUtils;
import Utils.validationUtils;
import java.util.List;
import java.util.Objects;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class EnrollmentDialog extends javax.swing.JDialog {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(EnrollmentDialog.class.getName());
    private final JTable table;
    private CacheData<Enrollment> cache = new CacheData<>();
    private Long idClasses;
    private int currentPage = ActionPaging.defaultPage;
    private int pageSize = ActionPaging.defaultPgeSize;
    private int totalPage = ActionPaging.defaultTotalPage;

    public EnrollmentDialog(java.awt.Frame parent, boolean modal, Long idClasses, JTable teacherTable) {
        super(parent, modal);
        initComponents();
        table = teacherTable;
        this.idClasses = idClasses;
        initStudentData();
        initClassesData();
        setTitle(TitleConstants.ENROLLMENT_DIALOG_TITLE);
    }

    private void initStudentData() {
        DefaultTableModel model = (DefaultTableModel) enrollTbl.getModel();
        model.setRowCount(0);

        List<Enrollment> list = getdataTable();
        for (Enrollment e : list) {
            Student s = e.getStudent();
            model.addRow(tableFillingUtils.fillStuSearch(s));
            cache.put(e.getId(), e, EntityState.UNCHANGED);
        }
    }

    private void initClassesData() {
        Classes cl = ClassesDAO.getInstance().findById(idClasses);
        idTxt.setText(cl.getId().toString());
        nameText.setText(cl.getName());
        subjectTxt.setText(cl.getSubject().getId() + " - " + cl.getSubject().getName());
        teacherTxt.setText(cl.getTeacher().getId() + " - " + cl.getTeacher().getName());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        InfoClasses = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        firstPageBtn3 = new javax.swing.JButton();
        prevPageBtn = new javax.swing.JButton();
        nextPageBtn = new javax.swing.JButton();
        lastPageBtn = new javax.swing.JButton();
        count_assum_label = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        enrollTbl = new javax.swing.JTable();
        InfoClasses2 = new javax.swing.JPanel();
        idTxt = new javax.swing.JTextField();
        nameText = new javax.swing.JTextField();
        nameLabel = new javax.swing.JLabel();
        nameLabel3 = new javax.swing.JLabel();
        subjectTxt = new javax.swing.JTextField();
        showSubjectBtn = new javax.swing.JButton();
        showTeacherBtn = new javax.swing.JButton();
        teacherTxt = new javax.swing.JTextField();
        nameLabel7 = new javax.swing.JLabel();
        nameLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jButton4.setText("Thêm");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton7.setText("Xóa");

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/iconfinder_Cancel_.png"))); // NOI18N
        jButton9.setText("Hủy");

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/save.png"))); // NOI18N
        btnAdd.setText("Lưu");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnAdd)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        firstPageBtn3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/First.png"))); // NOI18N
        firstPageBtn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                firstPageBtn3ActionPerformed(evt);
            }
        });

        prevPageBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/back.png"))); // NOI18N
        prevPageBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prevPageBtnActionPerformed(evt);
            }
        });

        nextPageBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/next.png"))); // NOI18N
        nextPageBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextPageBtnActionPerformed(evt);
            }
        });

        lastPageBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lastPageBtnActionPerformed(evt);
            }
        });

        count_assum_label.setText("Số lượng/ Tổng");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(firstPageBtn3, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(prevPageBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nextPageBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lastPageBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                    .addContainerGap(13, Short.MAX_VALUE)
                    .addComponent(count_assum_label)
                    .addContainerGap(345, Short.MAX_VALUE)))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(firstPageBtn3, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
            .addComponent(nextPageBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(prevPageBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lastPageBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                    .addContainerGap(19, Short.MAX_VALUE)
                    .addComponent(count_assum_label)
                    .addContainerGap(20, Short.MAX_VALUE)))
        );

        enrollTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Tên", "Email"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(enrollTbl);

        javax.swing.GroupLayout InfoClassesLayout = new javax.swing.GroupLayout(InfoClasses);
        InfoClasses.setLayout(InfoClassesLayout);
        InfoClassesLayout.setHorizontalGroup(
            InfoClassesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InfoClassesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(InfoClassesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        InfoClassesLayout.setVerticalGroup(
            InfoClassesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InfoClassesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, InfoClassesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        idTxt.setEditable(false);
        idTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idTxtActionPerformed(evt);
            }
        });

        nameText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameTextActionPerformed(evt);
            }
        });

        nameLabel.setText("Tên:");

        nameLabel3.setText("Môn học:");

        subjectTxt.setEditable(false);
        subjectTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subjectTxtActionPerformed(evt);
            }
        });

        showSubjectBtn.setText("+");
        showSubjectBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showSubjectBtnActionPerformed(evt);
            }
        });

        showTeacherBtn.setText("+");
        showTeacherBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showTeacherBtnActionPerformed(evt);
            }
        });

        teacherTxt.setEditable(false);
        teacherTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                teacherTxtActionPerformed(evt);
            }
        });

        nameLabel7.setText("Giảng Viên:");

        nameLabel1.setText("ID:");

        javax.swing.GroupLayout InfoClasses2Layout = new javax.swing.GroupLayout(InfoClasses2);
        InfoClasses2.setLayout(InfoClasses2Layout);
        InfoClasses2Layout.setHorizontalGroup(
            InfoClasses2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InfoClasses2Layout.createSequentialGroup()
                .addGroup(InfoClasses2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, InfoClasses2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(nameLabel7)
                        .addGap(30, 30, 30))
                    .addGroup(InfoClasses2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(InfoClasses2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nameLabel3)
                            .addComponent(nameLabel)
                            .addComponent(nameLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(InfoClasses2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(InfoClasses2Layout.createSequentialGroup()
                        .addGroup(InfoClasses2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(subjectTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                            .addComponent(teacherTxt))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(InfoClasses2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(showSubjectBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(showTeacherBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(nameText)
                    .addComponent(idTxt))
                .addGap(0, 11, Short.MAX_VALUE))
        );
        InfoClasses2Layout.setVerticalGroup(
            InfoClasses2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InfoClasses2Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(InfoClasses2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(idTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(InfoClasses2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(InfoClasses2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nameLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(InfoClasses2Layout.createSequentialGroup()
                        .addComponent(nameText, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(InfoClasses2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(showSubjectBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(subjectTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(InfoClasses2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(InfoClasses2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(teacherTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                        .addComponent(nameLabel7))
                    .addComponent(showTeacherBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(InfoClasses2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(InfoClasses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(InfoClasses, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(InfoClasses2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void firstPageBtn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_firstPageBtn3ActionPerformed
//        this.first();
    }//GEN-LAST:event_firstPageBtn3ActionPerformed

    private void prevPageBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prevPageBtnActionPerformed
//        this.prev();
    }//GEN-LAST:event_prevPageBtnActionPerformed

    private void nextPageBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextPageBtnActionPerformed
//        this.next();
    }//GEN-LAST:event_nextPageBtnActionPerformed

    private void lastPageBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lastPageBtnActionPerformed
//        this.last();
    }//GEN-LAST:event_lastPageBtnActionPerformed

    private void idTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idTxtActionPerformed

    private void nameTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameTextActionPerformed

    private void subjectTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subjectTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_subjectTxtActionPerformed
    private List<Enrollment> getdataTable() {
        List<Enrollment> list = EnrollmentDAO.getInstance().getByClassId(idClasses, currentPage, pageSize);
        for (Enrollment e : list) {
            CachedEntity<Enrollment> ce = cache.get(e.getId());
            if (ce == null) {
                cache.put(e.getId(), e, EntityState.UNCHANGED);
            } else {
                ce.setEntity(e);
            }
        }
        return list;
    }
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

    private void teacherTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_teacherTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_teacherTxtActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed

        saveClasses();
        saveStudent();
    }//GEN-LAST:event_btnAddActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        Classes cl = ClassesDAO.getInstance().findById(idClasses);
        SearchStudnetEnrollmentDialog dialog = new SearchStudnetEnrollmentDialog((java.awt.Frame) javax.swing.SwingUtilities.getWindowAncestor(this), true, cl, cache);
        dialog.setLocationRelativeTo(this);
        dialog.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                filltable();
            }
        });

        dialog.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed
   private void filltable (){
       List<CachedEntity<Enrollment>>  list = cache.getAll();
       DefaultTableModel model = (DefaultTableModel) enrollTbl.getModel();
        model.setRowCount(0);

        for (CachedEntity<Enrollment> e : list) {
            model.addRow(tableFillingUtils.fillStuSearch(e.getEntity().getStudent()));
        }
   }
    private void saveClasses() {
        String error = validationUtils.validateClasses(getData());
        if (error == null) {
            ClassesDAO.getInstance().update(Long.valueOf(idTxt.getText()), getData());
            JOptionPane.showMessageDialog(this,
                    "Cập nhật lớp học thành công!",
                    "Thông báo",
                    JOptionPane.INFORMATION_MESSAGE
            );
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this,
                    error,
                    "Thiếu thông tin",
                    JOptionPane.WARNING_MESSAGE
            );
        }
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

    private void saveStudent() {
        for (CachedEntity<Enrollment> ce : cache.getAll()) {
            switch (ce.getState()) {
                case NEW:
                    EnrollmentDAO.getInstance().insert(ce.getEntity());
                    break;
                case DELETED:
                    EnrollmentDAO.getInstance().update(ce.getEntity());
                    break;
                case UNCHANGED:
                    // bỏ qua
                    break;
            }
        }

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel InfoClasses;
    private javax.swing.JPanel InfoClasses2;
    private javax.swing.JButton btnAdd;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JLabel count_assum_label;
    private javax.swing.JTable enrollTbl;
    private javax.swing.JButton firstPageBtn3;
    private javax.swing.JTextField idTxt;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton lastPageBtn;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JLabel nameLabel1;
    private javax.swing.JLabel nameLabel3;
    private javax.swing.JLabel nameLabel7;
    private javax.swing.JTextField nameText;
    private javax.swing.JButton nextPageBtn;
    private javax.swing.JButton prevPageBtn;
    private javax.swing.JButton showSubjectBtn;
    private javax.swing.JButton showTeacherBtn;
    private javax.swing.JTextField subjectTxt;
    private javax.swing.JTextField teacherTxt;
    // End of variables declaration//GEN-END:variables
}
