public class BookCopy implements Borrowable{
    private int id;
    private boolean isAvailable;
    private Book book;

    public BookCopy(int id,Book book){
        this.id=id;
        this.isAvailable=true;
        this.book=book;
    }

    public void borrowItem(){

    }

    public void returnItem(){

    }

    public int getId() {
        return id;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setId(int id) {
        this.id = id;
    }



    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "BookCopy{" +
                "id=" + id +
                ", isAvailable=" + isAvailable +
                ", book=" + book +
                '}';
    }
}
