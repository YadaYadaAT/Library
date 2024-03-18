import java.util.ArrayList;

public interface BookCopyDAO {
    BookCopy findById(int id);
    void save(BookCopy bookCopy);
    void update(BookCopy bookCopy);
    void delete(BookCopy bookCopy);
    public ArrayList<BookCopy> findAllBookCopies();
    public ArrayList<Integer> findAvailableCopiesId();
    public ArrayList<Integer> findBorrowedCopiesId();
}
