
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

public class DepolamaController implements Initializable {

    @FXML
    private TableView<Depolama> depolamaTableView;
    @FXML
    private TableColumn<Depolama, String> markaColumn;
    @FXML
    private TableColumn<Depolama, String> diskTuruColumn;
    @FXML
    private TableColumn<Depolama, String> depolamaBoyutuColumn;
    @FXML
    private TableColumn<Depolama, String> hizColumn;
    @FXML
    private TableColumn<Depolama, Integer> fiyatColumn;
    @FXML
    private Label sepeteEklendiLabel;
    
    ObservableList<Depolama> list = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sepeteEklendiLabel.setText("");
        try(Scanner scanner = new Scanner(new FileReader("Depolama.txt"))) {
            while(scanner.hasNextLine()){
                String okunanSatir = scanner.nextLine();
                String[] array = okunanSatir.split(",");
                Depolama depolama = new Depolama(array[0],array[1],array[2],array[3],Integer.parseInt(array[4]));
                list.add(depolama);
            }
            markaColumn.setCellValueFactory(new PropertyValueFactory<Depolama, String>("Marka"));
            diskTuruColumn.setCellValueFactory(new PropertyValueFactory<Depolama, String>("DiskTuru"));
            depolamaBoyutuColumn.setCellValueFactory(new PropertyValueFactory<Depolama, String>("DepolamaBoyutu"));
            hizColumn.setCellValueFactory(new PropertyValueFactory<Depolama, String>("okumaYazmaHizi"));
            fiyatColumn.setCellValueFactory(new PropertyValueFactory<Depolama, Integer>("Fiyat"));
            depolamaTableView.setItems(list);
            
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
            Depolama depolama = depolamaTableView.getSelectionModel().getSelectedItem();
            writer.write(depolama.getMarka() + "," + depolama.getDiskTuru() + " " + depolama.getDepolamaBoyutu() + " " + depolama.getOkumaYazmaHizi() + "," + depolama.getFiyat() + "\n");
            sepeteEklendiLabel.setText(depolama.getMarka() + " " + depolama.getDiskTuru()+ " " + depolama.getDepolamaBoyutu()+ " " + depolama.getOkumaYazmaHizi()+ " Başarıyla Sepete Eklendi!");
        } catch (IOException ex) {
            Logger.getLogger(EkranKartiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
