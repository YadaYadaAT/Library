public class BookCopy extends Book implements Borrowable{
    private int id;
    private boolean isAvailable;


    public BookCopy(int titleId ,String title, String author,int id, boolean isAvailable){
        super(titleId,title,author);
        this.id=id;
        this.isAvailable=true;
    }

    public BookCopy(int id, boolean available){
        this.id = id;
        this.isAvailable = true;
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



    @Override
    public String toString() {
        return "BookCopy{" +
                "id=" + id +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
