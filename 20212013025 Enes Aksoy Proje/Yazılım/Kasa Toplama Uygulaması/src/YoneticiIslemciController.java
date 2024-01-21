
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

public class YoneticiIslemciController implements Initializable {

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
    private TextField markaTextField;
    @FXML
    private TextField modelTextField;
    @FXML
    private TextField gigaHertzTextField;
    @FXML
    private TextField cekirdekSayisiTextField;
    @FXML
    private TextField fiyatTextField;
    @FXML
    private Button btnKaydet;
    
    ObservableList<Islemci> list = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnKaydet.setDisable(true);
        try(Scanner scanner = new Scanner(new FileReader("Islemci.txt"))) {
            while(scanner.hasNextLine()){
                String okunanSatir = scanner.nextLine();
                String[] array = okunanSatir.split(",");
                Islemci islemci = new Islemci(array[0], array[1], array[2], Integer.parseInt(array[3]), Integer.parseInt(array[4]));
                list.add(islemci);
            }
            markaColumn.setCellValueFactory(new PropertyValueFactory<Islemci, String>("Marka"));
            modelColumn.setCellValueFactory(new PropertyValueFactory<Islemci, String>("Model"));
            gigaHertzColumn.setCellValueFactory(new PropertyValueFactory<Islemci, String>("gigaHertz"));
            cekirdekSayisiColumn.setCellValueFactory(new PropertyValueFactory<Islemci, Integer>("cekirdekSayisi"));
            fiyatColumn.setCellValueFactory(new PropertyValueFactory<Islemci, Integer>("Fiyat"));
            islemciTableView.setItems(list);
            
        } catch (FileNotFoundException ex) {
            System.out.println("Dosya bulunamadı...");
        }
        islemciTableView.setEditable(true);
        markaColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        modelColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        gigaHertzColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        cekirdekSayisiColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        fiyatColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));    
    }    

    @FXML
    private void onEditMarka(TableColumn.CellEditEvent<Islemci, String> event) {
        Islemci islemci = islemciTableView.getSelectionModel().getSelectedItem();
        islemci.setMarka(event.getNewValue());
        btnKaydet.setDisable(false);
    }

    @FXML
    private void onEditModel(TableColumn.CellEditEvent<Islemci, String> event) {
        Islemci islemci = islemciTableView.getSelectionModel().getSelectedItem();
        islemci.setModel(event.getNewValue());
        btnKaydet.setDisable(false);
    }

    @FXML
    private void onEditGigaHertz(TableColumn.CellEditEvent<Islemci, String> event) {
        Islemci islemci = islemciTableView.getSelectionModel().getSelectedItem();
        islemci.setGigaHertz(event.getNewValue());
        btnKaydet.setDisable(false);
    }

    @FXML
    private void onEditCekirdekSayisi(TableColumn.CellEditEvent<Islemci, Integer> event) {
        Islemci islemci = islemciTableView.getSelectionModel().getSelectedItem();
        islemci.setCekirdekSayisi(event.getNewValue());
        btnKaydet.setDisable(false);
    }

    @FXML
    private void onEditFiyat(TableColumn.CellEditEvent<Islemci, Integer> event) {
        Islemci islemci = islemciTableView.getSelectionModel().getSelectedItem();
        islemci.setFiyat(event.getNewValue());
        btnKaydet.setDisable(false);
    }

    @FXML
    private void btnSilClicked(ActionEvent event) {
        Islemci selectedItem = islemciTableView.getSelectionModel().getSelectedItem();
        islemciTableView.getItems().remove(selectedItem);
        btnKaydet.setDisable(false);
    }

    @FXML
    private void btnEkleClicked(ActionEvent event) {
        Islemci islemci = new Islemci(markaTextField.getText(), modelTextField.getText(), gigaHertzTextField.getText(), Integer.parseInt(cekirdekSayisiTextField.getText()),Integer.parseInt(fiyatTextField.getText()));
        islemciTableView.getItems().add(islemci);
        btnKaydet.setDisable(false);
        markaTextField.clear();
        modelTextField.clear();
        gigaHertzTextField.clear();
        cekirdekSayisiTextField.clear();
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
        try(FileWriter writer = new FileWriter("Islemci.txt")){
            Islemci islemci = new Islemci("", "", "", 0, 0);
            
            
            List <List<String>> arrayList = new ArrayList<>();
            
            for(int i = 0; i < islemciTableView.getItems().size(); i++){
                islemci = islemciTableView.getItems().get(i);
                arrayList.add(new ArrayList<>());
                arrayList.get(i).add(islemci.getMarka());
                arrayList.get(i).add(islemci.getModel());
                arrayList.get(i).add(islemci.getGigaHertz());
                arrayList.get(i).add(Integer.toString(islemci.getCekirdekSayisi()));
                arrayList.get(i).add(Integer.toString(islemci.getFiyat()));
            }
        
            for (int i = 0; i < arrayList.size(); i++) {
                for (int j = 0; j < arrayList.get(i).size(); j++) {
                    System.out.println(arrayList.get(i).get(j));
                    writer.write(arrayList.get(i).get(j) + ",");
                }
                writer.write("\n");
            }
            
        } catch (IOException ex) {
            Logger.getLogger(YoneticiIslemciController.class.getName()).log(Level.SEVERE, null, ex);
        }
        btnKaydet.setDisable(true);
    }
    
}
