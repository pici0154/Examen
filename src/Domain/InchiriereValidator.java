package Domain;

public class InchiriereValidator implements  IValidator<Inchiriere> {

    public InchiriereValidator(){}

    @Override
    public void validate(Inchiriere var1) {
        String errors = "";
        if (var1.getKm_parcursi () < 0) {
            errors = errors + "The autonomie must be > 0!\n";
        }

        if (var1.getNr_zile () < 0) {
            errors = errors + "The price must be > 0!\n";
        }
    }
}
