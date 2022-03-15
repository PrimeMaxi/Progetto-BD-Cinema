package sample.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.Application;
import sample.database.DatabaseConnection;
import sample.models.dao.implDAO.ProiezioneDAOImpl;
import sample.models.dao.interfaceDAO.ProiezioneDAO;
import sample.models.entity.Film;
import sample.models.entity.Proiezione;
import sample.models.enumerations.ORARI;

public class DetailsProiezioneModificaController implements Initializable {

  public TextField prezzo;
  public ChoiceBox<ORARI> orarioProiezione;
  public AnchorPane paneDetailsModifica;
  private Proiezione proiezione;
  private Film film;
  private ProiezioneDAO proiezioneDAO;

  public void clickModificaFilm(MouseEvent mouseEvent) {
    Parent root;
    try {
      FXMLLoader detailsFilmModifica = new FXMLLoader();
      detailsFilmModifica.setLocation(Application.class.getResource("viewsRefactor/TastoCinema/DetailsFilmModifica.fxml"));
      Stage stage = new Stage();
      stage.setTitle("Modifica Proiezione");
      stage.setScene(new Scene(detailsFilmModifica.load()));
      DetailsFilmModificaController filmModifica = detailsFilmModifica.getController();
      stage.show();
    }
    catch (IOException e) {
      Logger logger = Logger.getLogger(getClass().getName());
      logger.log(Level.SEVERE, "Failed to create new Window.", e);
    }
  }

  public void clickConferma(MouseEvent mouseEvent) {
    proiezioneDAO = new ProiezioneDAOImpl(DatabaseConnection.getConnection());
    proiezioneDAO.queryUpdateProiezione(proiezione.getIdProiezione(),Integer.parseInt(prezzo.getText()),orarioProiezione.getValue());
    close();
  }

  public void clickAnnulla(MouseEvent mouseEvent) {
    close();
  }

  public void setFilm(Film film) {
    this.film = film;
  }

  public void setProiezione(Proiezione proiezione) {
    this.proiezione = proiezione;
    orarioProiezione.setValue(ORARI.getORARI(proiezione.getOrarioProiezione()));
    prezzo.setText(proiezione.getPrezzo().toString());
  }
  public void close(){
    var root = (Stage) paneDetailsModifica.getScene().getWindow();
    root.close();
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    orarioProiezione.setItems(FXCollections.observableList(ORARI.getListORARI()));
  }
}


