import DAO.MemberDAO;
import DAO.TitleDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Library{
private List<Title> titles=new ArrayList<>();
private List<Member> members=new ArrayList<>();
private List<Borrowable> borrowables=new ArrayList<>();
private Scanner myScanObj=new Scanner(System.in);

    public Library(){
//    MemberDAO memberDAO=new MemberDAO();
//    TitleDAO titleDAO=new TitleDAO();


//        Title aBook=new Book();
//        System.out.println("Hello");
//        System.out.println("How I may serve you?");
//        int choice;
//        do{
//            choice=myScanObj.nextInt();
//            myScanObj.nextLine();
//            System.out.println("1.I would like to borrow a book");
//            System.out.println("2.I would like to return a book");
//            System.out.println("3.I would like to sign up as a member");
//            System.out.println("4.I would like to stop being a member");
//            System.out.println("5.Was just passing by. GoodBye");
//        }while(choice!=5);
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
