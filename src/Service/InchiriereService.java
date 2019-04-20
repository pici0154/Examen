package Service;

import Domain.Inchiriere;
import Repository.IRepository;

import java.util.List;

public class InchiriereService {
    private IRepository<Inchiriere> repository;


    public InchiriereService(IRepository<Inchiriere> repository) {
        this.repository = repository;
    }


    //face insert sau update
    public void addOrUpdate(String id, String id_masina, int nr_zile, double km_parcursi) {
        //verifica daca exista masina cu modelul dat
        Inchiriere existing = repository.findByModel (id);
        //daca exista atunci o sa faca update
        if (existing != null) {
            // keep unchanged fields as they were
            if (id_masina.isEmpty()) {
                id_masina = existing.getId_masina ();
            }
            if (nr_zile == 0 ) {
                nr_zile = existing.getNr_zile ();
            }
            if (km_parcursi == 0) {
                km_parcursi = existing.getKm_parcursi ();
            }

        }
        Inchiriere inchiriere = new Inchiriere(id, id_masina, nr_zile, km_parcursi);
        repository.upsert(inchiriere);
    }
    //sterge dupa modelul dat
    public void remove(String model) {
        repository.remove(model);
    }

    //returneaza toate masinile diesel
    public List<Inchiriere> getAll() {
        return repository.getAll();
    }

}
