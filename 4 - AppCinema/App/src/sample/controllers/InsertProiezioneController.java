package sample.controllers;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.database.DatabaseConnection;
import sample.models.dao.implDAO.FilmDaoImpl;
import sample.models.dao.implDAO.ProiezioneDAOImpl;
import sample.models.dao.interfaceDAO.FilmDAO;
import sample.models.dao.interfaceDAO.ProiezioneDAO;
import sample.models.enumerations.ORARI;

public class InsertProiezioneController {

  public ChoiceBox<String> films;
  public TextField prezzo;
  public AnchorPane paneInsertProiezione;
  private ProiezioneDAO proiezioneDAO;
  private FilmDAO filmDAO;
  private ORARI orari;
  private Integer idSala;

  public void conferma(MouseEvent mouseEvent) {
    if(!(films.getSelectionModel().isEmpty() && prezzo.getText().isEmpty())){
      proiezioneDAO = new ProiezioneDAOImpl(DatabaseConnection.getConnection());
      filmDAO = new FilmDaoImpl(DatabaseConnection.getConnection());
      var idFilm = filmDAO.queryFilmById(films.getSelectionModel().getSelectedItem());
      var time = ORARI.getTime(orari);
      proiezioneDAO.queryInsertProiezione(time[0],time[1],orari,Integer.parseInt(prezzo.getText()),idFilm,idSala);
      close();
    }
    Alert alert = new Alert(AlertType.ERROR);
    alert.setHeaderText(null);
    alert.setContentText("Tutti i campi sono obbligatori");
    alert.showAndWait();
  }

  public void annulla(MouseEvent mouseEvent) {
    close();
  }

  public void setOrari(ORARI orari) {
    this.orari = orari;
  }

  public void setIdSala(Integer idSala) {
    this.idSala = idSala;
  }

  public void close(){
    var root = (Stage) paneInsertProiezione.getScene().getWindow();
    root.close();
  }
}
