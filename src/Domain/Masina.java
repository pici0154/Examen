package Domain;


public class Masina extends Auto {

    private String model;
    private double km_achizitie;
    private double pret;


    public Masina(String id, String model, double km_achizitie, double pret) {
        super ( id );
        model = model;
        this.km_achizitie = km_achizitie;
        this.pret = pret;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        model = model;
    }

    public double getKm_achizitie() {
        return km_achizitie;
    }

    public void setKm_achizitie(double km_achizitie) {
        this.km_achizitie = km_achizitie;
    }

    public double getPret() {
        return pret;
    }

    public void setPret(double pret) {
        this.pret = pret;
    }


}
