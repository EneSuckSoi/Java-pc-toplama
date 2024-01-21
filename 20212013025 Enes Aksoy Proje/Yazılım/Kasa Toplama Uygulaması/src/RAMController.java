
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

public class RAMController implements Initializable {

    @FXML
    private TableView<RAM> ramTableView;
    @FXML
    private TableColumn<RAM, String> markaColumn;
    @FXML
    private TableColumn<RAM, String> bellekMiktariColumn;
    @FXML
    private TableColumn<RAM, String> bellekTipiColumn;
    @FXML
    private TableColumn<RAM, String> megaHertzColumn;
    @FXML
    private TableColumn<RAM, Integer> fiyatColumn;
    @FXML
    private Label sepeteEklendiLabel;

    
    ObservableList<RAM> list = FXCollections.observableArrayList();
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sepeteEklendiLabel.setText("");
         try(Scanner scanner = new Scanner(new FileReader("RAM.txt"))) {
            while(scanner.hasNextLine()){
                String okunanSatir = scanner.nextLine();
                String[] array = okunanSatir.split(",");
                RAM ram = new RAM(array[0],array[1],array[2],array[3],Integer.parseInt(array[4]));
                list.add(ram);
            }
            markaColumn.setCellValueFactory(new PropertyValueFactory<RAM, String>("Marka"));
            bellekMiktariColumn.setCellValueFactory(new PropertyValueFactory<RAM, String>("BellekMiktari"));
            bellekTipiColumn.setCellValueFactory(new PropertyValueFactory<RAM, String>("BellekTipi"));
            megaHertzColumn.setCellValueFactory(new PropertyValueFactory<RAM, String>("MegaHertz"));
            fiyatColumn.setCellValueFactory(new PropertyValueFactory<RAM, Integer>("Fiyat"));
            ramTableView.setItems(list);
            
            
            
            scanner.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Dosya bulunamadı...");
        }
         
    }    
    
    @FXML
    private void btnSepeteEkle(ActionEvent event) {
        try(FileWriter writer = new FileWriter("Sepet.txt",true)) {
            RAM ram = ramTableView.getSelectionModel().getSelectedItem();
            writer.write(ram.getMarka() + "," + ram.getBellekMiktari() + " " + ram.getBellekTipi() + " " + ram.getMegaHertz() + "," + ram.getFiyat() + "\n");
            sepeteEklendiLabel.setText(ram.getMarka() + " " + ram.getBellekMiktari()+ " " + ram.getBellekTipi() + " " + ram.getMegaHertz() + " Başarıyla Sepete Eklendi!");  
        } catch (IOException ex) {
            Logger.getLogger(EkranKartiController.class.getName()).log(Level.SEVERE, null, ex);
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
}
