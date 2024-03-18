import java.util.ArrayList;

public interface MemberDAO {
    public Member findById(int id);
    public void save(Member member);
    public void update(Member member);
    public void delete(Member member);
    public ArrayList<Member> findAllMembers();
}