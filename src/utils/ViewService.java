/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author HP
 */
public class ViewService {
    public static void loadComboBoxMotif(ComboBox<String> cboMotif){
        cboMotif.getItems().add("CONSULTATION");
        cboMotif.getItems().add("PRESTATION");
    } 
        public static void loadComboBoxFilterRendezVous(ComboBox<String> cboFilter){
        cboFilter.getItems().add("CONSULTATION");
        //cboMotif.getItems().add("RADIO");
        //cboMotif.getItems().add("RHUMOLOGIE");
        //cboMotif.getItems().add("ANALYSE");
        cboFilter.getItems().add("PRESTATION");
        //cboMotif.getItems().add("CHIRURGIE_DENTAIRE");
        //cboFilter.getSelectionModel().selectFirst();
    }
    public static void loadComboBoxTypeMedecin(ComboBox<String> cboMedecin){
        cboMedecin.getItems().add("GENERALISTE");
        cboMedecin.getItems().add("OPHTALMOLOGUE");
        cboMedecin.getItems().add("RHUMATOLOGUE");
        cboMedecin.getItems().add("NEUROLOGUE");
        cboMedecin.getItems().add("DENTISTE");
        cboMedecin.getItems().add("RADIOLOGUE");
        //cboMedecin.getSelectionModel().selectFirst();
    } 
    public static void loadComboBoxPrestation(ComboBox<String> cboPrestation){
        cboPrestation.getItems().add("RADIO");
        cboPrestation.getItems().add("RHUMOLOGIE");
        cboPrestation.getItems().add("ANALYSE");
        cboPrestation.getItems().add("DIABETOLOGIE");
        cboPrestation.getItems().add("CHIRURGIE_DENTAIRE");
        
    } 
    public static void loadComboBoxConstante(ComboBox<String> cboConst){
        cboConst.getItems().add("TEMPERATURE");
        cboConst.getItems().add("TENSION");
        cboConst.getItems().add("PULSATION");
        cboConst.getItems().add("POIDS");
        cboConst.getItems().add("FREQUENCE RESPIRATOIRE");
        cboConst.getItems().add("SATURATION EN OXYGENE");
        cboConst.getItems().add("GLYCEMIE CAPILLAIRE");
        cboConst.getItems().add("DIURESE");
        
    } 
    public void loadView(String view,AnchorPane anchorContent) throws IOException{
        AnchorPane root;
        root = FXMLLoader.load(getClass().getResource("/views/"+view+".fxml"));
        anchorContent.getChildren().clear();
        anchorContent.getChildren().add(root);
    
    }
    
}
