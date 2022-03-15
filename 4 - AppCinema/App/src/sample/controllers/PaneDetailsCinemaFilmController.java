package sample.controllers;

import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
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

  public void buttonModificaFilm(ActionEvent actionEvent) {
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
}
