public class Journal extends Title{
    private String editor;

    public Journal(int id , String title, String editor){
        super(id,title);
        this.editor=editor;
    }

    public String getEditor(){
        return editor;
    }

    public void setEditor(String editor){
        this.editor=editor;
    }

    public String toString(){
        return "editor: " + editor
              + super.toString();
    }
}
