
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

public class YoneticiPowerSupplyController implements Initializable {

    @FXML
    private TableView<PowerSupply> powerSupplyTableView;
    @FXML
    private TableColumn<PowerSupply, String> markaColumn;
    @FXML
    private TableColumn<PowerSupply, String> wattColumn;
    @FXML
    private TableColumn<PowerSupply, Integer> fiyatColumn;
    @FXML
    private TextField markaTextField;
    @FXML
    private TextField fiyatTextField;
    @FXML
    private TextField wattTextField;
    @FXML
    private Button btnKaydet;
    
    ObservableList<PowerSupply> list = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnKaydet.setDisable(true);
        try(Scanner scanner = new Scanner(new FileReader("PowerSupply.txt"))) {
            while(scanner.hasNextLine()){
                String okunanSatir = scanner.nextLine();
                String[] array = okunanSatir.split(",");
                PowerSupply powerSupply = new PowerSupply(array[0], array[1], Integer.parseInt(array[2]));
                list.add(powerSupply);
            }
            markaColumn.setCellValueFactory(new PropertyValueFactory<PowerSupply, String>("Marka"));
            wattColumn.setCellValueFactory(new PropertyValueFactory<PowerSupply, String>("Watt"));
            fiyatColumn.setCellValueFactory(new PropertyValueFactory<PowerSupply, Integer>("Fiyat"));
            powerSupplyTableView.setItems(list);
            
        } catch (FileNotFoundException ex) {
            System.out.println("Dosya bulunamadı...");
        }
        powerSupplyTableView.setEditable(true);
        markaColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        wattColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        fiyatColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    }    

    @FXML
    private void onEditMarka(TableColumn.CellEditEvent<PowerSupply, String> event) {
        PowerSupply powerSupply = powerSupplyTableView.getSelectionModel().getSelectedItem();
        powerSupply.setMarka(event.getNewValue());
        btnKaydet.setDisable(false);
    }

    @FXML
    private void onEditWatt(TableColumn.CellEditEvent<PowerSupply, String> event) {
        PowerSupply powerSupply = powerSupplyTableView.getSelectionModel().getSelectedItem();
        powerSupply.setWatt(event.getNewValue());
        btnKaydet.setDisable(false);
    }

    @FXML
    private void onEditFiyat(TableColumn.CellEditEvent<PowerSupply, Integer> event) {
        PowerSupply powerSupply = powerSupplyTableView.getSelectionModel().getSelectedItem();
        powerSupply.setFiyat(event.getNewValue());
        btnKaydet.setDisable(false);
    }

    @FXML
    private void btnSilClicked(ActionEvent event) {
        PowerSupply selectedItem = powerSupplyTableView.getSelectionModel().getSelectedItem();
        powerSupplyTableView.getItems().remove(selectedItem);
        btnKaydet.setDisable(false);
    }

    @FXML
    private void btnEkleClicked(ActionEvent event) {
        PowerSupply powerSupply = new PowerSupply(markaTextField.getText(), wattTextField.getText(),Integer.parseInt(fiyatTextField.getText()));
        powerSupplyTableView.getItems().add(powerSupply);
        btnKaydet.setDisable(false);
        markaTextField.clear();
        wattTextField.clear();
        fiyatTextField.clear();
    }

    @FXML
    private void btnGeriDonClicked(ActionEvent event) {
        try {
            Parent root = new FXMLLoader().load(getClass().getResource("YoneticiMenu.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("Yönetici Menü");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(YoneticiEkranKartiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnKaydetClicked(ActionEvent event) {
        try(FileWriter writer = new FileWriter("PowerSupply.txt")){
            PowerSupply powerSupply = new PowerSupply("", "", 0);
            
            
            List <List<String>> arrayList = new ArrayList<>();
            
            for(int i = 0; i < powerSupplyTableView.getItems().size(); i++){
                powerSupply = powerSupplyTableView.getItems().get(i);
                arrayList.add(new ArrayList<>());
                arrayList.get(i).add(powerSupply.getMarka());
                arrayList.get(i).add(powerSupply.getWatt());
                arrayList.get(i).add(Integer.toString(powerSupply.getFiyat()));
            }
        
            for (int i = 0; i < arrayList.size(); i++) {
                for (int j = 0; j < arrayList.get(i).size(); j++) {
                    System.out.println(arrayList.get(i).get(j));
                    writer.write(arrayList.get(i).get(j) + ",");
                }
                writer.write("\n");
            }
            
        } catch (IOException ex) {
            Logger.getLogger(YoneticiDepolamaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        btnKaydet.setDisable(true);
    }
    
    
}
