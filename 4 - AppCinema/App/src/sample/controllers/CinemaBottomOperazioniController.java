package sample.controllers;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.helpers.StageHelper;

public class CinemaBottomOperazioniController {

  private CinemaDashboardController cinemaDashboardController;

  public void film(ActionEvent actionEvent) throws IOException {
    final var loader = StageHelper.getLoaderFxml("TastoCinema/FilmInsert");
    final var stage = new Stage();
    stage.setTitle("Inserisci film");
    stage.setScene(new Scene(loader.load()));
    stage.show();
  }

  public void sala(ActionEvent actionEvent) throws IOException {
    final var loader = StageHelper.getLoaderFxml("TastoCinema/InsertSala");
    final var stage = new Stage();
    stage.setTitle("Inserisci sala");
    stage.setScene(new Scene(loader.load()));
    stage.show();
  }

  public void proiezione(ActionEvent actionEvent) throws IOException {
    final var loader = StageHelper.getLoaderFxml("TastoCinema/InsertProiezioneCompleto");
    final var stage = new Stage();
    stage.setTitle("Inserisci proiezione");
    stage.setScene(new Scene(loader.load()));
    InsertProiezioneCompletoController insertProiezioneCompletoController= loader.getController();
    insertProiezioneCompletoController.setCinemaDashboardController(cinemaDashboardController);
    stage.show();
  }

  public void listFilm(ActionEvent actionEvent) throws IOException {
    final var loader = StageHelper.getLoaderFxml("TastoCinema/ListaFilm");
    final var stage = new Stage();
    stage.setTitle("Lista film");
    stage.setScene(new Scene(loader.load()));
    stage.show();
  }

  public void setCinemaDashboardController(
      CinemaDashboardController cinemaDashboardController) {
    this.cinemaDashboardController = cinemaDashboardController;
  }
}
