
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

public class PowerSupplyController implements Initializable {

    @FXML
    private TableView<PowerSupply> PowerSupplyTableView;
    @FXML
    private TableColumn<PowerSupply, String> markaColumn;
    @FXML
    private TableColumn<PowerSupply, String> wattColumn;
    @FXML
    private TableColumn<PowerSupply, Integer> fiyatColumn;
    @FXML
    private Label sepeteEklendiLabel;
    
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
    
    ObservableList<PowerSupply> list = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sepeteEklendiLabel.setText("");
        try(Scanner scanner = new Scanner(new FileReader("PowerSupply.txt"))) {
            while(scanner.hasNextLine()){
                String okunanSatir = scanner.nextLine();
                String[] array = okunanSatir.split(",");
                PowerSupply powerSupply = new PowerSupply(array[0],array[1],Integer.parseInt(array[2]));
                list.add(powerSupply);
            }
            markaColumn.setCellValueFactory(new PropertyValueFactory<PowerSupply, String>("Marka"));
            wattColumn.setCellValueFactory(new PropertyValueFactory<PowerSupply, String>("Watt"));
            fiyatColumn.setCellValueFactory(new PropertyValueFactory<PowerSupply, Integer>("Fiyat"));
            PowerSupplyTableView.setItems(list);
            
        } catch (FileNotFoundException ex) {
            System.out.println("Dosya bulunamadı...");
        }
    }    
    

    @FXML
    private void btnSepeteEkle(ActionEvent event) {
        try(FileWriter writer = new FileWriter("Sepet.txt",true)) {
            PowerSupply powerSupply = PowerSupplyTableView.getSelectionModel().getSelectedItem();
            writer.write(powerSupply.getMarka() + "," + powerSupply.getWatt() + "," + powerSupply.getFiyat() + "\n");
            sepeteEklendiLabel.setText(powerSupply.getMarka() + " " + powerSupply.getWatt() + " Başarıyla Sepete Eklendi!");   
        } catch (IOException ex) {
            Logger.getLogger(EkranKartiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
