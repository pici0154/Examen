package UI;

import Domain.Inchiriere;
import Service.MasinaService;
import Service.InchiriereService;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import static java.lang.Integer.parseInt;

public class MasinaAddController {
    public TextField txtId;
    public TextField txtModel;
    public TextField txtKm_achizitie;
    public TextField txtPret;
    public TextField txtIdi;
    public TextField txtId_masina;
    public TextField txtNr_zile;
    public TextField txtKm_parcursi;


    public CheckBox chkMasina;
    public CheckBox chkInchiriere;

    public Button btnAdd;
    public Button btnCancel;

    private MasinaService masinaService;
    private InchiriereService inchiriereService;

    //metoda de setare a serviciilor
    public void setService(MasinaService masinaService, InchiriereService inchiriereService) {
        this.masinaService = masinaService;
        this.inchiriereService = inchiriereService;
    }


    public void btnCancelClick(ActionEvent actionEvent) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

  public void btnAddClick(ActionEvent actionEvent) {

        try {

            String id = txtId.getText();
            String model = txtModel.getText();
            double km_achizitie = txtKm_achizitie.getText();
            double pret = Double.parseDouble(txtPret.getText());
            String idi = txtId.getText ();
            String id_masina= txtId_masina.getText();
            int nr_zile = parseInt(txtNr_zile.getText ());
            double km_parcursi =Double.parseDouble (txtKm_parcursi.getText());


            boolean checkMasina = chkMasina.isSelected();
            boolean checkInchiriere = chkInchiriere.isSelected();
            //daca checkboxx Diesel e selectat
            if (checkMasina == true){
                //adaugam o masina de tip diesel
                masinaService.addOrUpdate ( id, model, km_achizitie, pret );
            }else if(checkInchiriere == true){
                //altfel, adaugam masina electrica
                inchiriereService.addOrUpdate ( idi, id_masina, nr_zile, km_parcursi );
            }

            btnCancelClick(actionEvent);
        } catch (RuntimeException rex) {
            Common.showValidationError(rex.getMessage());
        }
    }

}
