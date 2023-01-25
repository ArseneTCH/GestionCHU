/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import Dto.RendezVousDTO;
import Services.Service;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class MedecinController implements Initializable {
    Service service =new Service();
    private int id;
    ObservableList<RendezVousDTO> obvRv;
    //private RendezVousDTO RendezVousSelected;
     private static MedecinController ctrl;
    //private RendezVous rendezVous;
    
    @FXML
    private Text txtName;
    
    @FXML
    private AnchorPane anchoreContent;

    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ctrl = this;
            int id=LoginController.getCtrl().getUser().getId();
            txtName.setText(LoginController.getCtrl().getUser().getNom_complet());
            loadView("v_listeRendezVousByMedecin");
        } catch (IOException ex) {
            Logger.getLogger(MedecinController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    @FXML
    private void handleLogOut(MouseEvent event) {
        
        try {
            this.txtName.getScene().getWindow().hide();
            AnchorPane root = null;            
            root = FXMLLoader.load(getClass().getResource("/views/v_login.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MedecinController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void loadView(String view) throws IOException{
        AnchorPane root;
        root = FXMLLoader.load(getClass().getResource("/views/"+view+".fxml"));
        anchoreContent.getChildren().clear();
        anchoreContent.getChildren().add(root);
    }
    @FXML
    private void handleBackToList(MouseEvent event) {
        try {
            loadView("v_listeRendezVousByMedecin");
        } catch (IOException ex) {
            Logger.getLogger(MedecinController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static MedecinController getCtrl() {
        return ctrl;
    }
     

    
}
