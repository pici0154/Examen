package Service;

import Domain.Masina;
import Repository.IRepository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class MasinaService {
    private IRepository<Masina> repository;

    public MasinaService(IRepository<Masina> repository) {
        this.repository = repository;
    }

    //face insert sau update
    public void addOrUpdate(String id, String model, double km_achizitie, double pret) {
        //verifica daca exista masina cu modelul dat
        Masina existing = repository.findByModel (model);
        //daca exista atunci o sa faca update
        if (existing != null) {
            // keep unchanged fields as they were
            if (model.isEmpty()) {
                model = existing.getModel();
            }
            if (pret == 0 ) {
                pret = existing.getPret();
            }
            if (km_achizitie == 0) {
                km_achizitie = existing.getKm_achizitie ();
            }

        }
        Masina masina = new Masina(id,model,km_achizitie,pret);
        repository.upsert(masina);
    }
    //sterge dupa modelul dat
    public void remove(String id) {
        repository.remove(id);
    }

    //returneaza toate masinile
    public List<Masina> getAll() {
        return repository.getAll();
    }

    // returneaza masinile dupa pret
    public List<Masina> searchMasinaByPrice(double price) {
        List<Masina> masinas = new ArrayList<Masina> ();

        for(Masina m: getAll ()){
            // verificam ca fiecare masina din lista de masini diesel sa aiba pretul dupa care cautam
            // daca are pretul, o adaugam in masini_diesel
            if(m.getPret () == price ) {
                masinas.add ( m );
            }
        }
// returnam doar masinile care au pretul cautat
        return  masinas;
    }

    public List<Masina> sortByModel() {
        List<Masina> masini_sortate =getAll();
        // creem un comparator de tip Masina, care va sorta obiectele dupa model (s1.getModel(), s2.getModel()
        //sortare crescatoare
        Comparator<Masina> c = (s1, s2) -> s1.getModel ().compareTo(s2.getModel ());
        //daca vrei sa sortezi descrescator atunci cmparatorul va fi asa: (inversezi s1 cu s2)
        //  Comparator<Masina> c = (s1, s2) -> s2.getModel ().compareTo(s1.getModel ());
        masini_sortate.sort(c);

        return masini_sortate;
    }
    // functia aceasta ne ajuta sa comparam in functie de pret
    private static double compare(Masina m1, Masina m2) {
        if (m1.getPret () > m2.getPret ()) {
            return m1.getPret ();
        }
        ;
        return m2.getPret ();
    }
    public void getFileText() throws IOException {
        // luam toate elementele din lista
        List<Masina> masina = getAll();
        BufferedWriter writer = new BufferedWriter(new FileWriter("file.txt"));
        //sortare dupa pret crescator
        Comparator<Masina> comparator_pret = (m1, m2) -> (int) compare ( m1, m2 );
        masina.sort ( comparator_pret );
        for(Masina m: masina) {
            writer.write(m.toString () );
            writer.newLine ();
        }
        writer.close();

    }
}

