
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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginFXMLController implements Initializable {
    String adminKullaniciAdi = "admin";
    String adminSifre = "admin";
    
    @FXML
    private TextField kullaniciAdiTxtField;
    @FXML
    private PasswordField sifrePasswordField;
    @FXML
    private Label lblYanlis;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
    }    

    @FXML
    private void btnGirisYapClicked(ActionEvent event) {
        String kullaniciAdi = kullaniciAdiTxtField.getText();
        String sifre = sifrePasswordField.getText();
        
        if(kullaniciAdi.equals(adminKullaniciAdi) && sifre.equals(adminSifre)){
            lblYanlis.setText("");
            try {
                Parent root = new FXMLLoader().load(getClass().getResource("YoneticiMenu.fxml"));
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setTitle("Yönetici Menü");
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(LoginFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            lblYanlis.setText("Kullanıcı adı veya şifre yanlış.");
        }
    }
    @FXML
    private void btnGeriDonClicked(ActionEvent event) {
        try {
            
            Parent root = new FXMLLoader().load(getClass().getResource("MainFXML.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("Kasa Toplama Uygulaması");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
