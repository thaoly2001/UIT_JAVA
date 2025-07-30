/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import MODEL.Student;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author DELL
 */
public class StudentsDAO extends KetNoiCSDL {
     private static StudentsDAO instance;

    public static StudentsDAO getInstance() {
        if (instance == null) {
            instance = new StudentsDAO();
        }
        return instance;
    }
    public Student findById(String MaSV) {
       
        return new Student();
    }
    
    public List<Student> getAll() {
      
        return new ArrayList<>();
    }

    public boolean insert(Student model) {
        return false;
    }

    public boolean delete(String maSV) {
       
        return false;

    }
    
    public boolean update(Student model){
        return false;
    }

//    public static void main(String[] args) {
//        StudentsDAO stDAO = new StudentsDAO();
//        Students stu = stDAO.findById("PS01");
//        System.out.println(stu.getHoTen());
//    }
    
}


