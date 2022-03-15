package sample.controllers;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import sample.models.entity.Film;
import sample.models.entity.Proiezione;

public class DetailsFilmModificaController {

  public TextField titolo;
  public TextField trama;
  public TextField regia;
  public TextField anno;
  public ChoiceBox genere;
  private Proiezione proiezione;
  private Film film;

  public void clickConferma(MouseEvent mouseEvent) {
  }

  public void clickAnnulla(MouseEvent mouseEvent) {
  }

  public void setProiezione(Proiezione proiezione) {
    this.proiezione = proiezione;
  }

  public void setFilm(Film film) {
    this.film = film;
  }
}
