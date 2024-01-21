
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

public class YoneticiDepolamaController implements Initializable {

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
    private TextField markaTextField;
    @FXML
    private TextField diskTuruTextField;
    @FXML
    private TextField depolamaBoyutuTextField;
    @FXML
    private TextField okumaYazmaHiziTextField;
    @FXML
    private TextField fiyatTextField;
    @FXML
    private Button btnKaydet;

    ObservableList<Depolama> list = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnKaydet.setDisable(true);
        try(Scanner scanner = new Scanner(new FileReader("Depolama.txt"))) {
            while(scanner.hasNextLine()){
                String okunanSatir = scanner.nextLine();
                String[] array = okunanSatir.split(",");
                Depolama depolama = new Depolama(array[0], array[1], array[2], array[3], Integer.parseInt(array[4]));
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
        depolamaTableView.setEditable(true);
        markaColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        diskTuruColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        depolamaBoyutuColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        hizColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        fiyatColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    }    

    @FXML
    private void onEditMarka(TableColumn.CellEditEvent<Depolama, String> event) {
        Depolama depolama = depolamaTableView.getSelectionModel().getSelectedItem();
        depolama.setMarka(event.getNewValue());
        btnKaydet.setDisable(false);
    }

    @FXML
    private void onEditDiskTuru(TableColumn.CellEditEvent<Depolama, String> event) {
        Depolama depolama = depolamaTableView.getSelectionModel().getSelectedItem();
        depolama.setDiskTuru(event.getNewValue());
        btnKaydet.setDisable(false);
    }

    @FXML
    private void onEditDepolamaBoyutu(TableColumn.CellEditEvent<Depolama, String> event) {
        Depolama depolama = depolamaTableView.getSelectionModel().getSelectedItem();
        depolama.setDepolamaBoyutu(event.getNewValue());
        btnKaydet.setDisable(false);
    }

    @FXML
    private void onEditHiz(TableColumn.CellEditEvent<Depolama, String> event) {
        Depolama depolama = depolamaTableView.getSelectionModel().getSelectedItem();
        depolama.setOkumaYazmaHizi(event.getNewValue());
        btnKaydet.setDisable(false);
    }

    @FXML
    private void onEditFiyat(TableColumn.CellEditEvent<Depolama, Integer> event) {
        Depolama depolama = depolamaTableView.getSelectionModel().getSelectedItem();
        depolama.setFiyat(event.getNewValue());
        btnKaydet.setDisable(false);
    }

    @FXML
    private void btnSilClicked(ActionEvent event) {
        Depolama selectedItem = depolamaTableView.getSelectionModel().getSelectedItem();
        depolamaTableView.getItems().remove(selectedItem);
        btnKaydet.setDisable(false);
    }

    @FXML
    private void btnEkleClicked(ActionEvent event) {
        Depolama depolama = new Depolama(markaTextField.getText(), diskTuruTextField.getText(), depolamaBoyutuTextField.getText(), okumaYazmaHiziTextField.getText(),Integer.parseInt(fiyatTextField.getText()));
        depolamaTableView.getItems().add(depolama);
        btnKaydet.setDisable(false);
        markaTextField.clear();
        diskTuruTextField.clear();
        depolamaBoyutuTextField.clear();
        okumaYazmaHiziTextField.clear();
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
        try(FileWriter writer = new FileWriter("Depolama.txt")){
            Depolama depolama = new Depolama("", "", "", "", 0);
            
            
            List <List<String>> arrayList = new ArrayList<>();
            
            for(int i = 0; i < depolamaTableView.getItems().size(); i++){
                depolama = depolamaTableView.getItems().get(i);
                arrayList.add(new ArrayList<>());
                arrayList.get(i).add(depolama.getMarka());
                arrayList.get(i).add(depolama.getDiskTuru());
                arrayList.get(i).add(depolama.getDepolamaBoyutu());
                arrayList.get(i).add(depolama.getOkumaYazmaHizi());
                arrayList.get(i).add(Integer.toString(depolama.getFiyat()));
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
