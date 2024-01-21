
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class EkranKartiController implements Initializable {

    @FXML
    private TableView<EkranKarti> ekranKartiTableView;
    @FXML
    private TableColumn<EkranKarti, String> markaColumn;
    @FXML
    private TableColumn<EkranKarti, String> modelColumn;
    @FXML
    private TableColumn<EkranKarti, String> bellekBoyutuColumn;
    @FXML
    private TableColumn<EkranKarti, Integer> fiyatColumn;
    @FXML
    private Label sepeteEklendiLabel;

    @FXML
    public void btnGeriDonClicked(ActionEvent event){
        try {
            Parent root = new FXMLLoader().load(getClass().getResource("MainFXML.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("Kasa Toplama Uygulaması");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(EkranKartiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    ObservableList<EkranKarti> list = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sepeteEklendiLabel.setText("");
        try(Scanner scanner = new Scanner(new FileReader("EkranKarti.txt"))) {
            while(scanner.hasNextLine()){
                String okunanSatir = scanner.nextLine();
                String[] array = okunanSatir.split(",");
                EkranKarti ekranKarti;
                ekranKarti = new EkranKarti(array[0],array[1],array[2],Integer.parseInt(array[3]));
                list.add(ekranKarti);
            }
            markaColumn.setCellValueFactory(new PropertyValueFactory<EkranKarti, String>("Marka"));
            modelColumn.setCellValueFactory(new PropertyValueFactory<EkranKarti, String>("Model"));
            bellekBoyutuColumn.setCellValueFactory(new PropertyValueFactory<EkranKarti, String>("BellekBoyutu"));
            fiyatColumn.setCellValueFactory(new PropertyValueFactory<EkranKarti, Integer>("Fiyat"));
            ekranKartiTableView.setItems(list);
            
        } catch (FileNotFoundException ex) {
            System.out.println("Dosya bulunamadı...");
        }
    }
    
    @FXML
    public void btnSepeteEkle(ActionEvent event) {
        
        try(FileWriter writer = new FileWriter("Sepet.txt",true)) {
            EkranKarti ekranKarti = ekranKartiTableView.getSelectionModel().getSelectedItem();
            writer.write(ekranKarti.getMarka() + "," + ekranKarti.getModel() + " " + ekranKarti.getBellekBoyutu() + "," + ekranKarti.getFiyat() + "\n");
            sepeteEklendiLabel.setText(ekranKarti.getMarka() + " " + ekranKarti.getModel() + " " + ekranKarti.getBellekBoyutu() +" Başarıyla Sepete Eklendi!");
        } catch (IOException ex) {
            Logger.getLogger(EkranKartiController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
}
