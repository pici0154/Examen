package Domain;

public class Inchiriere extends Auto {


    private String id_masina;
    private int nr_zile;
    private double km_parcursi;



    public Inchiriere( String id,String id_masina, int nr_zile, double km_parcursi) {
       super (id);
        this.id_masina = id_masina;
        this.nr_zile = nr_zile;
        this.km_parcursi = km_parcursi;
    }

    public String getId_masina() {
        return id_masina;
    }

    public void setId_masina(String id_masina) {
        this.id_masina = id_masina;
    }

    public int getNr_zile() {
        return nr_zile;
    }

    public void setNr_zile(int nr_zile) {
        this.nr_zile = nr_zile;
    }

    public double getKm_parcursi() {
        return km_parcursi;
    }

    public void setKm_parcursi(double km_parcursi) {
        this.km_parcursi = km_parcursi;
    }

}
