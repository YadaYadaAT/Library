public class Member {
    private int id;
    private String name;
    private int MAX_ON_LOAN;

    public Member(int id, String name, int MAX_ON_LOAN) {
        this.id = id;
        this.name = name;
        this.MAX_ON_LOAN = MAX_ON_LOAN;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMAX_ON_LOAN() {
        return MAX_ON_LOAN;
    }

    public void setMAX_ON_LOAN(int MAX_ON_LOAN) {
        this.MAX_ON_LOAN = MAX_ON_LOAN;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", MAX_ON_LOAN=" + MAX_ON_LOAN +
                '}';
    }
}
