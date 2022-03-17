package sample.controllers;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.Application;
import sample.database.DatabaseConnection;
import sample.database.DatabaseUtil;
import sample.models.dao.implDAO.ProiezioneDAOImpl;
import sample.models.dao.interfaceDAO.ProiezioneDAO;
import sample.models.entity.Film;
import sample.models.entity.Proiezione;

public class PaneDetailsCinemaFilmController {

  public Pane paneLeftDetails;
  public Text textTitoloFilm;
  public Text textTrama;
  public Text textRegia;
  public Text textAnno;
  public Text textGenere;
  private Proiezione proiezione;
  private Film film;
  private ProiezioneDAO proiezioneDAO;
  private CinemaDashboardController cinemaDashboardController;

  public void buttonModificaProiezione(ActionEvent actionEvent) {
    Parent root;
    try {
      FXMLLoader detailsProiezioneModifica = new FXMLLoader();
      detailsProiezioneModifica.setLocation(Application.class.getResource("viewsRefactor/TastoCinema/DetailsProiezioneModifica.fxml"));
      Stage stage = new Stage();
      stage.setTitle("Modifica Proiezione");
      stage.setScene(new Scene(detailsProiezioneModifica.load()));
      DetailsProiezioneModificaController proiezioneModifica = detailsProiezioneModifica.getController();
      proiezioneModifica.setProiezione(proiezione);
      proiezioneModifica.setFilm(film);
      stage.show();
    }
    catch (IOException e) {
      Logger logger = Logger.getLogger(getClass().getName());
      logger.log(Level.SEVERE, "Failed to create new Window.", e);
    }
  }

  public void setProiezione(Proiezione proiezione) {
    this.proiezione = proiezione;
    setTextTitoloFilm(proiezione.getFilm().getTitolo());
  }

  public void setTextTitoloFilm(String textTitoloFilm) {
    this.textTitoloFilm.setText(textTitoloFilm);
  }

  public void setFilm(Film film) {
    this.film = film;
    this.textTrama.setText(film.getTrama());
    this.textGenere.setText(film.getGenere().toString());
    this.textAnno.setText(film.getAnnoUscita().toString());
    this.textRegia.setText(film.getRegia());
  }

  public void cancellaProiezione(ActionEvent actionEvent) {
    proiezioneDAO = new ProiezioneDAOImpl(DatabaseConnection.getConnection());
    proiezioneDAO.queryDeleteProiezione(proiezione);
    cinemaDashboardController.refresh();
  }

  public void setCinemaDashboardController(
      CinemaDashboardController cinemaDashboardController) {
    this.cinemaDashboardController = cinemaDashboardController;
  }
}
