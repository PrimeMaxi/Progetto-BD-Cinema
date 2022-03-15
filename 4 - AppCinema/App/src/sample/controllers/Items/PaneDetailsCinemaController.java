package sample.controllers.Items;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
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
