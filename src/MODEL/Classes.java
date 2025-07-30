/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class Classes {
    private Long id;
    private String name;
    private int credit;
    private Teacher teacher;

    public Classes(Long id, String name, int credit, Teacher teacher) {
        this.id = id;
        this.name = name;
        this.credit = credit;
        this.teacher = teacher;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCredit() {
        return credit;
    }

    public Teacher getTeacher() {
        return teacher;
    }
    
    @Override
    public String toString() {
        return name; 
    }
}
