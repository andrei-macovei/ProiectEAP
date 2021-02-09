package application;

public class Institutie {
    private int id;
    private String nume;
    private String username;
    private String password;

    public Institutie(String nume, String username, String password) {
        this.nume = nume;
        this.username = username;
        this.password = password;
    }

    public Institutie(int id, String nume, String username, String password) {
        this.id = id;
        this.nume = nume;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Institutie{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
