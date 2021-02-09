package application;

public class Eveniment {
    private int id;
    private String denumire;
    private String timp_inceput;
    private String timp_final;
    private int sala;
    private String numeSala;

    public String getNumeSala() {
        return numeSala;
    }

    public Eveniment(String timp_inceput, String timp_final) {
        this.timp_inceput = timp_inceput;
        this.timp_final = timp_final;
    }

    public Eveniment() {
    }

    public Eveniment(int id, String denumire, String timp_inceput, String timp_final, int sala) {
        this.id = id;
        this.denumire = denumire;
        this.timp_inceput = timp_inceput;
        this.timp_final = timp_final;
        this.sala = sala;
    }

    public Eveniment(int id, String denumire, String timp_inceput, String timp_final, int sala, String numeSala) {
        this.id = id;
        this.denumire = denumire;
        this.timp_inceput = timp_inceput;
        this.timp_final = timp_final;
        this.sala = sala;
        this.numeSala = numeSala;
    }

    public Eveniment(String denumire, String timp_inceput, String timp_final, int sala) {
        this.denumire = denumire;
        this.timp_inceput = timp_inceput;
        this.timp_final = timp_final;
        this.sala = sala;
    }

    public Eveniment(String denumire, int sala) {
        this.denumire = denumire;
        this.sala = sala;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public String getTimp_inceput() {
        return timp_inceput;
    }

    public void setTimp_inceput(String timp_inceput) {
        this.timp_inceput = timp_inceput;
    }

    public String getTimp_final() {
        return timp_final;
    }

    public void setTimp_final(String timp_final) {
        this.timp_final = timp_final;
    }

    public int getSala() {
        return sala;
    }

    public void setSala(int sala) {
        this.sala = sala;
    }

    @Override
    public String toString() {
        return "Eveniment{" +
                "id=" + id +
                ", denumire='" + denumire + '\'' +
                ", timp_inceput='" + timp_inceput + '\'' +
                ", timp_final='" + timp_final + '\'' +
                ", sala=" + sala +
                '}';
    }
}
