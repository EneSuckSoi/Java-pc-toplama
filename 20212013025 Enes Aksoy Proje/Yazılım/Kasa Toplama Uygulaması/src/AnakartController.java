
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

public class AnakartController implements Initializable {

    @FXML
    private TableView<Anakart> anakartTableView;
    @FXML
    private TableColumn<Anakart, String> markaColumn;
    @FXML
    private TableColumn<Anakart, String> modelColumn;
    @FXML
    private TableColumn<Anakart, String> bellekTipiColumn;
    @FXML
    private TableColumn<Anakart, String> megaHertzColumn;
    @FXML
    private TableColumn<Anakart, Integer> fiyatColumn;
    @FXML
    private Label sepeteEklendiLabel;
    
    ObservableList<Anakart> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sepeteEklendiLabel.setText("");
        try(Scanner scanner = new Scanner(new FileReader("Anakart.txt"))) {
            while(scanner.hasNextLine()){
                String okunanSatir = scanner.nextLine();
                String[] array = okunanSatir.split(",");
                Anakart anakart = new Anakart(array[0],array[1],array[2],array[3],Integer.parseInt(array[4]));
                list.add(anakart);
            }
            markaColumn.setCellValueFactory(new PropertyValueFactory<Anakart, String>("Marka"));
            modelColumn.setCellValueFactory(new PropertyValueFactory<Anakart, String>("Model"));
            bellekTipiColumn.setCellValueFactory(new PropertyValueFactory<Anakart, String>("BellekTipi"));
            megaHertzColumn.setCellValueFactory(new PropertyValueFactory<Anakart, String>("MegaHertz"));
            fiyatColumn.setCellValueFactory(new PropertyValueFactory<Anakart, Integer>("Fiyat"));
            anakartTableView.setItems(list);
            
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
            Anakart anakart = anakartTableView.getSelectionModel().getSelectedItem();
            writer.write(anakart.getMarka() + "," + anakart.getModel() + " " + anakart.getBellekTipi() + " " + anakart.getMegaHertz() + "," + anakart.getFiyat() + "\n");
            sepeteEklendiLabel.setText(anakart.getMarka() + " " + anakart.getModel() + " " + anakart.getBellekTipi() + " " + anakart.getMegaHertz() + " Başarıyla Sepete Eklendi!");
        } catch (IOException ex) {
            Logger.getLogger(EkranKartiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
