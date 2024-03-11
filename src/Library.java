import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Library{
private List<Title> titles=new ArrayList<>();
private List<Member> members=new ArrayList<>();
private List<Borrowable> borrowables=new ArrayList<>();

    public Library(){

    }

    public void addTitle(Title title){
        titles.add(title);
    }

    public void addMember(int id, String name, int Max_ON_LOAN){
        members.add(new Member(5,name,Max_ON_LOAN));
    }

    public void addBorrowable(Borrowable borrowable){
        borrowables.add(borrowable);
    }

    public void displayAllMembers(){
        System.out.println(members);
    }

    public void displayAllBorrowables(){
        System.out.println(borrowables);
    }

    public void displayBorrowedItems(){
        for (Borrowable borrowable : borrowables) {
            if (!borrowable.isAvailable()) {
                System.out.print(borrowable);
            }
        }
    }

}
