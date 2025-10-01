package MODEL;

public class Subject {

    private Long id;
    private String name;
    private int credit;

    public Subject() {
    }

    public Subject(Long id, String name, int credit) {
        this.id = id;
        this.name = name;
        this.credit = credit;
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
