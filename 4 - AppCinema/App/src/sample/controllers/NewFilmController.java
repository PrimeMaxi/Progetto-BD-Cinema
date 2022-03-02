package sample.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.time.LocalTime;
import java.time.Year;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import sample.database.DatabaseConnection;
import sample.models.dao.implDAO.FilmDaoImpl;
import sample.models.entity.Film;
import sample.service.SceneCreator;

public class NewFilmController implements Initializable {

  public TextField Titolo;
  public TextField Trama;
  public TextField Regia;
  public TextField AnnoFilm;
  public TextField Hour;
  public TextField minutes;
  public TextField second;
  public ChoiceBox GENERE;
  public Button AggiornaButton;
  public Button AnnullaButton;
  public StackPane rootPane;
  private FilmDaoImpl filmDao;
  private Integer idFilm;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    GENERE.getItems().addAll(sample.models.enumerations.GENERE.values());
  }

  public void setData(ObservableList<Film> list){
    Film film = list.get(0);
    Titolo.setText(film.getTitolo());
    Trama.setText(film.getTrama());
    Regia.setText(film.getRegia());
    AnnoFilm.setText(film.getAnnoUscita().toString());
    Hour.setText(film.getDurataFilm().toString().substring(0,2));
    minutes.setText(film.getDurataFilm().toString().substring(3,5));
    second.setText(film.getDurataFilm().toString().substring(6,8));
    GENERE.setValue(film.getGenere());

    idFilm = film.getIdFilm();
  }

  public void updateFilm(ActionEvent actionEvent) {
    filmDao = new FilmDaoImpl(DatabaseConnection.getConnection());
    Film film = new Film();
    film.setIdFilm(idFilm);
    film.setTitolo(Titolo.getText());
    film.setTrama(Trama.getText());
    film.setRegia(Regia.getText());
    film.setAnnoUscita(Year.parse(AnnoFilm.getText()));

    String ore = Hour.getText();
    String minut = minutes.getText();
    String secondi = second.getText();
    String durataFilm = ore+":"+minut+":"+secondi;

    film.setDurataFilm(Time.valueOf(
        LocalTime.of(Integer.parseInt(ore),Integer.parseInt(minut),Integer.parseInt(secondi))));
    film.setGenere(sample.models.enumerations.GENERE.valueOf(GENERE.getValue().toString()));

    filmDao.updateFilm(film);

    try {
      SceneCreator.launchScene("views/CinemaManagerSystem.fxml");
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  public void cancelFilm(ActionEvent actionEvent) {
    try {
      SceneCreator.launchScene("views/CinemaManagerSystem.fxml");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
