package sample.controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import sample.database.DatabaseConnection;
import sample.helpers.StageHelper;
import sample.models.dao.implDAO.FilmDaoImpl;
import sample.models.dao.implDAO.ProiezioneDAOImpl;
import sample.models.dao.interfaceDAO.FilmDAO;
import sample.models.dao.interfaceDAO.ProiezioneDAO;
import sample.models.enumerations.ORARI;
import sample.service.Implementations.DefaultInsertProiezioneCompleto;
import sample.service.InsertProiezioneCompleto;

public class InsertProiezioneCompletoController implements Initializable {

  public AnchorPane paneInsertCompleto;
  public TextField prezzo;
  public ChoiceBox<ORARI> fasciaOrari;
  public ChoiceBox<String> films;
  public DatePicker dataFrom;
  public DatePicker dataTo;
  public ChoiceBox<Integer> numeroSala;
  private FilmDAO filmDAO = new FilmDaoImpl(DatabaseConnection.getConnection());
  private ProiezioneDAO proiezioneDAO;
  private InsertProiezioneCompleto insertProiezioneCompleto = new DefaultInsertProiezioneCompleto();
  private CinemaDashboardController cinemaDashboardController;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    // Data To di default
    dataTo.setValue(LocalDate.now());
    //Fascia oraria disabilitato
    fasciaOrari.setDisable(true);
    //Films
    insertProiezioneCompleto.setChoichFilms(films);
    //Sale disabilitato
    numeroSala.setDisable(true);
  }

  public void datafrom(ActionEvent actionEvent) {
    final var dayCellFactory = new Callback<DatePicker, DateCell>(){
      @Override
      public DateCell call(DatePicker datePicker) {
        return new DateCell(){
          @Override
          public void updateItem(LocalDate localDate, boolean b) {
            super.updateItem(localDate, b);

            if(localDate.isBefore(
                dataFrom.getValue()
            )){
              setDisable(true);
              setStyle("-fx-background-color: #ffc0cb;");
            }
          }
        };
      }
    };
    dataTo.setDayCellFactory(dayCellFactory);
    numeroSala.setDisable(false);
    final var saleDisponibili = insertProiezioneCompleto.getSaleDisponibili(dataFrom.getValue(),dataTo.getValue());
    numeroSala.setItems(FXCollections.observableList(saleDisponibili.stream().toList()));
  }

  public void dataTo(ActionEvent actionEvent) {
    System.out.println(unlockFasciaOraria());
  }


  public void conferma(ActionEvent actionEvent) {
    if(dataFrom.getValue()!=null && !prezzo.getText().isEmpty()){
      proiezioneDAO = new ProiezioneDAOImpl(DatabaseConnection.getConnection());
      final var time = ORARI.getTime(fasciaOrari.getValue());
      proiezioneDAO.queryInsertProiezione(
          time[0],
          time[1],
          fasciaOrari.getValue(),
          Integer.parseInt(prezzo.getText()),
          filmDAO.queryFilmById(films.getValue()),
          numeroSala.getValue()
          );
      cinemaDashboardController.refresh();
      StageHelper.close(paneInsertCompleto);
    }else{
      Alert alert = new Alert(AlertType.ERROR);
      alert.setHeaderText(null);
      alert.setContentText("Tutti i campi sono obbligatori");
      alert.showAndWait();
    }
  }

  public boolean unlockFasciaOraria(){
    if(dataFrom.getValue()!=null && numeroSala.getValue()!=null){
      fasciaOrari.setDisable(false);
      final var idSala = numeroSala.getValue();
      final var saleDisponibili = insertProiezioneCompleto.getSaleDisponibili(dataFrom.getValue(),dataTo.getValue());
      final var orariDisponibili = insertProiezioneCompleto.getOrariDisponibili(saleDisponibili,idSala);
      fasciaOrari.setItems(FXCollections.observableList(orariDisponibili));
      return true;
    }
    return false;
  }

  public void annulla(ActionEvent actionEvent) {
    StageHelper.close(paneInsertCompleto);
  }

  public void filmClicked(MouseEvent mouseEvent) {
    films.setOnAction(actionEvent -> unlockFasciaOraria());
  }

  public void salaClicked(MouseEvent mouseEvent) {
    numeroSala.setOnAction((actionEvent) -> unlockFasciaOraria());
  }

  public void setCinemaDashboardController(
      CinemaDashboardController cinemaDashboardController) {
    this.cinemaDashboardController = cinemaDashboardController;
  }
}
