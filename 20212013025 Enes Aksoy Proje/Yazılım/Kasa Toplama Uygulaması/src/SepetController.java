
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class SepetController implements Initializable,SepetToplamiHesapla {
    
    @FXML
    private TableView<BilgisayarParcasi> sepetTableView;
    
    @FXML
    private TableColumn<BilgisayarParcasi, String> markaColumn;
    
    @FXML
    private TableColumn<BilgisayarParcasi, String> ozelliklerColumn;
    
    @FXML
    private TableColumn<BilgisayarParcasi, Integer> fiyatColumn;
    
    @FXML
    private Button btnKaydet;
    
    @FXML
    private Label sepetToplamiLabel;
    
    @FXML
    private Label siparisLabel;
    
    
    ObservableList<BilgisayarParcasi> list = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnKaydet.setDisable(true);
        try (Scanner scanner = new Scanner(new FileReader("Sepet.txt"))) {
            while(scanner.hasNextLine()){
                String okunanSatir = scanner.nextLine();
                String[] array = okunanSatir.split(",");
                BilgisayarParcasi bilgisayarParcasi = new BilgisayarParcasi(array[0], array[1] , Integer.parseInt(array[2]));
                list.add(bilgisayarParcasi);
            }
            markaColumn.setCellValueFactory(new PropertyValueFactory<BilgisayarParcasi, String>("Marka"));
            ozelliklerColumn.setCellValueFactory(new PropertyValueFactory<BilgisayarParcasi, String>("Ozellikler"));
            fiyatColumn.setCellValueFactory(new PropertyValueFactory<BilgisayarParcasi, Integer>("Fiyat"));
            sepetTableView.setItems(list);
            sepetToplamiLabel.setText(sepetToplamiHesapla());
        } catch (FileNotFoundException ex) {
            System.out.println("Dosya bulunamadı.");
        }
    }    
    
    @FXML
    public void btnSepettenKaldir(ActionEvent event) {
        BilgisayarParcasi selectedItem = sepetTableView.getSelectionModel().getSelectedItem();
        sepetTableView.getItems().remove(selectedItem);
        sepetToplamiLabel.setText(sepetToplamiHesapla());
        
        btnKaydet.setDisable(false);
    }
    
    @FXML
    public void btnSepetiTemizle(ActionEvent event) {
        sepetTableView.getItems().clear();
        btnKaydet.setDisable(false);
        sepetToplamiLabel.setText(sepetToplamiHesapla());
    }
    
    @FXML
    public void btnSepetiOnayla(ActionEvent event) {
        sepetTableView.getItems().clear();
        sepetToplamiLabel.setText(sepetToplamiHesapla());
        siparisLabel.setText("Satın alımınız başarıyla gerçekleşti!");
        btnKaydetClicked(event);
    }
    
    @FXML
    public void btnKaydetClicked(ActionEvent event) {
        
        try(FileWriter writer = new FileWriter("Sepet.txt")){
            BilgisayarParcasi bilgisayarParcasi = new BilgisayarParcasi("", "", 0);
            
            
            List <List<String>> arrayList = new ArrayList<>();
            
            for(int i = 0; i < sepetTableView.getItems().size(); i++){
                bilgisayarParcasi = sepetTableView.getItems().get(i);
                arrayList.add(new ArrayList<>());
                arrayList.get(i).add(bilgisayarParcasi.getMarka());
                arrayList.get(i).add(bilgisayarParcasi.getOzellikler());
                arrayList.get(i).add(Integer.toString(bilgisayarParcasi.getFiyat()));
            }
        
            for (int i = 0; i < arrayList.size(); i++) {
                for (int j = 0; j < arrayList.get(i).size(); j++) {
                    System.out.println(arrayList.get(i).get(j));
                    writer.write(arrayList.get(i).get(j) + ",");
                }
                writer.write("\n");
            }
            
        } catch (IOException ex) {
            Logger.getLogger(SepetController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        btnKaydet.setDisable(true);
        
    }
    
    @FXML
    private void btnAnaMenuyeDonClicked(ActionEvent event) {
        try {
            Parent root = new FXMLLoader().load(getClass().getResource("MainFXML.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("Kasa Toplama");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(SepetController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String sepetToplamiHesapla() {
        int toplam = 0;
        for(int i = 0; i< sepetTableView.getItems().size(); i++){
            BilgisayarParcasi bilgisayarParcasi = sepetTableView.getItems().get(i);
            toplam += bilgisayarParcasi.getFiyat();
        }
        return (Integer.toString(toplam) + " $");
    }
    
}
