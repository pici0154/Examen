package UI;

import Domain.Masina;
import Domain.Inchiriere;
import Service.MasinaService;
import Service.InchiriereService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller {
    @FXML

    public TableView tableViewMasina;
    public TableColumn tableColumnId;
    public TableColumn tableColumnModel;
    public TableColumn tableColumnKm_achizitie;
    public TableColumn tableColumnPret;

    public TableView tableViewInchiriere;
    public TableColumn tableColumnIdi;
    public TableColumn tableColumnId_masina;
    public TableColumn tableColumnnNr_zile;
    public TableColumn tableColumnnKm_parcursi;

    public TextField txtGetByPrice;
    public Button btnMasinaAdd;
    public Button btnMasinaDelete;
    public Button btnInchiriereDelete;
    public Button btnSortMasinaModel;
    public Button btnGetFileModel;

    private ObservableList<Masina> masinas = FXCollections.observableArrayList();
    private ObservableList<Inchiriere> inchirieres = FXCollections.observableArrayList();

    private MasinaService masinaService;
    private InchiriereService inchiriereService;

    public void setServices(MasinaService masinaService, InchiriereService inchiriereService) {
        this.masinaService = masinaService;
        this.inchiriereService = inchiriereService;
    }

    // pentru a initializa fereastra masini.fxml
    @FXML
    private void initialize() {

        Platform.runLater(() -> {
            // initializam tabelul pentru masini diesel
            masinas.addAll(masinaService.getAll());
            tableViewMasina.setItems(masinas);

            // initializam tabelul pentru masini electrice
            inchirieres.addAll(inchiriereService.getAll());  // in electrics pune toate masinile electrice
            tableViewInchiriere.setItems(inchirieres);  // seteaza masinile in tabel
        });
    }

    public void btnMasinaAddClick(ActionEvent actionEvent) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("addMasina.fxml"));
//initializarea fereastra de adaugare
            Scene scene = new Scene(fxmlLoader.load(), 600, 200);
            Stage stage = new Stage();
            stage.setTitle("Masina add");
            stage.setScene(scene);
            stage.initModality( Modality.APPLICATION_MODAL);


            MasinaAddController controller =  fxmlLoader.getController();
            controller.setService(masinaService, inchiriereService);
            stage.showAndWait();
            // dupa adaugarea unei masini reinitializam tabelul de  masini
            masinas.clear();
            masinas.addAll(masinaService.getAll());
// dupa adaugarea unei masini reinitializam tabelul de inchirieri
            inchirieres.clear();
            inchirieres.addAll(inchiriereService.getAll());


        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log( Level.SEVERE, "Failed to create new Window: add.", e);
        }
    }

    public void btnMasinaDeleteClick(ActionEvent actionEvent) {
        //returneaza linia pe care o selectam in Masini Diesel
        Masina selected = (Masina)tableViewMasina.getSelectionModel().getSelectedItem();
        //daca linia selectata nu e initiala, atunci sterge linia selectata
        if (selected != null) {
            try {
                masinaService.remove(selected.getModel ());
                masinas.clear();
                masinas.addAll(masinaService.getAll());
            } catch (RuntimeException rex) {
                Common.showValidationError(rex.getMessage());
            }
        }
    }

    public void btnInchiriereDeleteClick(ActionEvent actionEvent) {
        //returneaza linia selectata din tabelul de masini electrice
        Inchiriere selected = (Inchiriere) tableViewInchiriere.getSelectionModel().getSelectedItem();
        //daca linia selectata nu e initiala, atunci sterge linia selectata
        if (selected != null) {
            try {
                inchiriereService.remove(selected.getId ());
                inchirieres.clear();
                inchirieres.addAll(inchiriereService.getAll());
            } catch (RuntimeException rex) {
                Common.showValidationError(rex.getMessage());
            }
        }
    }

    // cauta doar masinile dupa pret
    public void btnGetByPriceClick(ActionEvent actionEvent) {
        double price = Double.parseDouble ( txtGetByPrice.getText () );

        if (price != 0){
            masinas.clear();
            // setam ca in tabel sa apara doar masinile cu pretul cautat
            masinas.addAll(masinaService.searchMasinaByPrice(price));
        }
    }

    // sortare masini dupa zile de inchiriere
    public void btnSortByModelClick(ActionEvent actionEvent) {

        masinas.clear();
        // setam ca in tabel sa apara doar masinile cu pretul cautat
        masinas.addAll(masinaService.sortByModel ());

    }
    // salvarea masinilor Diesel intr-un fisier text sortate dupa pret
    public void btnGetFileClick(ActionEvent actionEvent) throws IOException {


        // setam ca in tabel sa apara doar masinile cu pretul cautat
        masinaService.getFileText();

    }
}
