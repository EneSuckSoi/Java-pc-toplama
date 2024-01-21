
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainFXMLController implements Initializable {
    
    @FXML
    private void btnEkranKartlariClicked(ActionEvent event) {
        
        try {
            Parent root = new FXMLLoader().load(getClass().getResource("EkranKarti.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("Ekran Kartları");
            stage.setScene(scene);
            stage.show();   
        } catch (IOException ex) {
            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @FXML
    private void btnRAMlerClicked(ActionEvent event){
        try {
            Parent root = new FXMLLoader().load(getClass().getResource("RAM.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("RAM'ler");
            stage.setScene(scene);
            stage.show();   
        } catch (IOException ex) {
            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void btnIslemcilerClicked(ActionEvent event) {
        try {
            Parent root = new FXMLLoader().load(getClass().getResource("Islemci.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("İşlemciler");
            stage.setScene(scene);
            stage.show();   
        } catch (IOException ex) {
            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @FXML
    private void btnAnakartlarClicked(ActionEvent event) {
        try {
            Parent root = new FXMLLoader().load(getClass().getResource("Anakart.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("Anakartlar");
            stage.setScene(scene);
            stage.show();   
        } catch (IOException ex) {
            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @FXML
    private void btnDepolamaClicked(ActionEvent event) {
        try {
            Parent root = new FXMLLoader().load(getClass().getResource("Depolama.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("Depolama");
            stage.setScene(scene);
            stage.show();   
        } catch (IOException ex) {
            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void btnPowerSupplyClicked(ActionEvent event) {
        try {
            Parent root = new FXMLLoader().load(getClass().getResource("PowerSupply.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("PSU'lar");
            stage.setScene(scene);
            stage.show();   
        } catch (IOException ex) {
            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void btnYoneticiGirisiClicked(ActionEvent event) {
        
        try {
            
            Parent root = new FXMLLoader().load(getClass().getResource("LoginFXML.fxml"));
            Scene scene = new Scene(root);
            Stage loginStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            loginStage.setTitle("Yönetici Giriş");
            loginStage.setScene(scene);
            loginStage.show();
            
            
        } catch (IOException ex) {
            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void btnSepetiGoruntule(ActionEvent event) {
        try {
            Parent root = new FXMLLoader().load(getClass().getResource("Sepet.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Sepet");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
