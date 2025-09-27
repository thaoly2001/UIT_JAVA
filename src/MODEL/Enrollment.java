package MODEL;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
 

public class Enrollment {
    private Long id;
    private Student student;
    private Classes classes;
    private double score;
    private LocalDate enrollmentDate;

    public Enrollment() {
    }

    public Enrollment(Long id, Student student, Classes classes, Double score, LocalDate enrollmentDate) {
        this.id = id;
        this.student = student;
        this.classes = classes;
        this.score = score;
        this.enrollmentDate = enrollmentDate;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Classes getClasses() {
        return classes;
    }

    public void setClasses(Classes classes) {
        this.classes = classes;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    
}
