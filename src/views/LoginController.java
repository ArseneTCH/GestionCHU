/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import Entities.User;
import Services.Service;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class LoginController implements Initializable {

    @FXML
    private TextField txtflogin;
    @FXML
    private Text txtError;
    private final Service service = new Service();
    
    private static LoginController ctrl;
    private String vue;
    private User user;
    @FXML
    private PasswordField txtfpasse;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        txtError.setVisible(false);
         ctrl = this;
    }    

    @FXML
    private void handleClear(ActionEvent event) {
        txtflogin.clear();
        txtfpasse.clear();
        txtError.setVisible(false);
    }

    @FXML
    private void handleLogin(ActionEvent event) {
        String login = txtflogin.getText().trim();
        String password = txtfpasse.getText().trim();
        if(login.isEmpty() || password.isEmpty())
        {
          txtError.setText("login ou le mot de passe Obligatoire");
          txtError.setVisible(true);
        }else{
            user = service.login(login, password);
            
            if(user == null)
          {
               txtError.setText("login ou le mot de passe Incorrect ");
               txtError.setVisible(true);
          }
          else
          {
              
                String roles= user.getRole();
                vue= null;
                if(roles.compareTo("ROLE_PATIENT")==0){
                    vue="v_Patient.fxml";
                }
                if(roles.compareTo("ROLE_MEDECIN")==0){
                    vue="v_medecin.fxml";
                }
                if(roles.compareTo("ROLE_SECRETAIRE")==0){
                    vue="v_secretaire.fxml";
                }
                if(roles.compareTo("ROLE_RESPONSABLE_PRESTATION")==0){
                    vue="v_responsablePrestation.fxml";
                }
                
                
               //Cache la fénétre de connexion
                this.txtError.getScene().getWindow().hide();
                AnchorPane root = null;
                try {
                                      
                    root = FXMLLoader.load(getClass().getResource("/views/"+vue));
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    //stage.initStyle(StageStyle.UNDECORATED);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
          }
        }
    }

    @FXML
    private void HandleRegister(MouseEvent event) {
        try {
            this.txtError.getScene().getWindow().hide();
            AnchorPane root = null;

            root = FXMLLoader.load(getClass().getResource("/views/v_register.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }
    
    public static LoginController getCtrl() {
        return ctrl;
    }

    public User getUser() {
        return user;
    }

    @FXML
    private void handleNewCompte(ActionEvent event) {
        try {
            this.txtError.getScene().getWindow().hide();
            AnchorPane root = null;

            root = FXMLLoader.load(getClass().getResource("/views/v_register.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
