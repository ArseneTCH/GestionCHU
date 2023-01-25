/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import Dto.OrdonnanceDTO;
import Dto.RendezVousDTO;
import Entities.Constantes;
import Entities.Medicament;
import Entities.Prestation;
import Services.Service;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utils.ViewService;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class ConsultationController implements Initializable {

    private ObservableList<Constantes> obConstant;
    Service service =new Service();
    private final ObservableList<Constantes> obConstanteTableView = FXCollections.observableArrayList() ;
    private final ObservableList<OrdonnanceDTO> obOrdonnanceTableView = FXCollections.observableArrayList() ;
    
    private final OrdonnanceDTO o=new OrdonnanceDTO();
    private RendezVousDTO RVSelected;
    private Prestation p=null;
    private static final String DATE_FORMAT = "yyyy/MM/dd";
    private static final DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
   // private JFXTextField txtfLibelle;   
    
    @FXML
    private JFXTextField txtfValeur;
    @FXML
    private TableView<Constantes> tblvContantes;
    @FXML
    private TableColumn<Constantes, String> tblcCLibelle;
    @FXML
    private TableColumn<Constantes, String> tblcCValeur;
    @FXML
    private JFXTextField txtfCode;
    @FXML
    private JFXTextField txtfNom;
    @FXML
    private JFXTextArea txtfPosologie;
    @FXML
    private TableView<OrdonnanceDTO> tblvOrdonnance;
    @FXML
    private TableColumn<OrdonnanceDTO, String> tblcOCode;
    @FXML
    private TableColumn<OrdonnanceDTO, String> tblcONom;
    @FXML
    private TableColumn<OrdonnanceDTO, String> tblcOPosologie;
    @FXML
    private JFXComboBox<String> cboPrestation;
    @FXML
    private JFXComboBox<String> cboConst;
    @FXML
    private TableColumn<OrdonnanceDTO, String> tblcIdMedoc;
    @FXML
    private JFXTextField txtfIdMedoc;
    
    @FXML
    private JFXTextField txtfCodePatient;
    @FXML
    private JFXTextField txtfNomPatient;
    @FXML
    private JFXTextField txtfDateConsultation;
    @FXML
    private JFXDatePicker txtfDatePrestation;
    @FXML
    private AnchorPane anchorContent;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         Date currentDate = new Date();
        RVSelected=ListeRendezVousByMedecinController.getCtrl().getRv();
        //User patient=service.serachPatientById(RVSelected.getPatient_id());
        txtfCodePatient.setText(RVSelected.getCode());
        txtfNomPatient.setText(RVSelected.getNom_complet());
        txtfDateConsultation.setText(dateFormat.format(currentDate));
        
        ViewService.loadComboBoxPrestation(cboPrestation);
        loadTableViewConstante();
        loadTableViewOrdonnance();
        ViewService.loadComboBoxConstante(cboConst);   
    }    


    @FXML
    private void handleAddOrdonnance(MouseEvent event) {
         OrdonnanceDTO o =new OrdonnanceDTO();
         o.setId_medicament(Integer.parseInt(txtfIdMedoc.getText()) );
         o.setCode(txtfCode.getText());
         o.setNom(txtfNom.getText());
         o.setPosologie(txtfPosologie.getText());
         o.setId_Rv(RVSelected.getId_rv());
         
         obOrdonnanceTableView.add(o);

         clearFields();
    }

    private void loadTableViewOrdonnance(){
       tblcIdMedoc.setCellValueFactory(new PropertyValueFactory<>("id_medicament"));
        tblcOCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        tblcONom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        tblcOPosologie.setCellValueFactory(new PropertyValueFactory<>("posologie"));
        tblvOrdonnance.setItems(obOrdonnanceTableView);
    }
    
    @FXML
    private void handleAddConcultation(ActionEvent event) {
        //Prestation
        
        
        
      //Ordonnance
      o.setListOrdnc(obOrdonnanceTableView);
      o.setListConst(obConstanteTableView);
      o.setDate(txtfDatePrestation.getValue().toString());
      
      
      if(txtfDatePrestation.getValue().toString().isEmpty() && cboPrestation.getSelectionModel().getSelectedItem().isEmpty()){
      p.setDate(txtfDatePrestation.getValue().toString());
      p.setMotif(cboPrestation.getSelectionModel().getSelectedItem());
      o.setPrestation(p);
      }
      service.AddOrdonnance(o);
      RVSelected=null;
      closeWindows();
   
    }
    @FXML
    private void handleAddConstant(MouseEvent event) {
        Constantes c =new Constantes();
        c.setLibelle(cboConst.getSelectionModel().getSelectedItem());
        c.setValeur(txtfValeur.getText());
        obConstanteTableView.add(c);
        clearFields();
    }
    
    private void loadTableViewConstante(){
        
        tblcCLibelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        tblcCValeur.setCellValueFactory(new PropertyValueFactory<>("valeur"));
        tblvContantes.setItems(obConstanteTableView);
    }
    public void clearFields() {
       cboConst.getSelectionModel().clearSelection();
       txtfValeur.clear(); 
       txtfCode.clear();
       txtfNom.clear();
       txtfPosologie.clear();
       txtfIdMedoc.clear();
    }
    @FXML
    private void handleSearchMedocByCode(KeyEvent event) {
        String code= txtfCode.getText() ;
        Medicament m=service.serachMedocByCode(code);
        
        if(m !=null){
            txtfNom.setText(m.getNom());
            txtfIdMedoc.setText(Integer.toString(m.getId_medicament()));
        }
        
    }
    private void loadView(String view) throws IOException{
        AnchorPane root;
        root = FXMLLoader.load(getClass().getResource("/views/"+view+".fxml"));
        anchorContent.getChildren().clear();
        anchorContent.getChildren().add(root);
    }
    private void closeWindows(){
        try {
            loadView("v_listeRendezVousByMedecin");
        } catch (IOException ex) {
            Logger.getLogger(ConsultationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleCancelConcultation(ActionEvent event) {
            int idSel= RVSelected.getId_rv() ;

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Annulation RendezVous");
            alert.setContentText("Voulez-vous vraiment annuler ce rendezVous? ");


            alert.show();
            Optional<ButtonType> result =alert.showAndWait();
            if(result.isPresent() && result.get()==  ButtonType.OK){
                service.AnnulerRvByMedecin(RVSelected);
                //obvRv.add(RendezVousSelected);
            }
        closeWindows();
    }

    @FXML
    private void handleLoadDossizerMedical(ActionEvent event) {
        
        try {
            Parent parent =FXMLLoader.load(getClass().getResource("v_ListDosMediByMedecin.fxml"));
            Scene scene =new Scene(parent);
            Stage stage =new Stage() ;
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ConsultationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
}
