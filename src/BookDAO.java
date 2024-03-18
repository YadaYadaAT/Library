import java.util.ArrayList;

public interface BookDAO {
    Book findById(int id);

    void save(Book book);

    void update(Book book);

    void delete(Book book);
    public ArrayList<Book> findAllBooks();
}