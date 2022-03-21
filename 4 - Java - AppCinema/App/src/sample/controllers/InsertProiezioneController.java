package sample.controllers;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.database.DatabaseConnection;
import sample.database.DatabaseUtil;
import sample.models.dao.implDAO.FilmDaoImpl;
import sample.models.dao.implDAO.ProiezioneDAOImpl;
import sample.models.dao.interfaceDAO.FilmDAO;
import sample.models.dao.interfaceDAO.ProiezioneDAO;
import sample.models.entity.Film;
import sample.models.enumerations.ORARI;

public class InsertProiezioneController implements Initializable {

  public ChoiceBox<String> films;
  public TextField prezzo;
  public AnchorPane paneInsertProiezione;
  private ProiezioneDAO proiezioneDAO;
  private FilmDAO filmDAO;
  private ORARI orari;
  private Integer idSala;
  private CinemaDashboardController cinemaDashboardController;

  public void conferma(MouseEvent mouseEvent) {
    if(!(films.getSelectionModel().isEmpty() && prezzo.getText().isEmpty())){
      proiezioneDAO = new ProiezioneDAOImpl(DatabaseConnection.getConnection());
      filmDAO = new FilmDaoImpl(DatabaseConnection.getConnection());
      var idFilm = filmDAO.queryFilmById(films.getSelectionModel().getSelectedItem());
      System.out.println(idFilm);
      var time = ORARI.getTime(orari);
      proiezioneDAO.queryInsertProiezione(time[0],time[1],orari,Integer.parseInt(prezzo.getText()),idFilm,idSala);
      cinemaDashboardController.refresh();
      close();
      return;
    }
    Alert alert = new Alert(AlertType.ERROR);
    alert.setHeaderText(null);
    alert.setContentText("Tutti i campi sono obbligatori");
    alert.showAndWait();
  }

  public void annulla(MouseEvent mouseEvent) {
    close();
  }

  public void start(){
    proiezioneDAO = new ProiezioneDAOImpl(DatabaseConnection.getConnection());
    var listProiezioni = proiezioneDAO.queryListProiezioniFilm();
    if (listProiezioni.isEmpty()) {
      var proiezioniValidi =
          listProiezioni.stream()
              .filter(src -> DatabaseUtil.checkLocalDate(src.getInizioData(), src.getFineData()))
              .collect(Collectors.toList());
      films.setItems(
          FXCollections.observableList(
              proiezioniValidi.stream()
                  .map(src -> src.getFilm().getTitolo())
                  .collect(Collectors.toList())));
    }else{
      filmDAO = new FilmDaoImpl(DatabaseConnection.getConnection());
      final var listFilms = filmDAO.queryListFilm();
      films.setItems(FXCollections.observableList(listFilms.stream().map(Film::getTitolo).collect(
          Collectors.toList())));
    }
  }

  public void setOrari(ORARI orari) {
    this.orari = orari;
  }

  public void setIdSala(Integer idSala) {
    this.idSala = idSala;
  }

  public void setCinemaDashboardController(
      CinemaDashboardController cinemaDashboardController) {
    this.cinemaDashboardController = cinemaDashboardController;
  }

  public void close(){
    var root = (Stage) paneInsertProiezione.getScene().getWindow();
    root.close();
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    start();
  }
}
