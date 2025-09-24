public class Books {
    private int id;
    private String name;
    private String author;
    private int page;
    private int stock;
    public Books(int id,String name,String author,int page,int stock){
        this.id=id;
        this.name=name;
        this.author=author;
        this.page=page;
        this.stock=stock;
    }

    public int getId() {return id;}

    public String getName() {return name;}

    public String getAuthor() {return author;}

    public int getPage() {return page;}

    public int getStock() {return stock;}
}
