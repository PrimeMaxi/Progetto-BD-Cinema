package sample.controllers;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.Application;
import sample.models.enumerations.ORARI;

public class ItemNOController {

  private ORARI orari;
  private Integer idSala;

  public void insertProiezione(MouseEvent mouseEvent) {
    Parent root;
    try {
      FXMLLoader insertProiezioneLoader = new FXMLLoader();
      insertProiezioneLoader.setLocation(Application.class.getResource("viewsRefactor/TastoCinema/InsertProiezione.fxml"));
      Stage stage = new Stage();
      stage.setTitle("Inserisci Proiezione");
      stage.setScene(new Scene(insertProiezioneLoader.load()));
      InsertProiezioneController insertProiezione = insertProiezioneLoader.getController();
      insertProiezione.setOrari(orari);
      insertProiezione.setIdSala(idSala);
      stage.show();
    }
    catch (IOException e) {
      Logger logger = Logger.getLogger(getClass().getName());
      logger.log(Level.SEVERE, "Failed to create new Window.", e);
    }
  }

  public void setOrari(ORARI orari) {
    this.orari = orari;
  }

  public void setIdSala(Integer idSala) {
    this.idSala = idSala;
  }
}
