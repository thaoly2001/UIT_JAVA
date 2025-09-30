/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package GUI.admin.popup;

import Constaint.TitleConstants;
import DAO.StudentsDAO;
import MODEL.Student;
import Utils.tableFillingUtils;
import Utils.validationUtils;
import java.time.LocalDate;
import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import Utils.ImageUtil;
import com.toedter.calendar.JDateChooser;

/**
 *
 * @author ADMIN
 */
public class StudentDialog extends javax.swing.JDialog {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(StudentDialog.class.getName());
    private final Long ID;
    private byte[] personalImage;

    public StudentDialog(java.awt.Frame parent, boolean modal, Student stu) {
        super(parent, modal);
        initComponents();
        fillForm(stu);
        ID = Objects.nonNull(stu) ? stu.getId() : null;
        setTitle(TitleConstants.STUDENT_DIALOG_TITLE);
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
        idTxt = new javax.swing.JTextField();
        emailTxt = new javax.swing.JTextField();
        nameLabel2 = new javax.swing.JLabel();
        imgBtn = new javax.swing.JButton();
        phoneTxt = new javax.swing.JTextField();
        nameLabel4 = new javax.swing.JLabel();
        nameLabel5 = new javax.swing.JLabel();
        addressTxt = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        genderCbx = new javax.swing.JComboBox<>();
        nameLabel6 = new javax.swing.JLabel();
        birthdayTxt = new com.toedter.calendar.JDateChooser();
        birthdayTxt.setDateFormatString("dd-MM-yyyy");
        ((com.toedter.calendar.JTextFieldDateEditor) birthdayTxt.getDateEditor()).setEditable(false);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        nameLabel.setText("Tên:");

        nameText.setPreferredSize(new java.awt.Dimension(150, 28));
        nameText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameTextActionPerformed(evt);
            }
        });

        btnReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/iconfinder_Cancel_.png"))); // NOI18N
        btnReset.setPreferredSize(new java.awt.Dimension(100, 30));
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/save.png"))); // NOI18N
        btnAdd.setPreferredSize(new java.awt.Dimension(100, 30));
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        nameLabel1.setText("ID:");

        idTxt.setEditable(false);
        idTxt.setPreferredSize(new java.awt.Dimension(150, 28));
        idTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idTxtActionPerformed(evt);
            }
        });

        emailTxt.setPreferredSize(new java.awt.Dimension(150, 28));
        emailTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailTxtActionPerformed(evt);
            }
        });

        nameLabel2.setText("Email:");

        imgBtn.setText("Ảnh");
        imgBtn.setPreferredSize(new java.awt.Dimension(100, 204));
        imgBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imgBtnMouseClicked(evt);
            }
        });
        imgBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imgBtnActionPerformed(evt);
            }
        });

        phoneTxt.setPreferredSize(new java.awt.Dimension(150, 28));
        phoneTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phoneTxtActionPerformed(evt);
            }
        });

        nameLabel4.setText("Số điện thoại:");

        nameLabel5.setText("Địa chỉ");

        addressTxt.setPreferredSize(new java.awt.Dimension(150, 28));
        addressTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addressTxtActionPerformed(evt);
            }
        });

        jLabel1.setText("Giới Tính:");

        genderCbx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ", "Khác" }));
        genderCbx.setPreferredSize(new java.awt.Dimension(150, 28));
        genderCbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genderCbxActionPerformed(evt);
            }
        });

        nameLabel6.setText("Ngày sinh");

        birthdayTxt.setPreferredSize(new java.awt.Dimension(150, 28));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(nameLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(idTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(nameText, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(nameLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(nameLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(phoneTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(emailTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nameLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nameLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(birthdayTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addressTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(genderCbx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAdd, 94, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnReset, 94, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(imgBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nameLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(idTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(nameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(nameText, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(nameLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(emailTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(phoneTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nameLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(addressTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nameLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nameLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(birthdayTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(genderCbx, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(imgBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nameTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameTextActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        idTxt.setText("");
        nameText.setText("");
        emailTxt.setText("");
        phoneTxt.setText("");
        addressTxt.setText("");
        genderCbx.setSelectedIndex(0);
        birthdayTxt.setDate(null);
        ImageUtil.clearImageOnButton(imgBtn);
        personalImage = null;
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        String error = validationUtils.validateStudent(getData());
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

    private void idTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idTxtActionPerformed

    private void emailTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailTxtActionPerformed

    private void phoneTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phoneTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_phoneTxtActionPerformed

    private void addressTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addressTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addressTxtActionPerformed

    private void genderCbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genderCbxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_genderCbxActionPerformed

    private void birthdayTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_birthdayTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_birthdayTxtActionPerformed

    private void imgBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgBtnMouseClicked
        // Removed as actionPerformed will handle image selection
    }//GEN-LAST:event_imgBtnMouseClicked

    private void imgBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imgBtnActionPerformed
        personalImage = ImageUtil.chooseImageForButton(imgBtn);
        ImageUtil.showImageOnButtonSafe(imgBtn, personalImage);
    }//GEN-LAST:event_imgBtnActionPerformed

    private void fillForm(Student student) {
        if (student == null) {
            return;
        }
        btnAdd.setText("Sửa");
        idTxt.setText(student.getId()+"");
        nameText.setText(student.getName());
        emailTxt.setText(student.getEmail());
        phoneTxt.setText(student.getPhone());
        addressTxt.setText(student.getAddress());
        genderCbx.setSelectedItem(student.getGender());
        birthdayTxt.setDate(student.getBirthday());
        ImageUtil.showImageOnButtonSafe(imgBtn, student.getImg());
        personalImage = student.getImg();
    }

    private void create() {
        Student student = getData();
        if (StudentsDAO.getInstance().isEmailExists(student.getEmail(), null)) {
            JOptionPane.showMessageDialog(this,
                    "Email đã tồn tại cho một sinh viên khác!",
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }
        StudentsDAO.getInstance().insert(student);
        JOptionPane.showMessageDialog(this,
                "Tạo sinh viên thành công!",
                "Thông báo",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void update() {
        StudentsDAO.getInstance().update(getData());
        JOptionPane.showMessageDialog(this,
                "Cập nhật sinh viên thành công!",
                "Thông báo",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private Student getData() {
        Student student = new Student();
        student.setId(idTxt.getText().isEmpty() ? null : Long.valueOf(idTxt.getText()));
        student.setName(nameText.getText());
        student.setEmail(emailTxt.getText());
        student.setPhone(phoneTxt.getText());
        student.setAddress(addressTxt.getText());
        if (birthdayTxt.getDate() != null) {
            student.setBirthday(new java.sql.Date(birthdayTxt.getDate().getTime()));
        } else {
            student.setBirthday(null);
        }
        student.setGender(genderCbx.getSelectedItem().toString());
        student.setImg(personalImage);
        return student;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField addressTxt;
    private com.toedter.calendar.JDateChooser birthdayTxt;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnReset;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JTextField emailTxt;
    private javax.swing.JComboBox<String> genderCbx;
    private javax.swing.JTextField idTxt;
    private javax.swing.JButton imgBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JLabel nameLabel1;
    private javax.swing.JLabel nameLabel2;
    private javax.swing.JLabel nameLabel4;
    private javax.swing.JLabel nameLabel5;
    private javax.swing.JLabel nameLabel6;
    private javax.swing.JTextField nameText;
    private javax.swing.JTextField phoneTxt;
    // End of variables declaration//GEN-END:variables
}
