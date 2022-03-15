package sample.controllers;

import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import sample.models.entity.Proiezione;

public class PaneDetailsCinemaFilmController {

  public Pane paneLeftDetails;
  public Text textTitoloFilm;
  public Text textTrama;
  public Text textRegia;
  public Text textAnno;
  public Text textGenere;
  private Proiezione proiezione;

  public void buttonModificaFilm(ActionEvent actionEvent) {
  }

  public void setProiezione(Proiezione proiezione) {
    this.proiezione = proiezione;
    setTextTitoloFilm(proiezione.getFilm().getTitolo());
  }

  public void setTextTitoloFilm(String textTitoloFilm) {
    this.textTitoloFilm.setText(textTitoloFilm);
  }
}
