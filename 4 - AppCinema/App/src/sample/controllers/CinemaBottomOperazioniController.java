package sample.controllers;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.helpers.StageHelper;

public class CinemaBottomOperazioniController {

  public void film(ActionEvent actionEvent) throws IOException {
    final var loader = StageHelper.getLoaderFxml("TastoCinema/FilmInsert");
    final var stage = new Stage();
    stage.setTitle("Inserisci film");
    stage.setScene(new Scene(loader.load()));
    stage.show();
  }

  public void sala(ActionEvent actionEvent) {
  }

  public void proiezione(ActionEvent actionEvent) {
  }

  public void listFilm(ActionEvent actionEvent) {
  }
}
