package Domain;

//interfata validator mosteneste orice obiect T de tip Masina ( T- Electrica sau Diesel)
public interface IValidator<T extends Auto> {
    void validate(T var1);
}