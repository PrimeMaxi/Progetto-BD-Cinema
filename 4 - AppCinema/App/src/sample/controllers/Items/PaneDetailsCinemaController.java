package sample.controllers.Items;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.Application;
import sample.controllers.DetailsDalaModificaController;
import sample.models.entity.Sala;

public class PaneDetailsCinemaController implements Initializable {

  public AnchorPane paneDetailsCinema;
  public Pane paneLeftDetails;
  public Label salaNumeroDetailsCinema;
  public Label totNumero;
  public Label tecnInfo;
  public Label audioInfo;
  private Sala sala;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {

  }

  public void buttonModificaDetailsCinema(ActionEvent actionEvent) {
    Parent root;
    try {
      FXMLLoader detailsSalaModifica = new FXMLLoader();
      detailsSalaModifica.setLocation(Application.class.getResource("viewsRefactor/TastoCinema/DetailsSalaModifica.fxml"));
      Stage stage = new Stage();
      stage.setTitle("Modifica Sala");
      stage.setScene(new Scene(detailsSalaModifica.load()));
      DetailsDalaModificaController salaModifica = detailsSalaModifica.getController();
      salaModifica.setSala(sala);
      stage.show();
    }
    catch (IOException e) {
      Logger logger = Logger.getLogger(getClass().getName());
      logger.log(Level.SEVERE, "Failed to create new Window.", e);    }
  }

  public void setSalaNumeroDetailsCinema(Integer numero) {
    salaNumeroDetailsCinema.setText(numero.toString());
  }

  public void setTotNumero(Integer totNumero) {
    this.totNumero.setText(totNumero.toString());
  }


  public void setTecnInfo(String tecnInfo) {
    this.tecnInfo.setText(tecnInfo!=null ? tecnInfo : "-");
  }

  public void setAudioInfo(String audioInfo) {
    this.audioInfo.setText(audioInfo!=null ? audioInfo : "-");
  }

  public void setSala(Sala sala) {
    this.sala = sala;
    setSalaNumeroDetailsCinema(sala.getIdSala());
    setTotNumero(sala.getCapienza());
    setTecnInfo(sala.getTecnologia().toString());
    setAudioInfo(sala.getAudio().toString());
  }
}
