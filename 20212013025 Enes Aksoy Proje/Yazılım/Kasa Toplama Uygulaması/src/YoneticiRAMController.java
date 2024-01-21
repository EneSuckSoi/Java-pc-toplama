

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

public class YoneticiRAMController implements Initializable {

    @FXML
    private TableView<RAM> rAMTableView;
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
    private TextField markaTextField;
    @FXML
    private TextField bellekMiktariTextField;
    @FXML
    private TextField bellekTipiTextField;
    @FXML
    private TextField megaHertzTextField;
    @FXML
    private TextField fiyatTextField;
    @FXML
    private Button btnKaydet;
    
    ObservableList<RAM> list = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnKaydet.setDisable(true);
        try(Scanner scanner = new Scanner(new FileReader("RAM.txt"))) {
            while(scanner.hasNextLine()){
                String okunanSatir = scanner.nextLine();
                String[] array = okunanSatir.split(",");
                RAM ram = new RAM(array[0], array[1], array[2], array[3], Integer.parseInt(array[4]));
                list.add(ram);
            }
            markaColumn.setCellValueFactory(new PropertyValueFactory<RAM, String>("Marka"));
            bellekMiktariColumn.setCellValueFactory(new PropertyValueFactory<RAM, String>("BellekMiktari"));
            bellekTipiColumn.setCellValueFactory(new PropertyValueFactory<RAM, String>("BellekTipi"));
            megaHertzColumn.setCellValueFactory(new PropertyValueFactory<RAM, String>("MegaHertz"));
            fiyatColumn.setCellValueFactory(new PropertyValueFactory<RAM, Integer>("Fiyat"));
            rAMTableView.setItems(list);
            
        } catch (FileNotFoundException ex) {
            System.out.println("Dosya bulunamadı...");
        }
        rAMTableView.setEditable(true);
        markaColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        bellekMiktariColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        bellekTipiColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        megaHertzColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        fiyatColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    }    

    @FXML
    private void onEditMarka(TableColumn.CellEditEvent<RAM, String> event) {
        RAM ram = rAMTableView.getSelectionModel().getSelectedItem();
        ram.setMarka(event.getNewValue());
        btnKaydet.setDisable(false);
    }

    @FXML
    private void onEditBellekMiktari(TableColumn.CellEditEvent<RAM, String> event) {
        RAM ram = rAMTableView.getSelectionModel().getSelectedItem();
        ram.setBellekMiktari(event.getNewValue());
        btnKaydet.setDisable(false);
    }

    @FXML
    private void onEditBellekTipi(TableColumn.CellEditEvent<RAM, String> event) {
        RAM ram = rAMTableView.getSelectionModel().getSelectedItem();
        ram.setBellekTipi(event.getNewValue());
        btnKaydet.setDisable(false);
    }

    @FXML
    private void onEditMegaHertz(TableColumn.CellEditEvent<RAM, String> event) {
        RAM ram = rAMTableView.getSelectionModel().getSelectedItem();
        ram.setMegaHertz(event.getNewValue());
        btnKaydet.setDisable(false);
    }

    @FXML
    private void onEditFiyat(TableColumn.CellEditEvent<RAM, Integer> event) {
        RAM ram = rAMTableView.getSelectionModel().getSelectedItem();
        ram.setFiyat(event.getNewValue());
        btnKaydet.setDisable(false);
    }

    @FXML
    private void btnSilClicked(ActionEvent event) {
        RAM selectedItem = rAMTableView.getSelectionModel().getSelectedItem();
        rAMTableView.getItems().remove(selectedItem);
        btnKaydet.setDisable(false);
    }

    @FXML
    private void btnEkleClicked(ActionEvent event) {
        RAM ram = new RAM(markaTextField.getText(), bellekMiktariTextField.getText(), bellekTipiTextField.getText(), megaHertzTextField.getText(),Integer.parseInt(fiyatTextField.getText()));
        rAMTableView.getItems().add(ram);
        btnKaydet.setDisable(false);
        markaTextField.clear();
        bellekMiktariTextField.clear();
        bellekTipiTextField.clear();
        megaHertzTextField.clear();
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
        try(FileWriter writer = new FileWriter("RAM.txt")){
            RAM ram = new RAM("", "", "", "", 0);
            
            
            List <List<String>> arrayList = new ArrayList<>();
            
            for(int i = 0; i < rAMTableView.getItems().size(); i++){
                ram = rAMTableView.getItems().get(i);
                arrayList.add(new ArrayList<>());
                arrayList.get(i).add(ram.getMarka());
                arrayList.get(i).add(ram.getBellekMiktari());
                arrayList.get(i).add(ram.getBellekTipi());
                arrayList.get(i).add(ram.getMegaHertz());
                arrayList.get(i).add(Integer.toString(ram.getFiyat()));
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
