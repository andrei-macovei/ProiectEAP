package application;

public class Sala {
    private int id;
    private String nume;
    private int capacitate;
    private String user;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Sala(int id, String nume, int capacitate, String user) {
        this.id = id;
        this.nume = nume;
        this.capacitate = capacitate;
        this.user = user;
    }

    public Sala(String nume, int capacitate, String user) {
        this.nume = nume;
        this.capacitate = capacitate;
        this.user = user;
    }

    public Sala(String nume, int capacitate) {
        this.nume = nume;
        this.capacitate = capacitate;
    }

    public Sala(int id, String nume, int capacitate) {
        this.id = id;
        this.nume = nume;
        this.capacitate = capacitate;
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

    public int getCapacitate() {
        return capacitate;
    }

    public void setCapacitate(int capacitate) {
        this.capacitate = capacitate;
    }

    @Override
    public String toString() {
        return "Sala{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", capacitate=" + capacitate +
                '}';
    }
}
