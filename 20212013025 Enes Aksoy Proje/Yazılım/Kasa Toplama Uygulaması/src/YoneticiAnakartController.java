
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

public class YoneticiAnakartController implements Initializable {

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
    private TextField markaTextField;
    @FXML
    private TextField modelTextField;
    @FXML
    private TextField bellekTipiTextField;
    @FXML
    private TextField megaHertzTextField;
    @FXML
    private TextField fiyatTextField;
    @FXML
    private Button btnKaydet;
    
    ObservableList<Anakart> list = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnKaydet.setDisable(true);
        try(Scanner scanner = new Scanner(new FileReader("Anakart.txt"))) {
            while(scanner.hasNextLine()){
                String okunanSatir = scanner.nextLine();
                String[] array = okunanSatir.split(",");
                Anakart anakart = new Anakart(array[0], array[1], array[2], array[3], Integer.parseInt(array[4]));
                list.add(anakart);
            }
            markaColumn.setCellValueFactory(new PropertyValueFactory<Anakart, String>("Marka"));
            modelColumn.setCellValueFactory(new PropertyValueFactory<Anakart, String>("Model"));
            bellekTipiColumn.setCellValueFactory(new PropertyValueFactory<Anakart, String>("BellekTipi"));
            megaHertzColumn.setCellValueFactory(new PropertyValueFactory<Anakart, String>("megaHertz"));
            fiyatColumn.setCellValueFactory(new PropertyValueFactory<Anakart, Integer>("Fiyat"));
            anakartTableView.setItems(list);
            
        } catch (FileNotFoundException ex) {
            System.out.println("Dosya bulunamadı...");
        }
        anakartTableView.setEditable(true);
        markaColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        modelColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        bellekTipiColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        megaHertzColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        fiyatColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    }    

    @FXML
    private void onEditMarka(TableColumn.CellEditEvent<Anakart, String> event) {
        Anakart anakart = anakartTableView.getSelectionModel().getSelectedItem();
        anakart.setMarka(event.getNewValue());
        btnKaydet.setDisable(false);
    }

    @FXML
    private void onEditModel(TableColumn.CellEditEvent<Anakart, String> event) {
        Anakart anakart = anakartTableView.getSelectionModel().getSelectedItem();
        anakart.setModel(event.getNewValue());
        btnKaydet.setDisable(false);
    }

    @FXML
    private void onEditBellekTipi(TableColumn.CellEditEvent<Anakart, String> event) {
        Anakart anakart = anakartTableView.getSelectionModel().getSelectedItem();
        anakart.setBellekTipi(event.getNewValue());
        btnKaydet.setDisable(false);
    }

    @FXML
    private void onEditMegaHertz(TableColumn.CellEditEvent<Anakart, String> event) {
        Anakart anakart = anakartTableView.getSelectionModel().getSelectedItem();
        anakart.setMegaHertz(event.getNewValue());
        btnKaydet.setDisable(false);
    }

    @FXML
    private void onEditFiyat(TableColumn.CellEditEvent<Anakart, Integer> event) {
        Anakart anakart = anakartTableView.getSelectionModel().getSelectedItem();
        anakart.setFiyat(event.getNewValue());
        btnKaydet.setDisable(false);
    }

    @FXML
    private void btnSilClicked(ActionEvent event) {
        Anakart selectedItem = anakartTableView.getSelectionModel().getSelectedItem();
        anakartTableView.getItems().remove(selectedItem);
        btnKaydet.setDisable(false);
    }

    @FXML
    private void btnEkleClicked(ActionEvent event) {
        Anakart anakart = new Anakart(markaTextField.getText(), modelTextField.getText(), bellekTipiTextField.getText(), megaHertzTextField.getText(),Integer.parseInt(fiyatTextField.getText()));
        anakartTableView.getItems().add(anakart);
        btnKaydet.setDisable(false);
        markaTextField.clear();
        modelTextField.clear();
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
        try(FileWriter writer = new FileWriter("Anakart.txt")){
            Anakart anakart = new Anakart("", "", "", "", 0);
            
            
            List <List<String>> arrayList = new ArrayList<>();
            
            for(int i = 0; i < anakartTableView.getItems().size(); i++){
                anakart = anakartTableView.getItems().get(i);
                arrayList.add(new ArrayList<>());
                arrayList.get(i).add(anakart.getMarka());
                arrayList.get(i).add(anakart.getModel());
                arrayList.get(i).add(anakart.getBellekTipi());
                arrayList.get(i).add(anakart.getMegaHertz());
                arrayList.get(i).add(Integer.toString(anakart.getFiyat()));
            }
        
            for (int i = 0; i < arrayList.size(); i++) {
                for (int j = 0; j < arrayList.get(i).size(); j++) {
                    System.out.println(arrayList.get(i).get(j));
                    writer.write(arrayList.get(i).get(j) + ",");
                }
                writer.write("\n");
            }
            
        } catch (IOException ex) {
            Logger.getLogger(YoneticiAnakartController.class.getName()).log(Level.SEVERE, null, ex);
        }
        btnKaydet.setDisable(true);
    }
    
}
