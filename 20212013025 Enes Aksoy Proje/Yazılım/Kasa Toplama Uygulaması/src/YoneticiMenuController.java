
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


public class YoneticiMenuController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnEkranKartiClicked(ActionEvent event) {
        try {
            Parent root = new FXMLLoader().load(getClass().getResource("YoneticiEkranKarti.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("Ekran Kartı Yönetici");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(YoneticiMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnRAMClicked(ActionEvent event) {
        try {
            Parent root = new FXMLLoader().load(getClass().getResource("YoneticiRAM.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("RAM Yönetici");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(YoneticiMenuController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    @FXML
    private void btnAnakartClicked(ActionEvent event) {
        try {
            Parent root = new FXMLLoader().load(getClass().getResource("YoneticiAnakart.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("Anakart Yönetici");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(YoneticiMenuController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    @FXML
    private void btnIslemciClicked(ActionEvent event) {
        try {
            Parent root = new FXMLLoader().load(getClass().getResource("YoneticiIslemci.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("İşlemci Yönetici");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(YoneticiMenuController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    @FXML
    private void btnDepolamaClicked(ActionEvent event) {
        try {
            Parent root = new FXMLLoader().load(getClass().getResource("YoneticiDepolama.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("Depolama Yönetici");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(YoneticiMenuController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    @FXML
    private void btnPowerSupplyClicked(ActionEvent event) {
        try {
            Parent root = new FXMLLoader().load(getClass().getResource("YoneticiPowerSupply.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("PSU Yönetici");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(YoneticiMenuController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }
    
    @FXML
    private void btnGeriDonClicked(ActionEvent event) {
        try {
            Parent root = new FXMLLoader().load(getClass().getResource("LoginFXML.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("Yönetici Giriş");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(YoneticiMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
