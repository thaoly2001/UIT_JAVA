package MODEL;

public class Subject {

    private Long id;
    private String name;
    private int credit;
    private boolean status;
    private boolean isdeleted;

    public Subject() {
    }

    public Subject(Long id, String name, int credit, boolean isdeleted) {
        this.id = id;
        this.name = name;
        this.credit = credit;
        this.status = isdeleted;
    }

    public boolean isIsdeleted() {
        return isdeleted;
    }

    public void setIsdeleted(boolean isdeleted) {
        this.isdeleted = isdeleted;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
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

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCredit() {
        return credit;
    }

    @Override
    public String toString() {
        return name;
    }
}
