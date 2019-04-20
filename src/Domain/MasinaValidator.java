package Domain;

//Implementeaza IValidator de tip Diesel ( face validari pt obiecte diesel)
public class MasinaValidator implements  IValidator<Masina> {

    public MasinaValidator() {
    }

    public void validate(Masina masina) {
        String errors = "";
        if (masina.getPret() < 0) {
            errors = errors + "The price must be > 0!\n";
        }
    }


}
