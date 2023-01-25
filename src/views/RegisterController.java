/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;



import Entities.Patient;
import Services.Service;
import Validator.Validation;
import com.jfoenix.controls.JFXTextArea;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


/**
 * FXML Controller class
 *
 * @author HP
 */
public class RegisterController implements Initializable {
    private Patient p = new Patient();
    private final Validation validation = new Validation();
    Service service = new Service();

    @FXML
    private TextField txtfRNomComplet;
    @FXML
    private TextField txtfRLogin;
    @FXML
    private TextField txtfRPassword;
    @FXML
    private Text txtfError;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    private RegisterController ctrl;
    
    
    
    @FXML
    private JFXTextArea txtaAntecedent;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
        txtfError.setVisible(false);
        
    }    
    


    @FXML
    private void handleRegister(ActionEvent event) {
        
        String nom_complet = txtfRNomComplet.getText().trim();
        String login = txtfRLogin.getText().trim();
        String password = txtfRPassword.getText().trim();
        String antecedent =txtaAntecedent.getText().trim();
        
        if(nom_complet.isEmpty()||login.isEmpty()||password.isEmpty()){
            txtfError.setText(" *Champ Obligatoire");
            txtfError.setVisible(true);
        }else if(!validation.isValidMail(login)){
                txtfError.setText("Veuillez saisir un mail correct");
                txtfError.setVisible(true);
        
        }else if(service.searchUserByLogin(login) != null){
            
            txtfError.setText("Ce Utilisatur Existe Deja");
            txtfError.setVisible(true);
        
        }else{
            String uniqueID = UUID.randomUUID().toString();
            if(antecedent.isEmpty()){
            Patient p1 =new Patient(nom_complet,login,password,uniqueID);
            int id=service.AddCompte(p1);
            }else{
            Patient p2 =new Patient(nom_complet,login,password,uniqueID,antecedent);
            int id=service.AddCompte(p2);
            }
            
            this.txtfError.getScene().getWindow().hide();
                AnchorPane root = null;
                try {
                                      
                    root = FXMLLoader.load(getClass().getResource("/views/v_login.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.initStyle(StageStyle.UNDECORATED);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Inscription");
            alert.setContentText("Inscription reussie ");
            alert.show();
            
        }

        
        }  
    
}
