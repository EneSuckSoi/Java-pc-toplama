
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

public class IslemciController implements Initializable {
    @FXML
    private TableView<Islemci> islemciTableView;
    @FXML
    private TableColumn<Islemci, String> markaColumn;
    @FXML
    private TableColumn<Islemci, String> modelColumn;
    @FXML
    private TableColumn<Islemci, String> gigaHertzColumn;
    @FXML
    private TableColumn<Islemci, Integer> cekirdekSayisiColumn;
    @FXML
    private TableColumn<Islemci, Integer> fiyatColumn;
    @FXML
    private Label sepeteEklendiLabel;
    
    ObservableList<Islemci> list = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sepeteEklendiLabel.setText("");
        try(Scanner scanner = new Scanner(new FileReader("Islemci.txt"))) {
            while(scanner.hasNextLine()){
                String okunanSatir = scanner.nextLine();
                String[] array = okunanSatir.split(",");
                Islemci islemci = new Islemci(array[0],array[1],array[2],Integer.parseInt(array[3]),Integer.parseInt(array[4]));
                list.add(islemci);
            }
            markaColumn.setCellValueFactory(new PropertyValueFactory<Islemci, String>("Marka"));
            modelColumn.setCellValueFactory(new PropertyValueFactory<Islemci, String>("Model"));
            cekirdekSayisiColumn.setCellValueFactory(new PropertyValueFactory<Islemci, Integer>("CekirdekSayisi"));
            gigaHertzColumn.setCellValueFactory(new PropertyValueFactory<Islemci, String>("GigaHertz"));
            fiyatColumn.setCellValueFactory(new PropertyValueFactory<Islemci, Integer>("Fiyat"));
            islemciTableView.setItems(list);
            
        } catch (FileNotFoundException ex) {
            System.out.println("Dosya bulunamadı...");
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
            Logger.getLogger(EkranKartiController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void btnSepeteEkle(ActionEvent event) {
        
        try(FileWriter writer = new FileWriter("Sepet.txt",true)) {
            Islemci islemci = islemciTableView.getSelectionModel().getSelectedItem();
            writer.write(islemci.getMarka() + "," + islemci.getModel() + " " + islemci.getGigaHertz() + " " + islemci.getCekirdekSayisi() + "," + islemci.getFiyat() + "\n");
            sepeteEklendiLabel.setText(islemci.getMarka() + " " + islemci.getModel() + " " + islemci.getGigaHertz()+ " " + islemci.getCekirdekSayisi()+ " Başarıyla Sepete Eklendi!");
        } catch (IOException ex) {
            Logger.getLogger(EkranKartiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
