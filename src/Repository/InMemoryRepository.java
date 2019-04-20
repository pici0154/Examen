package Repository;

import Domain.IValidator;
import Domain.Auto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryRepository <T extends Auto> implements  IRepository<T>{
    private Map<String, T> storage = new HashMap<> ();
    private IValidator<T> validator;

    public InMemoryRepository(IValidator<T> validator) {
        this.validator = validator;
    }


   @Override
    public T findByModel(String id) {
        return storage.get(id);
    }

    @Override
    public void upsert(T auto) {
        //validam obiectul masina
        validator.validate(auto);
        storage.put(auto.getId (), auto);
    }

    @Override
    public void remove(String model) {
        //verifica daca exista masina cu modelul dat, daca nu eroare ca nu poate sterge
        if (!storage.containsKey(model)) {
            throw new RuntimeException("There is no entity with the given model to remove.");
        }

        storage.remove(model);
    }

    @Override
    public List<T> getAll() {
        //returneaza toata lista de masini
        return new ArrayList<> (storage.values());
    }
}
