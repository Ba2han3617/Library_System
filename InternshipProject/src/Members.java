public class Members {
    private int id;
    private String name;
    private String surname;
    private String email;
    private String username;
    private String password;
    public Members(int id,String name,String surname,String email,String username,String password){
        this.id=id;
        this.name=name;
        this.surname=surname;
        this.email=email;
        this.username=username;
        this.password=password;

    }

    public int getId() {return id;}

    public String getName() {return name;}

    public String getSurname() {return surname;}

    public String getEmail() {return email;}

    public String getUsername() {return username;}

    public String getPassword() {return password;}
}
