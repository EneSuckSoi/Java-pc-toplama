
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

public class YoneticiEkranKartiController implements Initializable {

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
    private TextField markaTextField;
    @FXML
    private TextField modelTextField;
    @FXML
    private TextField bellekBoyutuTextField;
    @FXML
    private TextField fiyatTextField;
    @FXML
    private Button btnKaydet;
    
    

    ObservableList<EkranKarti> list = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnKaydet.setDisable(true);
        try(Scanner scanner = new Scanner(new FileReader("EkranKarti.txt"))) {
            while(scanner.hasNextLine()){
                String okunanSatir = scanner.nextLine();
                String[] array = okunanSatir.split(",");
                EkranKarti ekranKarti = new EkranKarti(array[0],array[1],array[2],Integer.parseInt(array[3]));
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
        ekranKartiTableView.setEditable(true);
        markaColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        modelColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        bellekBoyutuColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        fiyatColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    }    
    
    public void onEditMarka(TableColumn.CellEditEvent<EkranKarti, String> ekranKartiStringCellEditEvent){
        EkranKarti ekranKarti = ekranKartiTableView.getSelectionModel().getSelectedItem();
        ekranKarti.setMarka(ekranKartiStringCellEditEvent.getNewValue());
        btnKaydet.setDisable(false);
    }

    public void onEditModel(TableColumn.CellEditEvent<EkranKarti, String> ekranKartiStringCellEditEvent){
        EkranKarti ekranKarti = ekranKartiTableView.getSelectionModel().getSelectedItem();
        ekranKarti.setModel(ekranKartiStringCellEditEvent.getNewValue());
        btnKaydet.setDisable(false);
    }
    
    public void onEditBellekBoyutu(TableColumn.CellEditEvent<EkranKarti, String> ekranKartiStringCellEditEvent){
        EkranKarti ekranKarti = ekranKartiTableView.getSelectionModel().getSelectedItem();
        ekranKarti.setBellekBoyutu(ekranKartiStringCellEditEvent.getNewValue());
        btnKaydet.setDisable(false);
    }
    
    public void onEditFiyat(TableColumn.CellEditEvent<EkranKarti, Integer> ekranKartiIntegerCellEditEvent){
        EkranKarti ekranKarti = ekranKartiTableView.getSelectionModel().getSelectedItem();
        ekranKarti.setFiyat(ekranKartiIntegerCellEditEvent.getNewValue());
        btnKaydet.setDisable(false);
    }
    
    
    @FXML
    private void btnDuzenleClicked(ActionEvent event) {
        
        
        btnKaydet.setDisable(false);
    }

    @FXML
    private void btnSilClicked(ActionEvent event) {
        EkranKarti selectedItem = ekranKartiTableView.getSelectionModel().getSelectedItem();
        ekranKartiTableView.getItems().remove(selectedItem);
        btnKaydet.setDisable(false);
    }

    @FXML
    private void btnEkleClicked(ActionEvent event) {
        EkranKarti ekranKarti = new EkranKarti(markaTextField.getText(), modelTextField.getText(), bellekBoyutuTextField.getText(), Integer.parseInt(fiyatTextField.getText()));
        ekranKartiTableView.getItems().add(ekranKarti);
        btnKaydet.setDisable(false);
        markaTextField.clear();
        modelTextField.clear();
        bellekBoyutuTextField.clear();
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
        try(FileWriter writer = new FileWriter("EkranKarti.txt")){
            EkranKarti ekranKarti = new EkranKarti("", "", "", 0);
            
            
            List <List<String>> arrayList = new ArrayList<>();
            
            for(int i = 0; i < ekranKartiTableView.getItems().size(); i++){
                ekranKarti = ekranKartiTableView.getItems().get(i);
                arrayList.add(new ArrayList<>());
                arrayList.get(i).add(ekranKarti.getMarka());
                arrayList.get(i).add(ekranKarti.getModel());
                arrayList.get(i).add(ekranKarti.getBellekBoyutu());
                arrayList.get(i).add(Integer.toString(ekranKarti.getFiyat()));
            }
        
            for (int i = 0; i < arrayList.size(); i++) {
                for (int j = 0; j < arrayList.get(i).size(); j++) {
                    System.out.println(arrayList.get(i).get(j));
                    writer.write(arrayList.get(i).get(j) + ",");
                }
                writer.write("\n");
            }
            
        } catch (IOException ex) {
            Logger.getLogger(YoneticiEkranKartiController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        btnKaydet.setDisable(true);
    }
    
}
