/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import Dto.RendezVousDTO;
import Services.Service;
import com.jfoenix.controls.JFXDatePicker;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class ListeRendezVousByMedecinController implements Initializable {

    @FXML
    private TableView<RendezVousDTO> tblvRv;
    @FXML
    private TableColumn<RendezVousDTO, String> tblcCode;
    @FXML
    private TableColumn<RendezVousDTO, String> tblcNomComplet;
    @FXML
    private TableColumn<RendezVousDTO, String> tblcDate;
    @FXML
    private TableColumn<RendezVousDTO, String> tblcMotif;
    @FXML
    private JFXDatePicker txtfDate;
    
     private static ListeRendezVousByMedecinController ctrl;
     Service service =new Service();
    private int id;
    ObservableList<RendezVousDTO> obvRv;
    private RendezVousDTO RendezVousSelected;
    @FXML
    private AnchorPane anchorContent;
    private List<RendezVousDTO> listRv;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ctrl = this;
        id=LoginController.getCtrl().getUser().getId();
       listRv = service.searchRvByMedecin(id);
        loadTableView(listRv);
    }    

    @FXML
    private void handleSelctedRv(MouseEvent event) {
        RendezVousSelected = tblvRv.getSelectionModel().getSelectedItem();
        //id=RendezVousSelected.getId_rv();
        //System.out.println(RendezVousSelected);
        if(RendezVousSelected!=null){
            try {
                    loadView("v_consultation");
           
                    
                } catch (IOException ex) {
                    Logger.getLogger(ListeRendezVousByMedecinController.class.getName()).log(Level.SEVERE, null, ex);
                }
       
        }
    }
        private void loadView(String view) throws IOException{
        AnchorPane root;
        root = FXMLLoader.load(getClass().getResource("/views/"+view+".fxml"));
        anchorContent.getChildren().clear();
        anchorContent.getChildren().add(root);
    
    }

    
    private void loadTableView(List<RendezVousDTO> rv){     
         
        obvRv=FXCollections.observableArrayList(rv);
         
        //Construction des colonnes
        tblcCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        tblcNomComplet.setCellValueFactory(new PropertyValueFactory<>("nom_complet"));
        tblcDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tblcMotif.setCellValueFactory(new PropertyValueFactory<>("motif"));
        
       
        tblvRv.getSelectionModel().getSelectedItems();
        tblvRv.setItems(obvRv);   
    }
    public static ListeRendezVousByMedecinController getCtrl() {
        return ctrl;
    }
     public RendezVousDTO getRv() {
        return RendezVousSelected;
    }

    @FXML
    private void handleFiltrRvByDate(MouseEvent event) {
        String date=txtfDate.getValue().toString();
        listRv=service.searchRvByDateByMedecin(id, date);
        loadTableView(listRv);
    }

}
