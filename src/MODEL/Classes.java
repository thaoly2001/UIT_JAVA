package MODEL;

public class Classes {
    private Long id;
    private String name;
    private Subject subject;
    private Teacher teacher;
    private boolean isDeleted;

    public Classes() {
    }

    public Classes(Long id, String name, Subject subject, Teacher teacher, boolean isDeleted) {
        this.id = id;
        this.name = name;
        this.subject = subject;
        this.teacher = teacher;
        this.isDeleted = isDeleted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public String toString() {
        return name;
    }
}
